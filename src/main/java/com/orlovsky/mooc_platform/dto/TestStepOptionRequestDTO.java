package com.orlovsky.mooc_platform.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestStepOptionRequestDTO {
    private Long id;
    private String optionText;
    private Boolean isCorrect;

    public boolean isCorrect() {
        return this.isCorrect;
    }

    public void setCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TestStepOptionRequestDTO;
    }

}
