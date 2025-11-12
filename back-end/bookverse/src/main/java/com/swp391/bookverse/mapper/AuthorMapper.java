package com.swp391.bookverse.mapper;

import com.swp391.bookverse.dto.request.AuthorCreationRequest;
import com.swp391.bookverse.dto.request.AuthorUpdateRequest;
import com.swp391.bookverse.dto.response.AuthorResponse;
import com.swp391.bookverse.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @Author huangdat
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);

    AuthorResponse toAuthorResponse(Author author);

    void updateAuthor(AuthorUpdateRequest request, @MappingTarget Author author);
}
