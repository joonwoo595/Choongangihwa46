package com.wecompany.duoprojectv1.strategy;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.StudentDto;
import com.wecompany.duoprojectv1.dto.UserInfoDto;
import com.wecompany.duoprojectv1.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component("STUDENT")
@RequiredArgsConstructor
public class StudentLoginHandler implements LoginHandlerStrategy {
    private final StudentMapper studentMapper;

    @Override
    public UserInfoDto buildUserInfo(Account account) {
        Student student = studentMapper.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "학생 정보가 없습니다."));
        StudentDto dto = new StudentDto();
        dto.setName(student.getStName());
        dto.setDepartment(student.getStDepartment());
        dto.setYear(student.getStYear());
        dto.setType("STUDENT");
        return dto;
    }
}
