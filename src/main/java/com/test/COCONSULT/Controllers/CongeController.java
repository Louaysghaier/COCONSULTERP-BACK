package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Conge;
import com.test.COCONSULT.ServiceIMP.CongeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/conge")
public class CongeController {
    @Autowired
    CongeServiceImpl congeServiceIMP;

    @PostMapping("/add-conge")
    public void addConge(@RequestBody Conge c) {
        congeServiceIMP.addConge(c);
    }

    @GetMapping("/getCurrentConge")
    public List<Conge> getCurrentConge() {
        return congeServiceIMP.getCurrentConge();
    }
}
