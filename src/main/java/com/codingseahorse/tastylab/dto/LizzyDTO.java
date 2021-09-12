package com.codingseahorse.tastylab.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class LizzyDTO {
    private Page<RecipeDTO> resultList;
}
