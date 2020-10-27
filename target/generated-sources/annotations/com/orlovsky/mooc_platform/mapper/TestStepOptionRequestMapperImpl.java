package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
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
public class TestStepOptionRequestMapperImpl implements TestStepOptionRequestMapper {

    @Override
    public TestStepOptionRequestDTO toDto(TestStepOption testStepOption) {
        if ( testStepOption == null ) {
            return null;
        }

        TestStepOptionRequestDTO testStepOptionRequestDTO = new TestStepOptionRequestDTO();

        testStepOptionRequestDTO.setId( testStepOption.getId() );
        testStepOptionRequestDTO.setCorrect( testStepOption.isCorrect() );
        testStepOptionRequestDTO.setOptionText( testStepOption.getOptionText() );

        return testStepOptionRequestDTO;
    }

    @Override
    public TestStepOption toEntity(TestStepOptionRequestDTO testStepOptionRequestDTO) {
        if ( testStepOptionRequestDTO == null ) {
            return null;
        }

        TestStepOption testStepOption = new TestStepOption();

        testStepOption.setId( testStepOptionRequestDTO.getId() );
        testStepOption.setCorrect( testStepOptionRequestDTO.isCorrect() );
        testStepOption.setOptionText( testStepOptionRequestDTO.getOptionText() );

        return testStepOption;
    }

    @Override
    public Collection<TestStepOption> toEntities(List<TestStepOptionRequestDTO> testStepOptionDTOs) {
        if ( testStepOptionDTOs == null ) {
            return null;
        }

        Collection<TestStepOption> collection = new ArrayList<TestStepOption>( testStepOptionDTOs.size() );
        for ( TestStepOptionRequestDTO testStepOptionRequestDTO : testStepOptionDTOs ) {
            collection.add( toEntity( testStepOptionRequestDTO ) );
        }

        return collection;
    }

    @Override
    public List<TestStepOptionRequestDTO> toDtos(Collection<TestStepOption> testStepOptions) {
        if ( testStepOptions == null ) {
            return null;
        }

        List<TestStepOptionRequestDTO> list = new ArrayList<TestStepOptionRequestDTO>( testStepOptions.size() );
        for ( TestStepOption testStepOption : testStepOptions ) {
            list.add( toDto( testStepOption ) );
        }

        return list;
    }
}
