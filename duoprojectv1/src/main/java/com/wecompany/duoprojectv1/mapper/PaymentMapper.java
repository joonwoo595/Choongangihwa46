package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Payment;
import com.wecompany.duoprojectv1.dto.PaymentResponseDto;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PaymentMapper {
    /**
     * 가상 결제 등록
     * payment_type : "VIRTUAL" 고정 사용
     */

   @Insert("INSERT INTO payment (payment_type) VALUES (#{paymentType})")
@Options(useGeneratedKeys = true, keyProperty = "payId", keyColumn = "pay_id")
void insertPayment(Payment payment);

 // 💳 학생 ID로 결제 내역 조회
    @Select("""
        SELECT 
            l.cos_name AS lectureName,
            e.payment AS amount,
            p.paid_at AS paidAt
        FROM payment p
        JOIN enrollment e ON p.pay_id = e.pay_id
        JOIN lecture l ON e.cos_id = l.cos_id
        WHERE e.st_num = #{studentId}
    """)
    List<PaymentResponseDto> findPaymentsByStudentId(@Param("studentId") int studentId);

}
