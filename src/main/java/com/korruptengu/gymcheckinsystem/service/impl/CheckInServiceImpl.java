package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PatchCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PostCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PutCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CheckInResponse;
import com.korruptengu.gymcheckinsystem.entity.CheckIn;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.exception.CheckInNotFoundException;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.mapper.CheckInMapper;
import com.korruptengu.gymcheckinsystem.repository.CheckInRepository;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.service.CheckInService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.CheckInUpdateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final CheckInMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<CheckInResponse> getAllCheckIns(){
        List<CheckIn> allCheckIns = checkInRepository.findAll();
        List<CheckInResponse> responseList = new ArrayList<CheckInResponse>();
        for (CheckIn checkIn : allCheckIns){
            responseList.add(mapper.toResponse(checkIn));
        }
        return responseList;
    }

    @Override
    public CheckInResponse getCheckInById(Long id){
        CheckIn checkIn = fetcher.fetchCheckIn(id);
        return mapper.toResponse(checkIn);
    }

    @Override
    public CheckInResponse createCheckIn(PostCheckInRequest request){
        if(request == null) throw new IllegalArgumentException("New data must not be null");
        CheckIn created = mapper.postRequestToEntity(request);
        created.setMember(fetcher.fetchMember(request.memberId()));
        checkInRepository.save(created);
        return mapper.toResponse(created);
    }

    @Override
    public CheckInResponse deleteCheckInById(Long id){
        CheckIn checkIn = fetcher.fetchCheckIn(id);
        checkInRepository.delete(checkIn);
        return mapper.toResponse(checkIn);
    }

    @Override
    public CheckInResponse updateCheckInCompletely(Long id, PutCheckInRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must be not null");

        CheckIn existing = fetcher.fetchCheckIn(id);
        Member member = fetcher.fetchMember(request.memberId());
        CheckIn updateData = mapper.putRequestToEntity(request);
        updateData.setMember(member);

        CheckInUpdateHelper.updateCompletely(existing, updateData);
        checkInRepository.save(existing);

        return mapper.toResponse(existing);
    }

    @Override
    public CheckInResponse updateCheckInPartially(Long id, PatchCheckInRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must be not null");

        CheckIn existing = fetcher.fetchCheckIn(id);

        Member member = request.memberId() != null
                ? fetcher.fetchMember(request.memberId())
                : null;

        CheckIn updateData = mapper.patchRequestToEntity(request);
        updateData.setMember(member);
        if (CheckInUpdateHelper.isAllFieldsNull(mapper.patchRequestToEntity(request)))
            throw new EmptyUpdateDataException();

        CheckInUpdateHelper.updatePartially(existing, updateData);
        checkInRepository.save(existing);

        return mapper.toResponse(existing);

    }
}
