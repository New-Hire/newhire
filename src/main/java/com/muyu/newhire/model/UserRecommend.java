package com.muyu.newhire.model;

import jakarta.annotation.Nullable;
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
public class UserRecommend {

    public UserRecommend(long raterId, long raterCompanyId, long userId, @Nullable Long userCompanyId, int level) {
        this.raterId = raterId;
        this.raterCompanyId = raterCompanyId;
        this.userId = userId;
        this.userCompanyId = userCompanyId;
        this.level = level;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rater_id")
    private Long raterId;

    @NotNull
    @Column(name = "rater_company_id")
    private Long raterCompanyId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "user_company_id")
    private Long userCompanyId;

    private int level;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
