package dcc.cloudcomp.recommendation.model;

import dcc.cloudcomp.recommendation.service.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleCompatibility {
    private Rule rule;
    private float compatibility;

    private List<String> matchedTracks;
}
