package com.codingseahorse.tastylab.model.member;

import com.codingseahorse.tastylab.model.recipe.Recipe;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_id",
                        columnNames = "member_id"),
                @UniqueConstraint(
                        name = "email",
                        columnNames = "email")
        })
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @SequenceGenerator(
            name = "member_id_sequencer",
            sequenceName = "member_id_sequencer",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "member_id_sequencer")
    @Column(
            name = "member_id",
            nullable = false,
            updatable = false)
    private Integer memberId;

    @NonNull
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT",
            length = 30)
    private String firstName;

    @NonNull
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT",
            length = 60)
    private String lastName;

    @NonNull
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            length = 100)
    private String email;

    @NonNull
    @Column(
            name = "age",
            nullable = false,
            columnDefinition = "INTEGER",
            length = 3)
    private Integer age;

    @NonNull
    @Enumerated(STRING)
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT")
    private Gender gender;

    @NonNull
    @OneToOne(
            cascade = ALL)
    @JoinColumn(
            name = "memberCardId",
            referencedColumnName = "member_card_id")
    private MemberCard membercard;

    @OneToMany(
            mappedBy = "creator",
            cascade = ALL,
            fetch = EAGER)
    private List<Recipe> recipes;
}