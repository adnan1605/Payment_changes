package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymententity.Payments;


@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long>{

}
