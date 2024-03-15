package com.muyu.newhire.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyCandidate {

    public CompanyCandidate(Long companyId, Long userId) {
        this.companyId = companyId;
        this.userId = userId;
        this.status = CandidateStatus.INITIALIZED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_id")
    private Long companyId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private CandidateStatus status;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Getter
    public enum CandidateStatus {
        INITIALIZED(0),
        INVITED(1),
        APPROVED(2),
        REJECTED(3);
        @JsonValue
        private final int value;

        CandidateStatus(int value) {
            this.value = value;
        }
    }

}
