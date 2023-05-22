package com.example.account.dto;

import com.example.account.aop.AccountLockIdInterface;
import com.example.account.type.TransactionResultType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

public class CancelBalance {
    /**
     * {
     * "transactionId":"abddfkdjfjdldk34dk",
     * "accountNumber":"1000000000",
     * "amount":1000
     * }
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements AccountLockIdInterface {       // 요청
        @NotBlank
        private String transactionId;

        @NotBlank
        @Size(min = 10, max = 10)
        private String accountNumber;

        @NotNull
        @Min(10)
        @Max(1000_000_000)
        private Long amount;
    }

    /**
     *{
     * "accountNumber":"1234567890",
     * "transactionResult":"S",
     * "transactionId":"c2033bb6d82a4250aecf8e27c49b63f6",
     * "amount":1000,
     * "transactedAt":"2022-06-01T23:26:14.671859"
     * }
     *
     */

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String accountNumber;                       // 계좌번호
        private TransactionResultType transactionResult;
        private String transactionId;
        private Long amount;
        private LocalDateTime transactedAt;                 // 계좌등록일시

        public static Response from(TransactionDto transactionalDto) {
            return Response.builder()
                    .accountNumber(transactionalDto.getAccountNumber())
                    .transactionResult(transactionalDto.getTransactionResultType())
                    .transactionId(transactionalDto.getTransactionId())
                    .amount(transactionalDto.getAmount())
                    .transactedAt(transactionalDto.getTransactedAt())
                    .build();
        }
    }
}
