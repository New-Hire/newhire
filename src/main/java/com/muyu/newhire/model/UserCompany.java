package com.muyu.newhire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCompany {

    public UserCompany(Long companyId, Long userId, int order) {
        this.companyId = companyId;
        this.userId = userId;
        this.order = order;
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

    private int order;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
