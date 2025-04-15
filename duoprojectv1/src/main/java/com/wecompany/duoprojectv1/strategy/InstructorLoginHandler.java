package com.wecompany.duoprojectv1.strategy;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.dto.InstructorDto;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import com.wecompany.duoprojectv1.mapper.InstructorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component("INSTRUCTOR")
@RequiredArgsConstructor
public class InstructorLoginHandler implements LoginHandlerStrategy {
    private final InstructorMapper instructorMapper;

    @Override
    public UserInfoDto buildUserInfo(Account account) {
        Instructor instructor = instructorMapper.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "교수 정보가 없습니다."));
        InstructorDto dto = new InstructorDto();
        dto.setName(instructor.getInsName());
        dto.setDepartmentId(instructor.getDepId());
        dto.setMajorId(instructor.getMajId());
        dto.setType("INSTRUCTOR"); // ✅ 제거해도 됨 (getType()으로 자동 처리됨)
        return dto;
    }
}


