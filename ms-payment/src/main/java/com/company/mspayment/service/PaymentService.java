package com.company.mspayment.service;

import com.company.mspayment.client.CountryClient;
import com.company.mspayment.entity.Payment;
import com.company.mspayment.exception.NotFoundException;
import com.company.mspayment.mapper.PaymentMapper;
import com.company.mspayment.model.request.PaymentRequest;
import com.company.mspayment.model.response.PaymentResponse;
import com.company.mspayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.mspayment.mapper.PaymentMapper.mapEntityToResponse;
import static com.company.mspayment.mapper.PaymentMapper.mapRequestToEntity;
import static com.company.mspayment.model.constant.ExceptionConstants.COUNTRY_NOT_FOUND_CODE;
import static com.company.mspayment.model.constant.ExceptionConstants.COUNTRY_NOT_FOUND_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CountryClient countryClient;

    public void savePayment(PaymentRequest request) {
        log.info("savePayment.start");
        countryClient.getAvailableCountries(request.getCurrency()).stream()
                .filter(countryDto -> countryDto.getRemainingLimit().compareTo(request.getAmount()) > 0)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(COUNTRY_NOT_FOUND_MESSAGE, request.getAmount(),
                        request.getCurrency()), COUNTRY_NOT_FOUND_CODE));
        paymentRepository.save(mapRequestToEntity(request));
        log.info("savePayment.success");
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream().map(PaymentMapper::mapEntityToResponse).collect(Collectors.toList());
    }

    public PaymentResponse getPaymentById(Long id) {
        log.info("getPayment.start id:{}", id);
        Payment payment = fetchPaymentIfExist(id);
        log.info("getPayment.success id:{}", id);
        return mapEntityToResponse(payment);
    }

    public void updatePayment(Long id, PaymentRequest request) {
        log.info("updatePayment.start id:{}", id);
        Payment payment = fetchPaymentIfExist(id);
        payment.setDescription(request.getDescription());
        payment.setAmount(request.getAmount());
        paymentRepository.save(payment);
        log.info("updatePayment.success id:{}", id);
    }


    public void deletePaymentById(Long id) {
        log.info("deletePaymentById.start id:{}", id);
        paymentRepository.deleteById(id);
        log.info("deletePaymentById.success id:{}", id);
    }

    private Payment fetchPaymentIfExist(Long id) {
        return paymentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
