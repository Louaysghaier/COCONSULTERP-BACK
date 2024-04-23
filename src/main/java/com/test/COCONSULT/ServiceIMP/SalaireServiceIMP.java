package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.Salaire;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.SalaireServiceInterface;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.SalaireRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SalaireServiceIMP implements SalaireServiceInterface {

    @Autowired
    private SalaireRepository salaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void addSalaire(Salaire salaire) {
        // Save the Salaire entity
        salaire.setDate(Date.from(new Date().toInstant()));
        salaireRepository.save(salaire);

        // Retrieve the User entity
        User user = userRepository.findById(salaire.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve the existing Set of Salaires from the User entity
        Set<Salaire> setSalaires = user.getSalaires();

        // If the setSalaires is null (which could happen if the user object has not been initialized),
        // initialize it with a new HashSet
        if (setSalaires == null) {
            setSalaires = new HashSet<>();
        }

        // Add the new Salaire to the setSalaires
        setSalaires.add(salaire);

        // Set the modified setSalaires back to the User entity
        user.setSalaires(setSalaires);

        // Save the updated User entity
        userRepository.save(user);
    }

    @Override
    public void deleteSalaire(int idSalaire) {
        Salaire salaire = salaireRepository.findById(idSalaire).get();
        User user = userRepository.findById(salaire.getUser().getId()).get();
        user.getSalaires().remove(salaire);
        userRepository.save(user);
        salaireRepository.deleteById(idSalaire);
    }

    @Override
    public Salaire getSalaireById(int idSalaire) {
        return salaireRepository.findById(idSalaire).get();
    }

    @Override
    public List<Salaire> getSalaireByUser(Long idUser) {
        return salaireRepository.findByUser_Id(idUser);
    }

    public List<Salaire> get(){
        return salaireRepository.findAll();
    }
    @Override
    public Salaire updateSalaire(Salaire salaire) {
        return salaireRepository.save(salaire);
    }

    //add salaire to user based on role and check exp is junior or senior
    public Salaire addSalaireToUser(){
        Salaire salaire = new Salaire();
        salaire.setImpot(30);
        salaire.setCurrency("TND");
        salaire.setDate(Date.from(new Date().toInstant()));
        Role role = roleRepository.findByName(RoleName.valueOf("Employee"))
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        List<User> users = userRepository.findByRolesContains(role);

        for(User user : users){
            Set<Salaire> setSalaires = user.getSalaires();
                salaire.setUser(user);
                if(user.getExp().equals("Junior")){
                    salaire.setSalaire(1500);
                }else if(user.getExp().equals("Senior")){
                    salaire.setSalaire(2500);
                }
                setSalaires.add(salaire);
                user.setSalaires(setSalaires);
                userRepository.save(user);
                salaireRepository.save(salaire);
        }
        return salaire;
    }
}
