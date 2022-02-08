package com.example.api.Controllers;

import com.example.api.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MovieDetailsController implements InitializeMovie {
    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label runTimeLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label writerLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private ListView<String> actorsListView;

    @FXML
    private ListView<Ratings> ratingsListView;

    @FXML
    private ImageView imageView;


    /**
     * Given the movieID, this method will call the API/JSON and create a
     * MovieDetails object. That will be used to populate the GUI with the movie
     * details
     * @param movieID - this is the ImdbID
     */
    public void loadMovieDetails(String movieID) {

        MovieDetails movie = null;
        try {
            movie = ApiUtility.getMovieDetails(movieID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        movieTitleLabel.setText(movie.getTitle());
        releaseDateLabel.setText(movie.getReleaseDate());
        runTimeLabel.setText(movie.getRuntime());
        genreLabel.setText(movie.getGenre());
        writerLabel.setText(movie.getWriter());
        languageLabel.setText(movie.getLanguage());
        actorsListView.getItems().addAll(movie.getActorsArray());
        ratingsListView.getItems().addAll(movie.getRatings());
        try{
            imageView.setImage(new Image(movie.getPoster()));
        }catch(Exception e)
        {
            imageView.setImage(new Image("defaultPoster.png"));
        }
    }

    @FXML
    private void returnToSearch(ActionEvent event) throws IOException, InterruptedException {
        SceneChanger.changeScenes(event,"search-view.fxml");
    }
}
