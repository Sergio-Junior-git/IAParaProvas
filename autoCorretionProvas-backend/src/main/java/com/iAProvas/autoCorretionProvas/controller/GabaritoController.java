package com.iAProvas.autoCorretionProvas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iAProvas.autoCorretionProvas.model.EvaluationResults;
import com.iAProvas.autoCorretionProvas.model.GabaritoTemplate;
import com.iAProvas.autoCorretionProvas.repo.GabaritoTemplateRepo;
import com.iAProvas.autoCorretionProvas.service.GradingService;
import com.iAProvas.autoCorretionProvas.service.OpenIaVisionService;


@RestController
@RequestMapping("/api/gabarito")
@CrossOrigin("*")
public class GabaritoController {

    private final GabaritoTemplateRepo repo;
    private final OpenIaVisionService vision;
    private final GradingService grading;

    public GabaritoController(GabaritoTemplateRepo repo, OpenIaVisionService vision, GradingService grading) {
        this.repo = repo;
        this.vision = vision;
        this.grading = grading;
    }

    @PostMapping("/template")
    public ResponseEntity<GabaritoTemplate> criarTemplate(@RequestBody GabaritoTemplate gabarito) {
        return ResponseEntity.ok(repo.save(gabarito));
    }

    @PostMapping("/avaliar/{id}")
    public ResponseEntity<EvaluationResults> avaliarProva( 
        @PathVariable Long id,
        @RequestParam("file") MultipartFile file)  throws Exception {
            
            var template = repo.findById(id).orElseThrow();
            Map<Integer, String> base = new ObjectMapper()
                .readValue(template.getAnswersJson(), Map.class);
            
            Map<Integer, String> detected = vision.detectAnswersFromImage(file);
            EvaluationResults results = grading.grade(base, detected);
            return ResponseEntity.ok(results);
    }
}
