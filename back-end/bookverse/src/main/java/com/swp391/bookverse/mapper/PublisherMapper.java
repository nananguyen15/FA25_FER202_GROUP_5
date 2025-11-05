package com.swp391.bookverse.mapper;

import com.swp391.bookverse.dto.request.PublisherCreationRequest;
import com.swp391.bookverse.dto.request.PublisherUpdateRequest;
import com.swp391.bookverse.dto.response.PublisherResponse;
import com.swp391.bookverse.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @Publisher huangdat
 */
@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher toPublisher(PublisherCreationRequest request);

    PublisherResponse toPublisherResponse(Publisher publisher);

    void updatePublisher(PublisherUpdateRequest request, @MappingTarget Publisher Publisher);
}
