package de.skratzsch.patterns.singleton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SingletonService {
    // Singleton instance (thread-safe using "Initialization-on-demand holder idiom")
    private static class Holder {
        static final SingletonService INSTANCE = new SingletonService();
    }

    // Private constructor to prevent instantiation
    private SingletonService() {}

    // Öffentliche Methode, um die Singleton-Instanz zu erhalten
    public static SingletonService getInstance() {
        return Holder.INSTANCE;
    }

    // Public method to get the singleton instance
    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchDataFromUrl(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from " + url + ": " + response.body());
            return response.body();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.err.println("Error fetching data from " + url + ": " + e.getMessage());
            return null;
        }
    }
}
