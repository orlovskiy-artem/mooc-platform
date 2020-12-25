package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
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
public class TestStepOptionMapperImpl implements TestStepOptionMapper {

    @Override
    public TestStepOptionDTO toDto(TestStepOption testStepOption) {
        if ( testStepOption == null ) {
            return null;
        }

        TestStepOptionDTO testStepOptionDTO = new TestStepOptionDTO();

        testStepOptionDTO.setOptionText( testStepOption.getOptionText() );
        testStepOptionDTO.setId( testStepOption.getId() );

        return testStepOptionDTO;
    }

    @Override
    public TestStepOption toEntity(TestStepOptionDTO testStepOptionDTO) {
        if ( testStepOptionDTO == null ) {
            return null;
        }

        TestStepOption testStepOption = new TestStepOption();

        testStepOption.setOptionText( testStepOptionDTO.getOptionText() );
        testStepOption.setId( testStepOptionDTO.getId() );

        return testStepOption;
    }

    @Override
    public Collection<TestStepOption> toEntities(List<TestStepOptionDTO> testStepOptionDTOs) {
        if ( testStepOptionDTOs == null ) {
            return null;
        }

        Collection<TestStepOption> collection = new ArrayList<TestStepOption>( testStepOptionDTOs.size() );
        for ( TestStepOptionDTO testStepOptionDTO : testStepOptionDTOs ) {
            collection.add( toEntity( testStepOptionDTO ) );
        }

        return collection;
    }

    @Override
    public List<TestStepOptionDTO> toDtos(Collection<TestStepOption> testStepOptions) {
        if ( testStepOptions == null ) {
            return null;
        }

        List<TestStepOptionDTO> list = new ArrayList<TestStepOptionDTO>( testStepOptions.size() );
        for ( TestStepOption testStepOption : testStepOptions ) {
            list.add( toDto( testStepOption ) );
        }

        return list;
    }
}
