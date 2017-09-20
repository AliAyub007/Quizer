package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;


public class Login {
    boolean userExists;
    @FXML TextField usernameText, passwordText;
    @FXML RadioButton radioButton;
    public void login(ActionEvent event){
            try {
                userExists = UserData.isFound(usernameText, passwordText, radioButton);
                RadioButton Role = (RadioButton) radioButton.getToggleGroup().getSelectedToggle();
                if(userExists && Objects.equals(Role.getText(),"Instructor")){
                    alertBox.display("Alert","Login Successful!");
                    Instructor instructor = new Instructor();
                    instructor.Instructor(event);
                } else if(userExists && Objects.equals(Role.getText(),"Student")){
                    alertBox.display("Alert","Login Successful!");
                    student student = new student();
                    student.Student(event);
                } else alertBox.display("Alert","Login Failed!");

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}