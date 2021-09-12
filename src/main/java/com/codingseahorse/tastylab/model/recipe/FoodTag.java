package com.codingseahorse.tastylab.model.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "foodTags",
        uniqueConstraints = @UniqueConstraint(
                name = "tagId",columnNames = "tag_id"))
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"recipe"})
public class FoodTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "tag_id",
            nullable = false,
            updatable = false)
    private Long tagId;

    @NonNull
    @Column(
            name = "tag_name",
            nullable = false)
    private String tagName;

    @ManyToMany(
            mappedBy = "recipeTag",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Recipe> recipe = new HashSet<>();
}
