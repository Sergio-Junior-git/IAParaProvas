package com.iAProvas.autoCorretionProvas.service;


import com.iAProvas.autoCorretionProvas.model.EvaluationResults;
import com.iAProvas.autoCorretionProvas.model.QuestionDetail;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class GradingService {

    public EvaluationResults grade(Map<Integer, String> detected, Map<Integer, String> template) {
        int total = template.size();
        int correct = 0;
        List<QuestionDetail> details = new ArrayList<>();

        // todo um teste pra cada entrada do gabarito, vendo se o valor foi recebido e se bate com o esperado
        for (var e : template.entrySet()) {
            int question = e.getKey();
            String expected = e.getValue();
            String found = detected.get(question);
            boolean ok = found != null && found.equalsIgnoreCase(expected);
            if (ok) correct++;
            details.add(new QuestionDetail(question, expected, found, ok));
        }

        double score = (double) correct / total * 10.0; // divide as corretas pelo total de questoes e multiplica por 10 para ter a nota
        return new EvaluationResults(score, correct, total, details);
    }

}
