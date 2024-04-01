package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Pointage;
import com.test.COCONSULT.ServiceIMP.PointageServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/pointage")
public class PointageController {
    @Autowired
    PointageServiceIMP pointageServiceIMP;

    @PostMapping("/add-pointage")
    public void addPointage(@RequestBody Pointage p) {
        pointageServiceIMP.addPointage(p);
    }
}
