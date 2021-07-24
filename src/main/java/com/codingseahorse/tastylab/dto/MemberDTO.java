package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.member.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberDTO {
    private @NonNull String firstName;
    private @NonNull String lastName;
    private String email;
    private @NonNull Integer age;
    private @NonNull Gender gender;
    private Page<RecipeDTO> recipes;
    private MemberCardDTO memberCardDTO;
}
