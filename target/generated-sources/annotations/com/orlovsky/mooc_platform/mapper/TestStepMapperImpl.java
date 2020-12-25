package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepDTO.TestStepDTOBuilder;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.TestStepOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T05:27:47+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class TestStepMapperImpl implements TestStepMapper {

    @Override
    public TestStepDTO toDto(TestStep testStep) {
        if ( testStep == null ) {
            return null;
        }

        TestStepDTOBuilder testStepDTO = TestStepDTO.builder();

        testStepDTO.answers( testStepOptionListToTestStepOptionRequestDTOList( testStep.getAnswers() ) );
        testStepDTO.score( testStep.getScore() );
        testStepDTO.description( testStep.getDescription() );
        testStepDTO.position( testStep.getPosition() );
        testStepDTO.id( testStep.getId() );

        return testStepDTO.build();
    }

    @Override
    public TestStep toEntity(TestStepDTO testStepDTO) {
        if ( testStepDTO == null ) {
            return null;
        }

        TestStep testStep = new TestStep();

        testStep.setAnswers( testStepOptionRequestDTOListToTestStepOptionList( testStepDTO.getAnswers() ) );
        testStep.setScore( testStepDTO.getScore() );
        testStep.setDescription( testStepDTO.getDescription() );
        testStep.setPosition( testStepDTO.getPosition() );
        testStep.setId( testStepDTO.getId() );

        return testStep;
    }

    @Override
    public Collection<TestStep> toEntities(List<TestStepDTO> testStepDTOs) {
        if ( testStepDTOs == null ) {
            return null;
        }

        Collection<TestStep> collection = new ArrayList<TestStep>( testStepDTOs.size() );
        for ( TestStepDTO testStepDTO : testStepDTOs ) {
            collection.add( toEntity( testStepDTO ) );
        }

        return collection;
    }

    @Override
    public List<TestStepDTO> toDtos(Collection<TestStep> testSteps) {
        if ( testSteps == null ) {
            return null;
        }

        List<TestStepDTO> list = new ArrayList<TestStepDTO>( testSteps.size() );
        for ( TestStep testStep : testSteps ) {
            list.add( toDto( testStep ) );
        }

        return list;
    }

    protected TestStepOptionRequestDTO testStepOptionToTestStepOptionRequestDTO(TestStepOption testStepOption) {
        if ( testStepOption == null ) {
            return null;
        }

        TestStepOptionRequestDTO testStepOptionRequestDTO = new TestStepOptionRequestDTO();

        testStepOptionRequestDTO.setId( testStepOption.getId() );
        testStepOptionRequestDTO.setOptionText( testStepOption.getOptionText() );
        testStepOptionRequestDTO.setIsCorrect( testStepOption.getIsCorrect() );

        return testStepOptionRequestDTO;
    }

    protected List<TestStepOptionRequestDTO> testStepOptionListToTestStepOptionRequestDTOList(List<TestStepOption> list) {
        if ( list == null ) {
            return null;
        }

        List<TestStepOptionRequestDTO> list1 = new ArrayList<TestStepOptionRequestDTO>( list.size() );
        for ( TestStepOption testStepOption : list ) {
            list1.add( testStepOptionToTestStepOptionRequestDTO( testStepOption ) );
        }

        return list1;
    }

    protected TestStepOption testStepOptionRequestDTOToTestStepOption(TestStepOptionRequestDTO testStepOptionRequestDTO) {
        if ( testStepOptionRequestDTO == null ) {
            return null;
        }

        TestStepOption testStepOption = new TestStepOption();

        testStepOption.setId( testStepOptionRequestDTO.getId() );
        testStepOption.setOptionText( testStepOptionRequestDTO.getOptionText() );
        testStepOption.setIsCorrect( testStepOptionRequestDTO.getIsCorrect() );

        return testStepOption;
    }

    protected List<TestStepOption> testStepOptionRequestDTOListToTestStepOptionList(List<TestStepOptionRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<TestStepOption> list1 = new ArrayList<TestStepOption>( list.size() );
        for ( TestStepOptionRequestDTO testStepOptionRequestDTO : list ) {
            list1.add( testStepOptionRequestDTOToTestStepOption( testStepOptionRequestDTO ) );
        }

        return list1;
    }
}
