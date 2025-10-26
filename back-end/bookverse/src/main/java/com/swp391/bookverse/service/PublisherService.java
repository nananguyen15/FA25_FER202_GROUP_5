package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.PublisherCreationRequest;
import com.swp391.bookverse.dto.request.PublisherUpdateRequest;
import com.swp391.bookverse.dto.response.PublisherResponse;
import com.swp391.bookverse.entity.Publisher;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.mapper.PublisherMapper;
import com.swp391.bookverse.repository.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huangdat
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublisherService {
    PublisherRepository publisherRepository;
    PublisherMapper publisherMapper;

    public Publisher createPublisher(PublisherCreationRequest request) {
        // check if publisher with the same name already exists
        if (publisherRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PUBLISHER_EXISTS);
        }
        // map request to Publisher entity and save it to repository
        Publisher publisher = publisherMapper.toPublisher(request);
        return publisherRepository.save(publisher);
    }

    public List<PublisherResponse> getPublishers() {
        // throw exception if there are no publisher entity store in DB
        if (publisherRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_PUBLISHERS_STORED);
        }
        // map list of Publisher to list of PublisherResponse
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream()
                .map(publisherMapper::toPublisherResponse)
                .toList();
    }

    public PublisherResponse getPublisherById(String publisherId) {
        // check if publisher with the given id exists and map it to PublisherResponse
        Publisher publisher = publisherRepository.findById(Long.parseLong(publisherId))
                .orElseThrow(() -> new AppException(ErrorCode.PUBLISHER_NOT_FOUND));
        return publisherMapper.toPublisherResponse(publisher);
    }

    public PublisherResponse updatePublisher(Long publisherId, PublisherUpdateRequest request) {
        // check if publisher with the given id exists
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new AppException(ErrorCode.PUBLISHER_NOT_FOUND));
        // update publisher entity with the provided request data
        publisherMapper.updatePublisher(request, publisher);
        // save the updated publisher entity and map it to PublisherResponse
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return publisherMapper.toPublisherResponse(updatedPublisher);
    }
}
