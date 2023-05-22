package com.example.account.domain;

import com.example.account.exception.AccountException;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity {
    @ManyToOne
    private AccountUser accountUser;            // 소유자 정보, 사용자 테이블과 연결
    private String accountNumber;               // 계좌번호

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;        // 계좌상태
    private Long balance;

    private LocalDateTime registeredAt;         // 계좌등록일시
    private LocalDateTime unRegisteredAt;       // 계좌해지일시

    public void useBalance(Long amount) {
        if (amount > balance) {
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void cancelBalance(Long amount) {
        if (amount < 0) {
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        balance += amount;
    }
}
