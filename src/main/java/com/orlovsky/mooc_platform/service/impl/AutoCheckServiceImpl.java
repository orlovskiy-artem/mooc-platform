package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
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
        if(chosenTestOption.getIsCorrect()) return ActionType.PASSED;
        else return ActionType.TRIED;
    }

    @Override
    public ActionType checkTestTask(TestStep testStep, TestStepDTO testStepDTO) {
        Boolean flag = true;
        for (TestStepOptionRequestDTO option: testStepDTO.getAnswers())
        {
            if(!flag){break;}
            for(TestStepOption answer: testStep.getAnswers()){
                if(answer.getId()==option.getId() &&
                        answer.getIsCorrect()!=option.getIsCorrect()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag) return ActionType.PASSED;
        else return ActionType.TRIED;
    }
}
