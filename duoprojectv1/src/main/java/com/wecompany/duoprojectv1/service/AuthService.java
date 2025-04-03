package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Account;
import com.wecompany.duoprojectv1.domain.Admin;
import com.wecompany.duoprojectv1.domain.Instructor;
import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.*;
import com.wecompany.duoprojectv1.repository.AccountRepository;
import com.wecompany.duoprojectv1.repository.AdminRepository;
import com.wecompany.duoprojectv1.repository.InstructorRepository;
import com.wecompany.duoprojectv1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(dto.getEmail());
        Account account = optionalAccount.orElseThrow(() ->
            new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(dto.getPasswd(), account.getPasswd())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        LoginResponseDto.LoginResponseDtoBuilder builder = LoginResponseDto.builder()
                .email(account.getEmail())
                .userType(account.getUserType()); // 수정된 부분: userType이 String이므로 name() 제거

        if (account.getUserType().equals("ADMIN")) {
            Admin admin = adminRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "관리자 정보가 없습니다."));
            AdminDto adminDto = new AdminDto();
            adminDto.setName(admin.getAdminName());
            adminDto.setPhone(admin.getAdminPhone());
            builder.userInfo(adminDto);
        } else if (account.getUserType().equals("STUDENT")) {
            Student student = studentRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "학생 정보가 없습니다."));
            StudentDto studentDto = new StudentDto();
            studentDto.setName(student.getStName());
            studentDto.setDepartment(student.getStDepartment());
            studentDto.setYear(student.getStYear());
            builder.userInfo(studentDto);
        } else if (account.getUserType().equals("INSTRUCTOR")) {
            Instructor instructor = instructorRepository.findByAccId(account.getAccId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "교수 정보가 없습니다."));
            InstructorDto instructorDto = new InstructorDto();
            instructorDto.setName(instructor.getInsName());
            instructorDto.setDepartmentId(instructor.getDepId());
            instructorDto.setMajorId(instructor.getMajId());
            builder.userInfo(instructorDto);
        }

        return builder.build();
    }
}
