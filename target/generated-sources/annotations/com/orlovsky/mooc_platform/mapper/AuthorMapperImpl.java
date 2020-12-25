package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.AuthorDTO;
import com.orlovsky.mooc_platform.model.Author;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-10T00:19:40+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO toDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setFirstName( author.getFirstName() );
        authorDTO.setLastName( author.getLastName() );
        authorDTO.setDescription( author.getDescription() );
        authorDTO.setId( author.getId() );

        return authorDTO;
    }

    @Override
    public Author toEntity(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setFirstName( authorDTO.getFirstName() );
        author.setLastName( authorDTO.getLastName() );
        author.setDescription( authorDTO.getDescription() );
        author.setId( authorDTO.getId() );

        return author;
    }

    @Override
    public List<AuthorDTO> toDtos(List<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        List<AuthorDTO> list = new ArrayList<AuthorDTO>( authors.size() );
        for ( Author author : authors ) {
            list.add( toDto( author ) );
        }

        return list;
    }

    @Override
    public List<Author> toEntities(List<AuthorDTO> authorDTOs) {
        if ( authorDTOs == null ) {
            return null;
        }

        List<Author> list = new ArrayList<Author>( authorDTOs.size() );
        for ( AuthorDTO authorDTO : authorDTOs ) {
            list.add( toEntity( authorDTO ) );
        }

        return list;
    }
}
