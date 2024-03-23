package com.muyu.newhire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

// 只读，不准写
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserScore {

    @Id
    private Long userId;

    @NotNull
    private Integer score;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
