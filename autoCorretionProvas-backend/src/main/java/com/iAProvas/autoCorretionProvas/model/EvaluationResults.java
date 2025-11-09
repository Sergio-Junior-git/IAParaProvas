package com.iAProvas.autoCorretionProvas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class EvaluationResults {
    private double score;
    private int correct;
    private double total;
    private List<QuestionDetail> details;
}
