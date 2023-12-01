package com.github.osvaldsoza.backenduol.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Getter
public class CodiNomeService {

    @Autowired
    private Environment env;
    private List<String> averngersCodiNomesList = new ArrayList<>();
    private List<String> justiceLeagueCodiNomesList = new ArrayList<>();

    @PostConstruct
    public void loadJsonCodiNames() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            RestTemplate restTemplate = new RestTemplate();

            String codiNomesResponse = restTemplate.getForObject(Objects.requireNonNull(env.getProperty("avengers")), String.class);
            JsonNode jsonNode = objectMapper.readTree(codiNomesResponse);
            ArrayNode avengers = (ArrayNode) jsonNode.get("vingadores");

            for (JsonNode codiName : avengers) {
                this.averngersCodiNomesList.add(codiName.get("codinome").asText());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void loadXmlCodiNames() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(env.getProperty("justice.league"));

            NodeList codiNomeList = document.getElementsByTagName("codinome");

            for (int i = 0; i < codiNomeList.getLength(); i++) {
                Element codiNomeelement = (Element) codiNomeList.item(i);
                String codiNome = codiNomeelement.getTextContent();
                this.justiceLeagueCodiNomesList.add(codiNome);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
