package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;
import com.test.COCONSULT.Interfaces.PaymentService;
import com.test.COCONSULT.Reposotories.PaymentRepository;
import com.test.COCONSULT.Services.ExcelPayementUploadServices;
import com.test.COCONSULT.Services.ExcelProspectUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository ;

    // ExcelPUploadService ExcelPayementUploadServices;
    @Override
    public List<Payment> retrievePayment() {
        return paymentRepository.findAll() ;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment retrievePayment(Long idPayment) {
        Optional<Payment> PaymentOptional = paymentRepository.findById(idPayment);
        return PaymentOptional.orElse(null);
    }

    @Override
    public void removePayment(Long idPayment) {
        paymentRepository.deleteById(idPayment);
    }

    @Override
    public void savePaymentsToDatabase(MultipartFile file){
        if(ExcelProspectUploadService.isValidExcelFile(file)){
            try {

                List<Payment> payement = ExcelPayementUploadServices.getPayementsDataFromExcel(file.getInputStream());
                this.paymentRepository.saveAll(payement);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
