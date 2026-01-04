package com.nano.banking.services;

import com.nano.banking.dtos.CreateTransactionRequest;
import com.nano.banking.dtos.TransactionResponse;
import com.nano.banking.entities.Customer;
import com.nano.banking.entities.Transaction;
import com.nano.banking.mappers.TransactionMapper;
import com.nano.banking.repositories.CustomerRepository;
import com.nano.banking.repositories.TransactionRepository;
import com.nano.shared.dtos.ApiResponse;
import com.nano.shared.enums.PaymentMethod;
import com.nano.shared.enums.TransactionStatus;
import com.nano.shared.enums.TransactionType;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@NoArgsConstructor
public class TransactionService implements ITransactionService {

    private CustomerRepository customerRepository;
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private WebClient webClient;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              CustomerRepository customerRepository, TransactionMapper transactionMapper,
                              WebClient webClient) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.transactionMapper = transactionMapper;
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<TransactionResponse> createTransaction(CreateTransactionRequest request) {

        try {
            log.debug("Received request to create transaction: {}", request);
            // get account details from account service
            Customer customer = customerRepository.findByAccountId(request.getAccountId());
            if (customer == null) {
                log.error("Customer not found for accountId: {}", request.getAccountId());
                return new ApiResponse<>(false, "Customer not found", null, "CUSTOMER_NOT_FOUND");
            }

            Transaction transaction = transactionMapper.toTransactionEntity(request);
            String referenceNumber = "TXN-" + System.currentTimeMillis();
            transaction.setReferenceNumber(referenceNumber);
            transaction.setDescription("Funds Top-up");
            transaction.setPaymentMethod(PaymentMethod.DEBIT_CARD.toString());
            transaction.setTransactionType(TransactionType.DEPOSIT.toString());
            transaction.setStatus(TransactionStatus.PENDING.toString());
            transactionRepository.save(transaction);

            var paymentRequest = new Object() {
                public final String accountId = customer.getAccountId().toString();
                public final String referenceNumber = transaction.getReferenceNumber();
                public final Double amount = transaction.getAmount();
                public final String transactionDescription = transaction.getDescription();
                public final String paymentMethod = transaction.getPaymentMethod();
            };

            TransactionResponse transactionResponse = transactionMapper.toTransactionResponse(transaction);

            var paymentApiResponse = webClient
                    .post()
                    .uri("/api/v1/payments/process")
                    .bodyValue(paymentRequest)
                    .retrieve()
                    .bodyToMono(ApiResponse.class)
                    .block();

            if (paymentApiResponse != null && paymentApiResponse.getIsSuccess()) {
                return new ApiResponse<TransactionResponse>(true, "Transaction processing", transactionResponse, null);
            } else {
                transaction.setStatus(TransactionStatus.FAILED.toString());
                transactionRepository.save(transaction);
                log.error("Payment processing failed for transaction: {}", transaction);
                return new ApiResponse<>(false, "Payment processing failed", null, "PAYMENT_FAILED");
            }


        } catch (Exception e) {
            log.error("Error creating transaction: {}", e.getMessage());
            return new ApiResponse<>(false, "Failed to create transaction", null, "TRANSACTION_CREATION_ERROR");
        }


    }

    @Override
    public ApiResponse<TransactionResponse> getTransactionByReferenceNumber(String referenceNumber) {
        try {
            log.debug("Fetching transaction with reference number: {}", referenceNumber);
            Transaction transaction = transactionRepository.findByReferenceNumber(referenceNumber);
            if (transaction == null) {
                log.error("Transaction not found for reference number: {}", referenceNumber);
                return new ApiResponse<>(false, "Transaction not found", null, "TRANSACTION_NOT_FOUND");
            }
            TransactionResponse response = transactionMapper.toTransactionResponse(transaction);
            return new ApiResponse<>(true, "Transaction retrieved successfully", response, null);
        } catch (Exception e) {
            log.error("Error retrieving transaction: {}", e.getMessage());
            return new ApiResponse<>(false, "Failed to retrieve transaction", null, "TRANSACTION_RETRIEVAL_ERROR");
        }
    }
}
