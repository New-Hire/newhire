package com.muyu.newhire.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Nullable
    @Column(name = "logo_id")
    private Long logoId;

    @NotBlank
    private String desc;

    @NotBlank
    private String type;

    @NotBlank
    private String province;

    private int size;

    @NotBlank
    private String city;

    @NotBlank
    @Column(name = "established_date")
    private String establishedDate;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
