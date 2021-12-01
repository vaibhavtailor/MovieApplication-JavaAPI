package com.example.api;

public class Main {
    public static void main(String[] args) {
        ApiResponse result = ApiUtility.getMoviesJsonFile();
        System.out.println(result);

    }
}
