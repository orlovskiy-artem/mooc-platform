package com.orlovsky.mooc_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalStepDTO {
    private Long id;
    private Long courseId;
    private URI eduMaterialUri;
    private int position;
}
