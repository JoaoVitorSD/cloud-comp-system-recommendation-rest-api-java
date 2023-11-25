package dcc.cloudcomp.recommendation.controller;

import dcc.cloudcomp.recommendation.model.Recommendation;
import dcc.cloudcomp.recommendation.service.RecommenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@CrossOrigin
public class RecomendationController {

    private RecommenderService recommender;


    public RecomendationController(RecommenderService recommender) {
        this.recommender = recommender;
    }

    @GetMapping
    public List<Recommendation> getRecommendation(@RequestParam List<String> tracks) {
        return recommender.getRecommendation(tracks);
    }
}
