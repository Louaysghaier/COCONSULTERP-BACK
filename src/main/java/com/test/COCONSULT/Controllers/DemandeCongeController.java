package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.TypeConge;
import com.test.COCONSULT.Entity.Conge;
import com.test.COCONSULT.Entity.DemandeConge;
import com.test.COCONSULT.Entity.Salaire;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.DemandeCongeService;
import com.test.COCONSULT.Reposotories.CongeRepository;
import com.test.COCONSULT.Reposotories.DemandeCongeRepository;
import com.test.COCONSULT.Reposotories.SalaireRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/demande-conge")
public class DemandeCongeController {
    @Autowired
    DemandeCongeService demandeCongeService;

    @Autowired
    DemandeCongeRepository demandeCongeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CongeRepository congeRepository;

    @Autowired
    SalaireRepository salaireRepository;

    @PostMapping("/add-demande-conge")
    public void addDemandeConge(
           @ModelAttribute DemandeConge demandeConge,
              @RequestParam(value = "certificateFile" , required = false) MultipartFile file
    ) {
        try {
            if (file != null && !file.isEmpty()) {
                demandeConge.setCertificate(file.getBytes());
                demandeConge.setCertificateFileName(file.getOriginalFilename());
                demandeConge.setCertificateContentType(file.getContentType());
            }



            demandeCongeService.addDemandeConge(demandeConge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/demande-conge/{id}/certificate")
    public ResponseEntity<byte[]> getCertificate(@PathVariable("id") int demandeCongeId) {
        DemandeConge demandeConge = demandeCongeRepository.findById(demandeCongeId).orElse(null);
        if (demandeConge == null || demandeConge.getCertificate() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", demandeConge.getCertificateFileName());

        return new ResponseEntity<>(demandeConge.getCertificate(), headers, HttpStatus.OK);
    }

    @GetMapping("/")
    List<DemandeConge> getAllDemandeConge() {
        return demandeCongeRepository.findAll();
    }

    @DeleteMapping("/delete-demande-conge/{id}")
    public void deleteDemandeConge(@PathVariable("id") int id) {
        demandeCongeRepository.deleteById(id);
    }

    @PostMapping("/approve-demande-conge/{id}")
    void approveDemandeConge(@PathVariable("id") int id) {
        DemandeConge demandeConge = demandeCongeRepository.findById(id).orElse(null);
        if (demandeConge != null) {
            Conge conge = new Conge();
            conge.setDateDebut(demandeConge.getStartDate());
            conge.setDateFin(new Date(demandeConge.getStartDate().getTime() + (long) demandeConge.getDuration() * 24 * 60 * 60 * 1000));
            conge.setDuree(demandeConge.getDuration());
            conge.setTypeConge(TypeConge.valueOf(demandeConge.getType()));
            conge.setUser(demandeConge.getUser());
            User user = userRepository.findById(demandeConge.getUser().getId()).orElse(null);
            congeRepository.save(conge);
            if (user != null) {
                if (user.getSoldeConge()==0 && demandeConge.getCertificate()==null){
                    System.out.println("no solde conge");
                    Set<Salaire> salaires = user.getSalaires();
                    Salaire lastSalaire = null;
                    for (Salaire salaire : salaires) {
                        if (lastSalaire == null || salaire.getDate().after(lastSalaire.getDate())) {
                            lastSalaire = salaire;
                        }
                    }
                    if( lastSalaire != null ){
                      //minus the duration of days of conge *20 from the last salary
                        lastSalaire.setSalaire(lastSalaire.getSalaire() - demandeConge.getDuration() * 20);
                        salaireRepository.save(lastSalaire);
                }
                }

                if (user.getConges() == null) {
                    Set<Conge> conges = Set.of(conge);
                    user.setConges(conges);
                } else {
                    user.getConges().add(conge);
                }
                userRepository.save(user);
            }
            demandeCongeRepository.deleteById(id);
        }
    }

}
