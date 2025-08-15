package de.skratzsch.patterns.singleton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Provides a thread-safe singleton service for fetching data from URLs using HTTP.
 * This class follows the "Initialization-on-demand holder idiom" pattern to ensure
 * lazy and thread-safe initialization of the singleton instance.
 *
 * <p>Example usage:
 * <pre>{@code
 * SingletonService service = SingletonService.getInstance();
 * String data = service.fetchDataFromUrl("https://api.example.com/data");
 * }</pre>
 *
 * @see java.net.http.HttpClient
 * @see java.net.http.HttpRequest
 */
public class SingletonService {

    /**
     * Private static holder class to defer singleton initialization until first use.
     * This approach is thread-safe and does not require explicit synchronization.
     */
    private static class Holder {
        static final SingletonService INSTANCE = new SingletonService();
    }

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the internal HTTP client.
     */
    private SingletonService() {
        // Initialization code (if any) goes here
    }

    /**
     * Returns the singleton instance of {@code SingletonService}.
     *
     * @return The singleton instance of {@code SingletonService}
     */
    public static SingletonService getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * HTTP client used for all network requests.
     * Initialized once and reused for all instances.
     */
    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Fetches data from the specified URL and returns the response body as a String.
     * Handles both {@link IOException} and {@link InterruptedException} gracefully.
     *
     * @param url The URL to fetch data from. Must be a valid HTTP/HTTPS URL.
     * @return The response body as a String, or {@code null} if the request fails.
     * @throws IllegalArgumentException If the URL is malformed or invalid.
     * @see #getInstance()
     * @see java.net.http.HttpClient#send(HttpRequest, HttpResponse.BodyHandler)
     */
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
