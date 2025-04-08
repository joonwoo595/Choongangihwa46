package com.wecompany.duoprojectv1.service;

import com.wecompany.duoprojectv1.domain.Payment;
import com.wecompany.duoprojectv1.dto.PaymentResponseDto;
import com.wecompany.duoprojectv1.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;

    /**
     * 가상 결제 생성 메서드
     */
    public Payment createVirtualPayment() {
        Payment payment = new Payment();
        payment.setPaymentType("Virtual");
        // paidAt는 DB의 current_timestamp() 활용 (혹은 여기서 LocalDateTime.now()로 할당)
        paymentMapper.insertPayment(payment);

        return payment;
    }

    // 💳 학생별 결제 내역 조회
    public List<PaymentResponseDto> getPaymentsByStudentId(int studentId) {
        return paymentMapper.findPaymentsByStudentId(studentId);
    }
}
