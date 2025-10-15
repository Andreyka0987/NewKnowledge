package org.example.petprohect_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StageStartUpClass extends Application implements ToStartNewStageInterface {

    public static Controller controller;
    public static InstantStartUp instantStartUp = new LoginCheckIsCorrect();

    ToStartNewStageInterface toStartNewStageInterface;
    public static final String USER_PROFILE_FXML = "user-profile.fxml";
    public static final String LOGIN_MENU_FXML = "login-menu.fxml";


    @Override
    public void start(Stage stage) throws IOException {
        startUpFunc(stage,LOGIN_MENU_FXML);
        instantStartUp.instantLogin(controller.loginBar,controller.passwordBar,controller.rememberMeButton);

    }

    public static void main(String[] args) {
        launch();
        instantStartUp.fileHooker();

    }


    public static void startUpFunc(Stage stage, String currentScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StageStartUpClass.class.getResource(currentScene));
        Scene scene = new Scene(fxmlLoader.load(), 625, 425);
        controller = fxmlLoader.getController();
        controller.setMainStage(stage);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void startMainUserScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StageStartUpClass.class.getResource(USER_PROFILE_FXML));
        Scene scene = new Scene(fxmlLoader.load(), 625, 425);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }






}