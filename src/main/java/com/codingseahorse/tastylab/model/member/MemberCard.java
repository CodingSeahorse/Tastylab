package com.codingseahorse.tastylab.model.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(
        name = "member_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_card_id",
                        columnNames = "member_card_id")
        })
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberCard {
    @Id
    @GeneratedValue(
            strategy = AUTO)
    @Column(
            name = "member_card_id",
            nullable = false,
            updatable = false)
    private Long memberCardId;

    @NonNull
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @NonNull
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT")
    private String username;

    @NonNull
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT")
    private String password;
}