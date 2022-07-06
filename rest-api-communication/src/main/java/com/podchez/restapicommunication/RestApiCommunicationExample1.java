package com.podchez.restapicommunication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// using API: https://reqres.in/api
public class RestApiCommunicationExample1 {

    public static void main(String[] args) {
        // using RestTemplate from spring-web dependency
        RestTemplate restTemplate = new RestTemplate();

        getRequestExample(restTemplate);
        System.out.println("----------");
        postRequestExample(restTemplate);
    }

    private static void getRequestExample(RestTemplate restTemplate) {
        String url = "https://reqres.in/api/users/2";
        String responseAfterGetRequest = restTemplate.getForObject(url, String.class);
        System.out.println(responseAfterGetRequest);
    }

    private static void postRequestExample(RestTemplate restTemplate) {
        Map<String, String> jsonKeyToValue = new HashMap<>();
        jsonKeyToValue.put("name", "Max");
        jsonKeyToValue.put("job", "Java Developer");
        // we can also add headers to HttpEntity in constructor params using the HttpHeaders class
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonKeyToValue);

        String url = "https://reqres.in/api/users";
        String responseAfterPostRequest = restTemplate.postForObject(url, request, String.class);
        System.out.println(responseAfterPostRequest);

        System.out.println("----------");

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Parsing using Jackson library:");
            JsonNode obj = mapper.readTree(responseAfterPostRequest);
            System.out.println("Name: " + obj.get("name"));
            System.out.println("Job: " + obj.get("job"));
            System.out.println("ID: " + obj.get("id"));
            System.out.println("CreatedAt: " + obj.get("createdAt"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Or we can create separate object for parsing response and also use Jackson library,
        // instead of getting the response in Spring (see example2)
    }
}
