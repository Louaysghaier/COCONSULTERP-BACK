package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.JobOpport;
import com.test.COCONSULT.Interfaces.JobOpportServiceInterface;
import com.test.COCONSULT.Reposotories.JobOpportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class JobOpportServiceImp implements JobOpportServiceInterface {

    @Autowired
    JobOpportRepository jobOpportRepository;

    @Override
    public JobOpport createJobOpport(JobOpport jobOpport) {
        return jobOpportRepository.save(jobOpport);
    }

    @Override
    public JobOpport updateJobOpport(int id, JobOpport jobOpport) {
        if (jobOpportRepository.existsById(id)) {
            jobOpport.setId_offre(id);
            return jobOpportRepository.save(jobOpport);
        }
        return null;
    }

    @Override
    public void deleteJobOpport(int id) {
        jobOpportRepository.deleteById(id);
    }

    @Override
    public List<JobOpport> getAllJobOpports() {
        return jobOpportRepository.findAll();
    }

    @Override
    public JobOpport getJobOpportById(int id) {
        return jobOpportRepository.findById(id);
    }
}

