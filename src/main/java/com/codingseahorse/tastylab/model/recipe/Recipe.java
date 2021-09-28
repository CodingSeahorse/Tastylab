package com.codingseahorse.tastylab.model.recipe;

import com.codingseahorse.tastylab.model.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name = "recipe",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "recipe_id",
                        columnNames = "recipe_id")
        })
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_id_sequencer",
            sequenceName = "recipe_id_sequencer",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "recipe_id_sequencer")
    @Column(
            name = "recipe_id",
            nullable = false,
            updatable = false)
    private Integer recipeId;

    @NonNull
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @NonNull
    @Column(
            name = "recipe_name",
            nullable = false,
            columnDefinition = "TEXT",
            length = 100)
    private String recipeName;

    @NonNull
    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "INTEGER")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "recipeStatus",
            nullable = false)
    private RecipeStatus recipeStatus = RecipeStatus.NORMAL; // DEFAULT VALUE

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(
            name = "recipe_skills",
            nullable = false)
    private RecipeSkills recipeSkills;

    @NonNull
    @ElementCollection(targetClass = Food.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "recipe_foods")
    @Column(
            name = "foods")
    private Collection<Food> foods;

    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "member_id",
            nullable = false,
            referencedColumnName = "member_id",
            foreignKey = @ForeignKey(
                    name = "member_recipes_fk"))
    private Member creator;

    @NonNull
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_tags",

            joinColumns = @JoinColumn(
                    name = "recipe_id",
                    foreignKey = @ForeignKey(name = "recipes_id_fk")),

            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    foreignKey = @ForeignKey(name = "tags_id_fk")))
    private Set<FoodTag> recipeTag;
}
