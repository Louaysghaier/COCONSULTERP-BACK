package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.ContractVerificationResult;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Interfaces.ContractService;
import com.test.COCONSULT.Interfaces.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequestMapping("Payment")
@RestController
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService ;

    private final ContractService contractService ;
    @GetMapping("/GetAllPayment")
    public ResponseEntity<List<Payment>> retrievePayment() {
        List<Payment> PaymentList = paymentService.retrievePayment();
        return ResponseEntity.ok(PaymentList);
    }

    @GetMapping("/GetPaymentByID/{id}")
    public ResponseEntity<Payment> retrievePayment(@PathVariable("id") Long idPayment ) {
        Payment payment =  paymentService.retrievePayment(idPayment);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ajouterPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);

    }

    @PutMapping("/updatePayment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") Long idPayment, @RequestBody Payment payment) {
        payment.setIdPayment(idPayment); // Set the ID from the path variable
        Payment updatedPayment = paymentService.updatePayment(payment);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/RemovePayment/{id}")
    public ResponseEntity<Void> removePayment(@PathVariable("id") Long idPayment) {
        paymentService.removePayment(idPayment);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload-payments-data")
    public ResponseEntity<?> uploadPaymentsData(@RequestParam("file") MultipartFile file) {
        paymentService.savePaymentsToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "Payments data uploaded and saved to database successfully"));
    }

    @GetMapping("/verifyPayments")
    public List<ContractVerificationResult> verifyPayments() {
        List<Contract> contracts = contractService.retrieveContract();
        List<Payment> payments = paymentService.retrievePayment();
        return paymentService.verifyPayments(contracts, payments);
    }
}
