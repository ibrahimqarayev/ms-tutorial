package com.company.mspayment.controller;

import com.company.mspayment.model.request.PaymentRequest;
import com.company.mspayment.model.response.PaymentResponse;
import com.company.mspayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(@Valid @RequestBody PaymentRequest request) {
        paymentService.savePayment(request);
    }

    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/edit/{id}")
    public void update(@PathVariable Long id, @RequestBody PaymentRequest request) {
        paymentService.updatePayment(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaymentById(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
    }

}
