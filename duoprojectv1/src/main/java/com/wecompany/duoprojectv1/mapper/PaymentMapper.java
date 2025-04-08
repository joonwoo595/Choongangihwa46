package com.wecompany.duoprojectv1.mapper;

import com.wecompany.duoprojectv1.domain.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PaymentMapper {
    /**
     * 가상 결제 등록
     * payment_type : "VIRTUAL" 고정 사용
     */

   @Insert("INSERT INTO payment (payment_type) VALUES (#{paymentType})")
@Options(useGeneratedKeys = true, keyProperty = "payId", keyColumn = "pay_id")
void insertPayment(Payment payment);




}
