package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Evaluation;
import com.test.COCONSULT.ServiceIMP.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Autowired
    EvaluationServiceImpl evaluationService;

    @PostMapping("/add-evaluation")
    public void addEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
    }
}
