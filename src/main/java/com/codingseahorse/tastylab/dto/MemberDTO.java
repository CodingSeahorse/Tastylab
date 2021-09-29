package com.codingseahorse.tastylab.dto;

import com.codingseahorse.tastylab.model.member.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@RequiredArgsConstructor
public class MemberDTO {
    private final String firstName;
    private final String lastName;
    private String email;
    private final Integer age;
    private final Gender gender;
    private Page<RecipeDTO> recipes;
    private MemberCardDTO memberCardDTO;
}
