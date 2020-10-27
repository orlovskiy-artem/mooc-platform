package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.model.ActionType;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.TestStepOption;
import com.orlovsky.mooc_platform.service.AutoCheckService;
import org.springframework.stereotype.Service;

@Service
public class AutoCheckServiceImpl implements AutoCheckService {
    @Override
    public ActionType checkTestTask(TestStep testStep,
                                    TestStepOption chosenTestOption) {
        if(chosenTestOption.isCorrect()) return ActionType.PASSED;
        else return ActionType.TRIED;
    }
}
