package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.Objects;

public class Instructor {
    @FXML Text questionHeading, answerText;
    @FXML TextField enterQuestion,maximumScoreInput,expectedAnswerInput,optionA,optionB,optionC,optionD,quizTitle,quizDescription;
    @FXML RadioButton type;
    @FXML VBox maximumScore, expectedAnswer, options, optionInput;
    @FXML Button finish;
    @FXML ListView<String> questionList;
    @FXML ListView<String> quizList;

    static int questionCount=0;
    RadioButton Type;
    static int quizNumber =0;

    public void Instructor(ActionEvent event) throws IOException {
        Parent quiz = FXMLLoader.load(getClass().getResource("/Instructor.fxml"));
        Scene scene = new Scene(quiz);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Instructor");
        appStage.setScene(scene);
        appStage.show();
        //if (quizNumber >0) quizList.getItems().add("Quiz # " + quizNumber);
    }

    public void onClickCreateQuiz(ActionEvent event) throws IOException {
        Parent quiz = FXMLLoader.load(getClass().getResource("/quizCreation.fxml"));
        Scene scene = new Scene(quiz);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Instructor");
        appStage.setScene(scene);
        appStage.show();
        quizNumber++;
    }

    public void onClickCreate(ActionEvent event) throws IOException{
        if(Objects.equals(quizTitle.getText(),"") || Objects.equals(quizDescription.getText(),"")){
            sample.alertBox.display("Alert", "Kindly fill all the details");
        } else {
            Parent quiz = FXMLLoader.load(getClass().getResource("/questions.fxml"));
            Scene scene = new Scene(quiz);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setTitle("Instructor");
            appStage.setScene(scene);
            appStage.show();
            UserData.createQuiz(quizNumber,quizTitle,quizDescription);
        }
    }

    public void onAddQuestion(){
        questionCount++;
        Type = (RadioButton) type.getToggleGroup().getSelectedToggle();
        if (Type == null){
            sample.alertBox.display("Alert","Kindly select one answer type for your question." );
        } else if(Objects.equals(Type.getText(), "MCQs")){
            questionHeading.setVisible(true);
            questionHeading.setText("Question" + " # "+questionCount);
            enterQuestion.setVisible(true);
            answerText.setVisible(true);
            maximumScore.setVisible(true);
            expectedAnswer.setVisible(true);
            finish.setVisible(true);
            options.setVisible(true);
            optionInput.setVisible(true);
        } else if (Objects.equals(Type.getText(), "True/False") || Objects.equals(Type.getText(), "Numeric")) {
            questionHeading.setVisible(true);
            questionHeading.setText("Question" + " # "+questionCount);
            enterQuestion.setVisible(true);
            answerText.setVisible(true);
            maximumScore.setVisible(true);
            expectedAnswer.setVisible(true);
            finish.setVisible(true);
        }
    }

    public void onQuestionFinish() throws IOException {
        if(Objects.equals(Type.getText(),"True/False") || Objects.equals(Type.getText(),"Numeric") ){
            if(Objects.equals(enterQuestion.getText(), "") || Objects.equals(maximumScoreInput.getText(), "") || Objects.equals(expectedAnswerInput.getText(), "")){
                sample.alertBox.display("Alert","Kindly fill all the details");
            } else {
                UserData.writeQuizFileOther(Type, questionCount,enterQuestion,maximumScoreInput,expectedAnswerInput);
                questionHeading.setVisible(false);
                enterQuestion.setVisible(false);
                enterQuestion.setText("");
                maximumScore.setVisible(false);
                maximumScoreInput.setText("");
                expectedAnswer.setVisible(false);
                expectedAnswerInput.setText("");
                finish.setVisible(false);
                questionList.getItems().add("Question" + " # "+questionCount);
            }
        } else if(Objects.equals(Type.getText(),"MCQs")){

            if(Objects.equals(enterQuestion.getText(), "") || Objects.equals(optionA.getText(), "") || Objects.equals(optionB.getText(), "") || Objects.equals(optionC.getText(), "") || Objects.equals(optionD.getText(), "") || Objects.equals(maximumScoreInput.getText(), "") || Objects.equals(expectedAnswerInput.getText(), "")){
                sample.alertBox.display("Alert","Kindly fill all the details");
            } else {
                UserData.writeQuizFileMCQs(Type, questionCount,enterQuestion,optionA,optionB,optionC,optionD,maximumScoreInput,expectedAnswerInput);
                questionHeading.setVisible(false);
                enterQuestion.setVisible(false);
                enterQuestion.setText("");
                maximumScore.setVisible(false);
                maximumScoreInput.setText("");
                expectedAnswer.setVisible(false);
                expectedAnswerInput.setText("");
                finish.setVisible(false);
                options.setVisible(false);
                optionInput.setVisible(false);
                optionA.setText("");
                optionB.setText("");
                optionC.setText("");
                optionD.setText("");
                questionList.getItems().add("Question" + " # "+questionCount);
            }
        }

    }

    public void onClickFinishQuiz(ActionEvent event) throws IOException{
        Instructor instructor = new Instructor();
        instructor.Instructor(event);
    }
}
