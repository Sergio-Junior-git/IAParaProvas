package com.iAProvas.autoCorretionProvas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "gabarito_template")
public class GabaritoTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "total_questoes")
    private int totalQuestions;

    @Column(name = "alternativas")
    private int alternatives;

    @Lob //lombok que guarda as informaçoes em JSON pro sistema ler
    @Column(name = "respostas_json", columnDefinition = "TEXT")
    private String answersJson;

    
}
