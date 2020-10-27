package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.TestStepOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T20:05:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class TestStepMapperImpl implements TestStepMapper {

    @Override
    public TestStepDTO toDto(TestStep testStep) {
        if ( testStep == null ) {
            return null;
        }

        TestStepDTO testStepDTO = new TestStepDTO();

        testStepDTO.setAnswers( testStepOptionCollectionToTestStepOptionDTOCollection( testStep.getAnswers() ) );
        testStepDTO.setScore( testStep.getScore() );
        testStepDTO.setDescriptionUri( testStep.getDescriptionUri() );
        testStepDTO.setPosition( testStep.getPosition() );
        testStepDTO.setId( testStep.getId() );

        return testStepDTO;
    }

    @Override
    public TestStep toEntity(TestStepDTO testStepDTO) {
        if ( testStepDTO == null ) {
            return null;
        }

        TestStep testStep = new TestStep();

        testStep.setAnswers( testStepOptionDTOCollectionToTestStepOptionCollection( testStepDTO.getAnswers() ) );
        testStep.setScore( testStepDTO.getScore() );
        testStep.setDescriptionUri( testStepDTO.getDescriptionUri() );
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

    protected TestStepOptionDTO testStepOptionToTestStepOptionDTO(TestStepOption testStepOption) {
        if ( testStepOption == null ) {
            return null;
        }

        TestStepOptionDTO testStepOptionDTO = new TestStepOptionDTO();

        testStepOptionDTO.setId( testStepOption.getId() );
        testStepOptionDTO.setOptionText( testStepOption.getOptionText() );

        return testStepOptionDTO;
    }

    protected Collection<TestStepOptionDTO> testStepOptionCollectionToTestStepOptionDTOCollection(Collection<TestStepOption> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<TestStepOptionDTO> collection1 = new ArrayList<TestStepOptionDTO>( collection.size() );
        for ( TestStepOption testStepOption : collection ) {
            collection1.add( testStepOptionToTestStepOptionDTO( testStepOption ) );
        }

        return collection1;
    }

    protected TestStepOption testStepOptionDTOToTestStepOption(TestStepOptionDTO testStepOptionDTO) {
        if ( testStepOptionDTO == null ) {
            return null;
        }

        TestStepOption testStepOption = new TestStepOption();

        testStepOption.setId( testStepOptionDTO.getId() );
        testStepOption.setOptionText( testStepOptionDTO.getOptionText() );

        return testStepOption;
    }

    protected Collection<TestStepOption> testStepOptionDTOCollectionToTestStepOptionCollection(Collection<TestStepOptionDTO> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<TestStepOption> collection1 = new ArrayList<TestStepOption>( collection.size() );
        for ( TestStepOptionDTO testStepOptionDTO : collection ) {
            collection1.add( testStepOptionDTOToTestStepOption( testStepOptionDTO ) );
        }

        return collection1;
    }
}
