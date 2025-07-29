package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.courseType.PatchCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PostCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PutCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseTypeResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.mapper.CourseTypeMapper;
import com.korruptengu.gymcheckinsystem.repository.CourseTypeRepository;
import com.korruptengu.gymcheckinsystem.service.CourseTypeService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.CourseTypeUpdateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseTypeServiceImpl implements CourseTypeService {
    private final CourseTypeRepository repository;
    private final CourseTypeMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<CourseTypeResponse> getAllCourseTypes() {
        List<CourseType> allCourseTypes = repository.findAll();
        List<CourseTypeResponse> responseList = new ArrayList<>();
        for (CourseType courseType : allCourseTypes) {
            responseList.add(mapper.toResponse(courseType));
        }
        return responseList;
    }

    @Override
    public CourseTypeResponse getCourseTypeById(Long id){
        CourseType courseType = fetcher.fetchCourseTypeById(id);
        return mapper.toResponse(courseType);
    }

    @Override
    public CourseTypeResponse createCourseType(PostCourseTypeRequest request) {
        if (request == null) throw new IllegalArgumentException("New data must not be null");
        return mapper.toResponse(repository.save(mapper.postRequestToEntity(request)));
    }

    @Override
    public CourseTypeResponse deleteCourseTypeById(Long id){
        CourseType deleted = fetcher.fetchCourseTypeById(id);
        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public CourseTypeResponse updateCourseTypeCompletely(Long id, PutCourseTypeRequest request) {
        if (request == null) throw new IllegalArgumentException("Update data must not be null");
        CourseType existing = fetcher.fetchCourseTypeById(id);
        CourseTypeUpdateHelper.updateCompletely(existing, mapper.putRequestToEntity(request));
        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public CourseTypeResponse updateCourseTypePartially(Long id, PatchCourseTypeRequest request) {
        if (request == null) throw new IllegalArgumentException("Update data must not be null");
        CourseType updateData = mapper.patchRequestToEntity(request);
        if (CourseTypeUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        CourseType existing = fetcher.fetchCourseTypeById(id);
        CourseTypeUpdateHelper.updatePartially(existing, updateData);
        return mapper.toResponse(repository.save(existing));
    }
}

