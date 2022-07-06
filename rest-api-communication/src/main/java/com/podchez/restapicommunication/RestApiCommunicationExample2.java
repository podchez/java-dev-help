package com.podchez.restapicommunication;

import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

// using API: https://api.itbook.store/1.0
public class RestApiCommunicationExample2 {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // String.class response, like in Example1
        // System.out.println(restTemplate.getForObject("https://api.itbook.store/1.0/search/" + "java", String.class));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Search IT books by Title, Author: ");
        String bookToSearch = scanner.nextLine();

        String url = "https://api.itbook.store/1.0/search/" + bookToSearch;
        // Custom java object response, using Jackson library
        ItbookResponse itbookResponse = restTemplate.getForObject(url, ItbookResponse.class);

        System.out.println("Search results:");
        for (Book book : itbookResponse.getBooks()) {
            System.out.println(book);
        }
    }
}
