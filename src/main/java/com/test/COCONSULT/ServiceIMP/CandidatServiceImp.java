package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.*;
import com.test.COCONSULT.Interfaces.CandidatServiceInterface;
import com.test.COCONSULT.Reposotories.*;
import com.test.COCONSULT.Services.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CandidatServiceImp implements CandidatServiceInterface {
    @Autowired
    CandidatRepository candidatRepository;
    QuestionRepository questionRepository;
    QuizRepository quizRepository;
    TestRepository testRepository;
    MailSenderService mailSenderService;
    CandidateNotificationService candidateNotificationService;
    JobOpportRepository jobOpportRepository;

    @Override
    public Candidat createCandidat(Candidat candidat) {
        if (candidatRepository.findByEmail(candidat.getEmail()) == null) {
            ////  candidat.getJobOpport().add(candidat.)
            return candidatRepository.save(candidat);
        }
        return candidat;
    }

    @Override
    public Candidat updateCandidat(int id, Candidat candidat) {
        if (candidatRepository.existsById(id)) {
            candidat.setId_condidat(id);
            return candidatRepository.save(candidat);
        }
        return null;
    }


    @Override
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    @Override
    public Candidat getCandidatById(int id) {
        return candidatRepository.findById(id).orElse(null);
    }


    @Override
    public void ajouterTest(int idQuestion, int idQuiz, int idCandidat, String selectedAnswer) {
        Qestion question = questionRepository.findById(idQuestion).orElseThrow(() -> new RuntimeException("Question not found"));
        Quiz quiz = quizRepository.findById(idQuiz).orElseThrow(() -> new RuntimeException("Quiz not found"));
        Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow(() -> new RuntimeException("Candidat not found"));

        test nouveauTest = new test();
        nouveauTest.getQuestions().add(question);
        nouveauTest.getQuiz().add(quiz);
        nouveauTest.setCandidat(candidat);

        question.setSelected_answer(selectedAnswer);

        testRepository.save(nouveauTest);
    }

    @Override
    public void deleteCandidat(int id) {
        candidatRepository.deleteById(id);
    }


    @Override
    public Candidat getCandidatByEmail(String email) {
        return candidatRepository.findByEmail(email);
    }
@Override
    public Candidat createCandidatWithOnlyEmail(String email) {
        // Créer un nouveau candidat avec seulement l'e-mail
        Candidat candidat = new Candidat();
        candidat.setEmail(email);

        // Enregistrer le candidat
        return candidatRepository.save(candidat);
    }

    @Override
    public void ajouterCandidatAOffre(Candidat candidat, int idJobOpport) { //dorraaaaaaaaaa
        // Obtenez l'offre d'emploi en fonction de son ID
        JobOpport jobOpport = jobOpportRepository.findById(idJobOpport);
        // Ajoutez le candidat à l'offre d'emploi
        jobOpport.getCandidat().add(candidat);

        // Ajoutez l'offre d'emploi au candidat
        candidat.setJobOpport(jobOpport);

        // Enregistrez les modifications dans la base de données
        candidatRepository.save(candidat);
        jobOpportRepository.save(jobOpport);
    }

    public List<Object[]> getCandidatDetails() {
        return candidatRepository.findCandidatDetails();
    }

}
