package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class student {

    public void Student(ActionEvent event) throws IOException{
        Parent quiz = FXMLLoader.load(getClass().getResource("/quizWindow.fxml"));
        Scene scene = new Scene(quiz);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Student");
        appStage.setScene(scene);
        appStage.show();

    }
}