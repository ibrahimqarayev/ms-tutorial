package com.company.mspayment.service.specification;

import com.company.mspayment.entity.Payment;
import com.company.mspayment.model.request.PaymentCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class PaymentSpecification implements Specification<Payment> {

    private final PaymentCriteria paymentCriteria;

    private static final String AMOUNT="amount";
    private static final String DESCRIPTION="description";

    @Override
    public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
