package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseBookingResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseBooking;
import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;
import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.exception.*;
import com.korruptengu.gymcheckinsystem.mapper.CourseBookingMapper;
import com.korruptengu.gymcheckinsystem.repository.CourseBookingRepository;
import com.korruptengu.gymcheckinsystem.repository.CourseSessionRepository;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.service.CourseBookingService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.CourseBookingUpdateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseBookingServiceImpl implements CourseBookingService {
    private final CourseBookingRepository courseBookingRepository;
    private final CourseBookingMapper mapper;
    private final EntityFetcher fetcher;

    public List<CourseBookingResponse> getAllCourseBookings() {
        List<CourseBooking> allCourseBookings = courseBookingRepository.findAll();
        List<CourseBookingResponse> responseList = new ArrayList<>();
        for (CourseBooking courseBooking : allCourseBookings) {
            responseList.add(mapper.toResponse(courseBooking));
        }
        return responseList;
    }

    public CourseBookingResponse getCourseBookingById(CourseBookingId id){
        CourseBooking courseBooking = fetcher.fetchCourseBooking(id);
        return mapper.toResponse(courseBooking);
    }
    public CourseBookingResponse createCourseBooking(PostCourseBookingRequest request){
        if(request == null) throw new IllegalArgumentException("New data must not be null");

        CourseBooking created = mapper.postRequestToEntity(request);
        created.setMember(fetcher.fetchMember(request.memberId()));
        created.setCourseSession(fetcher.fetchCourseSession(request.courseSessionId()));

        courseBookingRepository.save(created);
        return mapper.toResponse(created);
    }
    public CourseBookingResponse deleteCourseBookingById(CourseBookingId id){
        CourseBooking deleted = fetcher.fetchCourseBooking(id);
        courseBookingRepository.delete(deleted);
        return mapper.toResponse(deleted);
    }
    public CourseBookingResponse updateCourseBookingCompletely(CourseBookingId id, PutCourseBookingRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must be not null");

        CourseBooking updatedData = mapper.putRequestToEntity(request);
        updatedData.setMember(fetcher.fetchMember(request.memberId()));
        updatedData.setCourseSession(fetcher.fetchCourseSession(request.courseSessionId()));

        CourseBooking existing = fetcher.fetchCourseBooking(id);

        CourseBookingUpdateHelper.updateCompletely(existing, updatedData);
        return mapper.toResponse(existing);
    }
    public CourseBookingResponse updateCourseBookingPartially(CourseBookingId id, PatchCourseBookingRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must be not null");

        CourseBooking updatedData = mapper.patchRequestToEntity(request);
        Member member = request.memberId() != null
                ? fetcher.fetchMember(request.memberId())
                : null;
        updatedData.setMember(member);
        CourseSession courseSession = request.courseSessionId() != null
                ? fetcher.fetchCourseSession(request.courseSessionId())
                : null;
        updatedData.setCourseSession(courseSession);
        if(CourseBookingUpdateHelper.isAllFieldsNull(updatedData)) throw new EmptyUpdateDataException();

        CourseBooking existing = fetcher.fetchCourseBooking(id);

        CourseBookingUpdateHelper.updatePartially(existing, updatedData);
        return mapper.toResponse(existing);
    }

}
