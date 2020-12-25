package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.model.ActionType;
import com.orlovsky.mooc_platform.model.TestStepOption;
import com.orlovsky.mooc_platform.model.TestStep;

public interface AutoCheckService {
    ActionType checkTestTask(TestStep testStep,
                             TestStepOption chosenTestAnswer);
    ActionType checkTestTask(TestStep testStep,
                             TestStepDTO testStepDTO);
}
