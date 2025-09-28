package org.example.petprohect_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{


    Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    ToStartNewStageInterface startUpInterface = new StageStartUpClass();
    public boolean IsUserWindow = false;
    public TextField loginBar;
    public TextField passwordBar;
    public RadioButton rememberMeButton;
    LoginCheckIsCorrect loginCheckIsCorrect = new LoginCheckIsCorrect();
    MainWindowLogic mainWindowLogic = new MainWindowLogic();


    public void enterLoginButton(ActionEvent event) throws IOException {

          IsUserWindow = loginCheckIsCorrect.checkingIsLoginIsCorrect(loginBar.getText(),passwordBar.getText());

          if (rememberMeButton.isSelected()) {
              loginCheckIsCorrect.checkIfUserWantToRememberHim(loginBar.getText(),passwordBar.getText(),true);
          }
          if (!rememberMeButton.isSelected()){
              loginCheckIsCorrect.checkIfUserWantToRememberHim(loginBar.getText(),"",false);
          }


          if (mainStage != null && IsUserWindow){
            startUpInterface.startMainUserScene(mainStage);
          }

          mainWindowLogic.connectAndGetPrimaryKeyFunc();

    }





}