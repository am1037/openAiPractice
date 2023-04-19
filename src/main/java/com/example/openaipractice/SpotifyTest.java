package com.example.openaipractice;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;
import java.net.URI;

public class SpotifyTest {
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
        SpotifyTest spotifyTest = new SpotifyTest();
        spotifyTest.setToken();
        spotifyTest.searchTest("Laura day romance", "artist");

    }

    String client_ID = System.getenv("SPOTIFY_CID");
    String client_SECRET = System.getenv("SPOTIFY_SECRET");
    String token;
    SpotifyApi spotifyApi;
    ClientCredentials clientCredentials;
    public SpotifyTest() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(client_ID)
                .setClientSecret(client_SECRET)
                .build();
    }

    public String getToken() {
        return token;
    }
    public void setToken() {
        try {
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                    .build();
            clientCredentials = clientCredentialsRequest.execute();
            token = clientCredentials.getAccessToken();
            spotifyApi.setAccessToken(token);
            System.out.println("token is : " + token);
            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchTest(String str, String type){
        SearchItemRequest sir = spotifyApi.searchItem(str, type)
                .build();
        try {
            SearchResult sr = sir.execute();
            for (Artist artist : sr.getArtists().getItems()) {
                System.out.println(artist.getName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
