package com.example.api;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApiUtility {

    /**
     * This method will read the file called jsonData in the root and create an ApiResponse object
     */
    public static  ApiResponse getMoviesJsonFile() {

        //Create a GSON object
        Gson gson = new Gson();

        ApiResponse response = null;

        try(
                FileReader fileReader = new FileReader("jsonData");
                JsonReader jsonReader = new JsonReader(fileReader);
            )
        {
            response = gson.fromJson(jsonReader, ApiResponse.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * This will call the omdb api with specified search term
     */
    public static ApiResponse getMoviesFromOMDB(String searchTerm) throws IOException, InterruptedException {
        searchTerm = searchTerm.trim().replace(" ", "%20");

        String uri = "http://www.omdbapi.com/?apikey=cfb9b2f0&s="+searchTerm;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

//        //this sends result from api to file
//        HttpResponse<Path> response = client.send(httpRequest,
//                                                    HttpResponse.BodyHandlers.ofFile(Paths.get("jsonData")));
//
//        return getMoviesJsonFile();

         HttpResponse<String> response = client.send(httpRequest,
                                                        HttpResponse.BodyHandlers.ofString());
         String jsonString = response.body();

         Gson gson = new Gson();
         ApiResponse apiResponse = null;

         try{
             apiResponse = gson.fromJson(jsonString, ApiResponse.class);
         } catch (Exception e) {
             e.printStackTrace();
         }

         return apiResponse;

    }

}
