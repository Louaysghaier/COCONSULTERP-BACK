package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.ProjFeed;
import com.test.COCONSULT.Interfaces.ProjFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projfeeds")
public class ProjFeedController {

    private final ProjFeedService projFeedService;

    @Autowired
    public ProjFeedController(ProjFeedService projFeedService) {
        this.projFeedService = projFeedService;
    }

    @GetMapping("getAllPrjFeed")
    public ResponseEntity<List<ProjFeed>> retrieveProjFeeds() {
        List<ProjFeed> projFeeds = projFeedService.retrieveProjFeeds();
        return ResponseEntity.ok(projFeeds);
    }

    @GetMapping("/getPrjFeedById/{idProjFeed}")
    public ResponseEntity<ProjFeed> retrieveProjFeed(@PathVariable("idProjFeed") Long idProjFeed) {
        ProjFeed projFeed = projFeedService.retrieveProjFeed(idProjFeed);
        if (projFeed != null) {
            return ResponseEntity.ok(projFeed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterPrjFeed")
    public ResponseEntity<ProjFeed> addProjFeed(@RequestBody ProjFeed projFeed) {
        ProjFeed savedProjFeed = projFeedService.addProjFeed(projFeed);
        return new ResponseEntity<>(savedProjFeed, HttpStatus.CREATED);
    }

    @PutMapping("/updatePfeed/{idProjFeed}")
    public ResponseEntity<ProjFeed> updateProjFeed(@PathVariable("idProjFeed") Long idProjFeed, @RequestBody ProjFeed projFeed) {
        projFeed.setIdPjtFeed(idProjFeed); // Set the ID from the path variable
        ProjFeed updatedProjFeed = projFeedService.updateProjFeed(projFeed);
        if (updatedProjFeed != null) {
            return ResponseEntity.ok(updatedProjFeed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePrjFeed/{idProjFeed}")
    public ResponseEntity<Void> removeProjFeed(@PathVariable("idProjFeed") Long idProjFeed) {
        projFeedService.removeProjFeed(idProjFeed);
        return ResponseEntity.noContent().build();
    }
}
