package com.iAProvas.autoCorretionProvas.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class OpenIaVisionService {

    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String apiKey = System.getenv("OPENAI_API_KEY");
    
    public OpenIaVisionService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Map<Integer, String> detectAnswersFromImage(MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        String b64 = Base64.getEncoder().encodeToString(bytes);

        String prompt = """
            Você é um assistente que analisa imagens de gabaritos de múltipla escolha.
            Retorne apenas JSON no formato:
            { "answers": { "1": "A", "2": "B", "3": "D", ... } }
            Se alguma questão não puder ser detectada, coloque null.
                """;

        Map<String, Object> body = Map.of(
                "model", "gpt-4o-mini-vision",
                "input", Map.of(
                        "image", "data:image/jpeg;base64," + b64,
                        "prompt", prompt
                )
        );

        String response = webClient.post()
            .uri("/responses")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        
        JsonNode root = mapper.readTree(response);
        String text = root.at("/output/0/content/0/text").asText(null);
        if (text == null) text = root.at("/choices/0/message/content").asText();

        JsonNode parsed = mapper.readTree(text);
        JsonNode answers = parsed.get("answers");

        Map<Integer, String> detected = new HashMap<>();
        answers.properties().forEach(e -> detected.put(Integer.parseInt(e.getKey()), e.getValue().asText()));
        return detected;
    }
}
