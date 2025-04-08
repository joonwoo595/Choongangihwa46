package com.wecompany.duoprojectv1.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {

    private Integer payId;
    private String paymentType;
    private LocalDateTime paidAt;

}
