package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Candidat;
import com.test.COCONSULT.Entity.Entretien;
import com.test.COCONSULT.Reposotories.CandidatRepository;
import com.test.COCONSULT.Reposotories.EntretienRepository;
import com.test.COCONSULT.Services.MailSenderService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class GoogleMeetAPIService {
    @Autowired
    private MailSenderService mailSenderService;
@Autowired
    CandidatRepository candidatRepository;

    @Autowired
    private EntretienRepository entretienRepository;

    public Entretien getEntretienById(int entretienId) {
        return entretienRepository.findById(entretienId)
                .orElseThrow(() -> new EntityNotFoundException("Entretien not found with ID: " + entretienId));
    }


    public Entretien ajouterEntretienEtAssocierACandidat(Entretien entretien, int candidatId) {
        Candidat candidat = candidatRepository.findById(candidatId).orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
        entretien.setCandidat(candidat);
        return entretienRepository.save(entretien);
    }
}


