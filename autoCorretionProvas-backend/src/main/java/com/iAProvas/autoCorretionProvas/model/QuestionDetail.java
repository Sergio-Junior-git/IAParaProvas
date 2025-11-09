package com.iAProvas.autoCorretionProvas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDetail {
    private int question;
    private String correctAnswer;
    private String detectedAnswer;
    private boolean correct;
}
