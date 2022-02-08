package com.example.api.Controllers;

import com.example.api.ApiResponse;
import com.example.api.ApiUtility;
import com.example.api.Movie;
import com.example.api.SceneChanger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Movie> initialMovieDataListView;

    @FXML
    private ImageView posterImageView;

    @FXML
    private Label errMsgLabel;

    @FXML
    private Button getDetailsButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private void getSearchResults() throws IOException, InterruptedException {
        initialMovieDataListView.getItems().clear();

        ApiResponse apiResponse = ApiUtility.getMoviesFromOMDB(searchTextField.getText());
        if (apiResponse.getSearch() != null)
        {
            initialMovieDataListView.getItems().addAll(apiResponse.getSearchSorted());
            setMovieFound(true, false);
        }
        else
            setMovieFound(false, false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMovieFound(false, false);
        errMsgLabel.setVisible(false);
        progressBar.setVisible(false);

        initialMovieDataListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovie, movieSelected) -> {
                    progressBar.setVisible(true);

                    //create a new Thread to fetch the Poster art
                    //This is calling the sleep method to simulate doing some slow running task
                    Thread fetchPosterThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            double progress = 0;
                            for (int i=0; i<=10; i++)
                            {
                                //simulate the computer doing work
                                try{
                                    Thread.sleep(1000);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                progress += 0.1;

                                //create a final (non-changable) variable to pass in the progress
                                //to the JavaFX thread
                                final double reportedProgress = progress;

                                //this is the JavaFX thread.  The method runLater() will execute
                                //once the thread is available
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (reportedProgress > .9){
                                            progressBar.setVisible(false);
                                            try{
                                                posterImageView.setImage(new Image(movieSelected.getPoster()));
                                                setMovieFound(true,true);
                                            }catch(Exception e)
                                            {
                                                posterImageView.setImage(new Image("images/defaultPoster.png"));
                                            }
                                        }
                                        progressBar.setProgress(reportedProgress);
                                    }
                                });
                            }

                        }
                    });
                    fetchPosterThread.start();
                });
    }

    /**
     * This method will turn visual element to be visible or not visible depending
     * on the state of the GUI
     */
    private void setMovieFound(boolean movieFound, boolean movieSelected)
    {
        initialMovieDataListView.setVisible(movieFound);
        getDetailsButton.setVisible(movieSelected);
        posterImageView.setVisible(movieSelected);
        errMsgLabel.setVisible(!movieFound);
    }

    @FXML
    private void getMovieDetails(ActionEvent event) throws IOException, InterruptedException {
        String movieId = initialMovieDataListView.getSelectionModel().getSelectedItem().getImdbID();
        SceneChanger.changeScenes(event, "movie-details-view.fxml", movieId);
    }


}
