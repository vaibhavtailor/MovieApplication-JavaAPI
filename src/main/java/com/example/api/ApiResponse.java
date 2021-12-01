package com.example.api;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    private String totalResults;

    @SerializedName("Response")
    private String Response;

    @SerializedName("Search")
    private Movie[] search;

    public Movie[] getSearch() {
        return search;
    }

    public void setSearch(Movie[] search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
