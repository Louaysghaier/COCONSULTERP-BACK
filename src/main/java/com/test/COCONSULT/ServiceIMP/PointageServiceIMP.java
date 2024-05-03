package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Pointage;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.PointageServiceInterface;
import com.test.COCONSULT.Reposotories.PointageRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class PointageServiceIMP implements PointageServiceInterface {

    @Autowired
    private PointageRepository pointageRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addPointage(Pointage pointage) {

        pointage.setDateEntr(Date.from(new Date().toInstant()));
        pointageRepository.save(pointage);
        // Retrieve the User entity
        User user = userRepository.findById(pointage.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Pointage> setPointages = user.getPointages();

        if (setPointages == null) {
            setPointages = new HashSet<>();
        }

        setPointages.add(pointage);

        user.setPointages(setPointages);

        userRepository.save(user);


    }

    @Override
    public void deletePointage(int idPointage) {
        pointageRepository.deleteById(idPointage);
    }

    @Override
    public Pointage updatePointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    @Override
    public Pointage getPointageById(int idPoint) {
        return pointageRepository.findById(idPoint).get();
    }
}
