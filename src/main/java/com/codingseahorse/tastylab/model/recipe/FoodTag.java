package com.codingseahorse.tastylab.model.recipe;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "tag",
        uniqueConstraints = @UniqueConstraint(
                name = "tagId",columnNames = "tag_id"
        )
)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FoodTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "tag_id",
            nullable = false,
            updatable = false
    )
    private Long tagId;

    @NonNull
    @Column(
            name = "tag_name",
            nullable = false
    )
    private String tagName;

    @ManyToMany(
            mappedBy = "foodTag"
    )
    private List<Recipe> recipe = new ArrayList<>();
}
