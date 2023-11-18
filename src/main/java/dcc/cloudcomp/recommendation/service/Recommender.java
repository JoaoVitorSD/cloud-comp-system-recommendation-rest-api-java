package dcc.cloudcomp.recommendation.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dcc.cloudcomp.recommendation.model.Dataset;
import dcc.cloudcomp.recommendation.model.Recommendation;
import dcc.cloudcomp.recommendation.model.RulesMatchManager;
import dcc.cloudcomp.recommendation.model.RuleCompatibility;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class Recommender {
    Dataset dataset;


    public Recommender() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        this.dataset = objectMapper.readValue(inputStream, new TypeReference<>(){});
    }


    public List<Recommendation> getRecommendation(List<String> tracks){
        RulesMatchManager rulesMatchsManager = new RulesMatchManager();
        for (Rule rule : dataset.getRecommendationList()) {
            List<String> matchedTracks = new ArrayList<>();
            for(String track : tracks){
                for(String trackRule : rule.getTracks()){
                    if(track.equals(trackRule)){
                        matchedTracks.add(track);
                    }
                }
            }
            float compatibility = (float) matchedTracks.size() / rule.getTracks().size();
            rulesMatchsManager.addRule(new RuleCompatibility(rule, compatibility, matchedTracks));
        }
        return rulesMatchsManager.getRecommentaions();
    }
}
