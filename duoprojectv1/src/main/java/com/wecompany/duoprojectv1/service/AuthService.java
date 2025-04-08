package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.domain.Admin;
import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.*;
import com.wecompany.duoprojectv1.mapper.AccountMapper;
import com.wecompany.duoprojectv1.mapper.AdminMapper;
import com.wecompany.duoprojectv1.mapper.InstructorMapper;
import com.wecompany.duoprojectv1.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountMapper accountRepository;
    private final AdminMapper adminRepository;
    private final StudentMapper studentRepository;
    private final InstructorMapper instructorRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인 처리 메소드
    public LoginResponseDto login(LoginRequestDto dto) {

        // 이메일로 계정 조회
        Account account = getAccountByEmail(dto.getEmail());

        // 비밀번호 검증
        verifyPassword(dto.getPasswd(), account.getPasswd());

        // 로그인 응답 데이터 빌드
        LoginResponseDto.LoginResponseDtoBuilder builder = LoginResponseDto.builder()
                .email(account.getEmail())
                .userType(account.getUserType());

        // 사용자 유형에 맞는 추가 정보 설정
        builder.userInfo(getUserInfoByType(account));

        return builder.build();
    }

    // 이메일로 계정 조회
    private Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 존재하지 않습니다."));
    }

    // 비밀번호 검증
    private void verifyPassword(String inputPassword, String storedPassword) {
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }

    // 사용자 유형에 맞는 정보 반환
    private UserInfoDto getUserInfoByType(Account account) {
        switch (account.getUserType()) {
            case "ADMIN":
                return getAdminInfo(account);
            case "STUDENT":
                return getStudentInfo(account);
            case "INSTRUCTOR":
                return getInstructorInfo(account);
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "지원하지 않는 사용자 유형입니다.");
        }
    }

    // ADMIN 유형에 맞는 정보 반환
    private AdminDto getAdminInfo(Account account) {
        Admin admin = adminRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "관리자 정보가 없습니다."));
        AdminDto adminDto = new AdminDto();
        adminDto.setName(admin.getAdminName());
        adminDto.setPhone(admin.getAdminPhone());
        return adminDto;
    }

    // STUDENT 유형에 맞는 정보 반환
    private StudentDto getStudentInfo(Account account) {
        Student student = studentRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "학생 정보가 없습니다."));
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getStName());
        studentDto.setDepartment(student.getStDepartment());
        studentDto.setYear(student.getStYear());
        return studentDto;
    }

    // INSTRUCTOR 유형에 맞는 정보 반환
    private InstructorDto getInstructorInfo(Account account) {
        Instructor instructor = instructorRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "교수 정보가 없습니다."));
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setName(instructor.getInsName());
        instructorDto.setDepartmentId(instructor.getDepId());
        instructorDto.setMajorId(instructor.getMajId());
        return instructorDto;
    }
}

