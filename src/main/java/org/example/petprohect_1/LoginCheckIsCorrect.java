package org.example.petprohect_1;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.sql.*;

public class LoginCheckIsCorrect implements InstantStartUp{



    private static final String dataBaseURL = "jdbc:mysql://localhost:3306/userinfo";
    private static final String dataBaseUser = "root";
    private static final String dataBasePassword = "password";




    public boolean dataBaseConnectionFunc(String login, String inputPassword){
        Connection connection = null;
        Statement statement = null;


        System.out.println(login);
        System.out.println(inputPassword);

        try {
            connection = DriverManager.getConnection(dataBaseURL,dataBaseUser,dataBasePassword);

            statement = connection.createStatement();

            String selectSQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()){
                String inBaseLogin = resultSet.getString("login");
                String inBasePassword = resultSet.getString("password");



                if (inBaseLogin.equals(login)){
                    if (inBasePassword.equals(inputPassword)){
                        System.out.println("Welcome!");
                        return true;
                    }
                    else {
                        System.out.println("Something wrong with password!");

                    }
                }
                else {
                    System.out.println("SomeThing wrong with login");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) statement.close();
                if (connection != null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean checkingIsLoginIsCorrect(String login, String password){

        String[] loginArr = login.split("");

        for (int i=0; i<loginArr.length; i++) {

            if (loginArr[i].matches("[A-Z]")) {

                System.out.println("Error");
            }
            else {
                return dataBaseConnectionFunc(login,password);
            }
        }


        return false;
    }


    public void checkIfUserWantToRememberHim(String login, String password, boolean isStartUp) throws IOException {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/org/example/petprohect_1/cookies/RememberCookie.txt")){};

            bufferedWriter.write("IsAutoStartUp = "+isStartUp);
            bufferedWriter.newLine();
            bufferedWriter.write("Login = "+login);
            bufferedWriter.newLine();
            bufferedWriter.write("Pass = "+password);
            bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void instantLogin(TextField login, TextField password, RadioButton radioButton) throws IOException {

        File file = new File("src/main/java/org/example/petprohect_1/cookies/RememberCookie.txt");

        if (!file.exists()){
            checkIfUserWantToRememberHim("","",false);
        }



        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/org/example/petprohect_1/cookies/RememberCookie.txt")){};


            String radioRead = bufferedReader.readLine();
            boolean checkIfRadioIsTrue = Boolean.parseBoolean(radioRead.substring(16));
            System.out.println(checkIfRadioIsTrue);

            bufferedReader.read();
            String loginRead = bufferedReader.readLine();
            bufferedReader.read();
            String passwordRead = bufferedReader.readLine();


            if (checkIfRadioIsTrue) {
                radioButton.setSelected(true);
                login.setText(loginRead.substring(7));
                password.setText(passwordRead.substring(6));
            }else {
                radioButton.setSelected(false);

            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void fileHooker() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("src/main/java/org/example/petprohect_1/cookies/HardKey.txt");
                if (file.exists())file.delete();
            }
        }));
    }
}
