package sample;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.*;

public class UserData {

    static File file = new File("LoginData");
    static boolean userFound;
    static File quiz;


    public static boolean isFound(TextField username, TextField password, RadioButton role) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String buffer = new String();
        RadioButton Role = (RadioButton) role.getToggleGroup().getSelectedToggle();
        String data = username.getText() + "," + password.getText() + "," + Role.getText();
        System.out.println(data);
        while ((buffer = bufferedReader.readLine()) != null){
            if(data.equals(buffer)) {
                System.out.println(buffer);
                userFound = true;
                break;
            } else userFound = false;
        }
        return userFound;
    }

    public static void createQuiz(int quizNumber, TextField title, TextField description) throws IOException{
        quiz = new File("quiz "+" # "+quizNumber);
        FileOutputStream fileOutputStream = new FileOutputStream(quiz,true);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeChars("{\"Quiz" + " # " +quizNumber + "\":" + "{\"title\":" + "\"" + title.getText() + "\"," + "\"description\":" + "\"" + description.getText() + "\"" + "," + "\"questions" + "\":[");
        fileOutputStream.close();
    }

    public static void writeQuizFileMCQs(RadioButton type, int questionCount, TextField enterQuestion, TextField optionA, TextField optionB, TextField optionC, TextField optionD, TextField maximumScoreInput, TextField expectedAnswerInput) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(quiz,true);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeChars("{\"number\":\"" + questionCount + "\"," + "\"question\":\"" + enterQuestion.getText() + "\"," + "\"optionA\":\"" + optionA.getText() + "\"," + "\"optionB\":\"" + optionB.getText() + "\"," + "\"optionC\":\"" + optionC.getText() + "\"," + "\"optionD\":\"" + optionD.getText() + "\"," + "\"maximum score\":\"" + maximumScoreInput.getText() + "\"," + "\"expected answer\":\"" + expectedAnswerInput.getText() + "\",\"answer type\":\"" + type.getText() + "\"},");
        fileOutputStream.close();
    }

    public static void writeQuizFileOther(RadioButton type, int questionCount, TextField enterQuestion, TextField maximumScoreInput, TextField expectedAnswerInput) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(quiz,true);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeChars("{\"number\":\"" + questionCount + "\"," + "\"question\":\"" + enterQuestion.getText() + "\"," + "\"maximum score\":\"" + maximumScoreInput.getText() + "\"," + "\"expected answer\":\"" + expectedAnswerInput.getText() + "\",\"answer type\":\"" + type.getText() + "\"},");
        fileOutputStream.close();
    }
}