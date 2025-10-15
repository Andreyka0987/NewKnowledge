package org.example.petprohect_1;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public interface InstantStartUp {

    void instantLogin(TextField login, TextField password, RadioButton radioButton) throws IOException;
    void fileHooker();

}
