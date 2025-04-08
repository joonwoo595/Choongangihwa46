package com.wecompany.duoprojectv1.controller;

import com.wecompany.duoprojectv1.dto.PaymentResponseDto;
import com.wecompany.duoprojectv1.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getPayments(@RequestParam int studentId) {
        List<PaymentResponseDto> payments = paymentService.getPaymentsByStudentId(studentId);
        return ResponseEntity.ok(payments);
    }
}

