package com.mansourappdevelopment.hrvacationapp.controller;

import com.mansourappdevelopment.hrvacationapp.Main;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private PasswordField passwordInputField;
    @FXML
    private TextField userNameInputFiled;
    @FXML
    private ImageView passwordImage;
    @FXML
    private ImageView userNameImage;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        String userName = userNameInputFiled.getText();
        String password = passwordInputField.getText();
        if (Validator.validUserName(userName)) {
            if (userName.equals("hr")) {
                if (Validator.validPassword(password) && password.equals("hr")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/mansourappdevelopment/hrvacationapp/hr-view.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("HR Login Successful");
                } else {
                    //TODO:: Password wrong, Needs Alert
                }
            } else {
                //TODO:: check if the entered data matches a user from the database

                Parent root = FXMLLoader.load(getClass().getResource("/com/mansourappdevelopment/hrvacationapp/employee-view.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("Employee Login Successful");
            }
        }

    }
}