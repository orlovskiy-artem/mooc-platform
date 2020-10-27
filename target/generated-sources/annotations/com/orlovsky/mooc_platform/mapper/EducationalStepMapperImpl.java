package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.EducationalStepDTO;
import com.orlovsky.mooc_platform.model.EducationalStep;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T20:05:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class EducationalStepMapperImpl implements EducationalStepMapper {

    @Override
    public EducationalStepDTO toDto(EducationalStep educationalStep) {
        if ( educationalStep == null ) {
            return null;
        }

        EducationalStepDTO educationalStepDTO = new EducationalStepDTO();

        educationalStepDTO.setEduMaterialUri( educationalStep.getEduMaterialUri() );
        educationalStepDTO.setPosition( educationalStep.getPosition() );
        educationalStepDTO.setId( educationalStep.getId() );

        return educationalStepDTO;
    }

    @Override
    public EducationalStep toEntity(EducationalStepDTO educationalStepDTO) {
        if ( educationalStepDTO == null ) {
            return null;
        }

        EducationalStep educationalStep = new EducationalStep();

        educationalStep.setEduMaterialUri( educationalStepDTO.getEduMaterialUri() );
        educationalStep.setPosition( educationalStepDTO.getPosition() );
        educationalStep.setId( educationalStepDTO.getId() );

        return educationalStep;
    }

    @Override
    public List<EducationalStepDTO> toDtos(Collection<EducationalStep> educationalStepList) {
        if ( educationalStepList == null ) {
            return null;
        }

        List<EducationalStepDTO> list = new ArrayList<EducationalStepDTO>( educationalStepList.size() );
        for ( EducationalStep educationalStep : educationalStepList ) {
            list.add( toDto( educationalStep ) );
        }

        return list;
    }

    @Override
    public Collection<EducationalStep> toEntities(List<EducationalStepDTO> educationalStepDTOList) {
        if ( educationalStepDTOList == null ) {
            return null;
        }

        Collection<EducationalStep> collection = new ArrayList<EducationalStep>( educationalStepDTOList.size() );
        for ( EducationalStepDTO educationalStepDTO : educationalStepDTOList ) {
            collection.add( toEntity( educationalStepDTO ) );
        }

        return collection;
    }
}
