package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> retrievePayment();
    Payment updatePayment(Payment payment);
    Payment addPayment(Payment payment);
    Payment retrievePayment(Long idPayment);

    void removePayment(Long idPayment);
}
