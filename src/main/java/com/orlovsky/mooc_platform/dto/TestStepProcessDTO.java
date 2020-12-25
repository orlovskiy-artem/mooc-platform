package com.orlovsky.mooc_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestStepProcessDTO {
    private Long id;
    private Long courseId;
    private String description;
    private List<Boolean> answersChecked;
    private int score;
    private int position;
}
