package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Payment;
import com.wecompany.duoprojectv1.domain.Student;
import com.wecompany.duoprojectv1.dto.EnrollRequestDto;
import com.wecompany.duoprojectv1.dto.EnrollResponseDto;
import com.wecompany.duoprojectv1.mapper.EnrollmentMapper;
import com.wecompany.duoprojectv1.mapper.PaymentMapper;
import com.wecompany.duoprojectv1.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;
    private final PaymentMapper paymentMapper;
    private final PaymentService paymentService;

    /**
     * 수강 신청 처리
     */
    public void registerEnrollment(EnrollRequestDto dto) {
        // 0. 학생 존재 여부 확인
        Student student = studentMapper.findById(dto.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 학생은 존재하지 않습니다."));

        // 1. 중복 수강 신청 여부 확인
        int duplicateCount = enrollmentMapper.countByStudentIdAndLectureId(dto.getStudentId(), dto.getLectureId());
        if (duplicateCount > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 수강 신청한 강의입니다.");
        }

        // 2. 강의 가격 조회
        Integer price = enrollmentMapper.getLecturePrice(dto.getLectureId());
        if (price == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 강의는 존재하지 않습니다.");
        }

        // 💡 2-A. 현재 수강 인원
        int currentCount = enrollmentMapper.countEnrollmentsByLectureId(dto.getLectureId());

// 💡 2-B. 최대 수강 인원
        int maxCapacity = enrollmentMapper.getLectureMaxCapacityWithLock(dto.getLectureId());


// 💡 2-C. 정원 초과 여부 확인
        if (currentCount >= maxCapacity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 강의는 수강 정원이 마감되었습니다.");
        }


        // 3. 가상 결제 생성
        Payment payment = paymentService.createVirtualPayment();

        if (payment.getPayId() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "결제 ID 생성에 실패했습니다.");
        }

        // 4. 수강 등록
        int rowsInserted = enrollmentMapper.insertEnrollment(
                dto.getStudentId(),
                dto.getLectureId(),
                price,
                payment.getPayId()
        );

        if (rowsInserted == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "수강 신청 등록에 실패했습니다.");
        }
    }

    public List<EnrollResponseDto> getEnrollments(int studentId) {
        return enrollmentMapper.findEnrollmentsByStudentId(studentId);
    }

    public void cancelEnrollment(int enrollmentId) {
        int deleted = enrollmentMapper.deleteEnrollment(enrollmentId);
        if (deleted == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 수강 신청 내역이 존재하지 않습니다.");

        }
    }
}
