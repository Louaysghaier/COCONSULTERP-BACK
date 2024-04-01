package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.TimeRecord;
import com.test.COCONSULT.Interfaces.TimeRecordService;
import com.test.COCONSULT.Reposotories.TimeRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TimeRecordServiceImpl implements TimeRecordService {
   private final TimeRecordRepository timeRecordRepository;

   @Override
   public List<TimeRecord> retrieveTimeRec() {
      return timeRecordRepository.findAll();
   }

   @Override
   public TimeRecord updateTimeRec(TimeRecord timeRecord) {
      return timeRecordRepository.save(timeRecord);
   }

   @Override
   public TimeRecord ajouterTimeRec(TimeRecord timeRecord) {
      return timeRecordRepository.save(timeRecord);
   }

   @Override
   public TimeRecord retrieveTimeRecord(Long idTimeRec) {
      return timeRecordRepository.findById(idTimeRec).orElse(null);
   }

   @Override
   public void removeTimeRec(Long idTimeRec) {
      timeRecordRepository.deleteById(idTimeRec);


   }
}