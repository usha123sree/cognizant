package Cognizant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Import for ignoring unknown fields

public class Task36 {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";
    private static final String GITHUB_USERNAME = "google";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GITHUB_API_URL + GITHUB_USERNAME))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("--- HTTP Response Details ---");
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body (first 500 chars):\n" + response.body().substring(0, Math.min(response.body().length(), 500)) + "...");

            if (response.statusCode() == 200) {
                System.out.println("\n--- Parsing JSON Response ---");
                ObjectMapper mapper = new ObjectMapper();
                GitHubUser user = mapper.readValue(response.body(), GitHubUser.class);

                System.out.println("GitHub User Login: " + user.getLogin());
                System.out.println("GitHub User ID: " + user.getId());
                System.out.println("GitHub User Name: " + user.getName());
                System.out.println("Public Repos: " + user.getPublicRepos());
                System.out.println("Followers: " + user.getFollowers());
                System.out.println("Profile URL: " + user.getHtmlUrl());

            } else {
                System.err.println("Failed to fetch data. Status code: " + response.statusCode());
                System.err.println("Error Body: " + response.body());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error making HTTP request or parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Add this line
    static class GitHubUser {
        public int id;
        public String login;
        public String name;
        public int public_repos; // Matches "public_repos" in JSON
        public int followers;
        public String html_url; // Matches "html_url" in JSON

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getPublicRepos() { return public_repos; }
        public void setPublicRepos(int public_repos) { this.public_repos = public_repos; }

        public int getFollowers() { return followers; }
        public void setFollowers(int followers) { this.followers = followers; }

        public String getHtmlUrl() { return html_url; }
        public void setHtmlUrl(String html_url) { this.html_url = html_url; }
    }
}
