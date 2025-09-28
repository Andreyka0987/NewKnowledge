package org.example.petprohect_1;

import java.io.*;
import java.sql.*;

public class MainWindowLogic {

    private static final String dataBaseURL = "jdbc:mysql://localhost:3306/userinfo";
    private static final String dataBaseUSER = "root";
    private static final String dataBasePassword = "";



    public void connectAndGetPrimaryKeyFunc(){
        Connection connection = null;
        Statement statement = null;
        String userLoginName;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/org/example/petprohect_1/cookies/RememberCookie.txt"));
            bufferedReader.readLine();
            userLoginName = bufferedReader.readLine().substring(8);
            System.out.println(userLoginName);

            connection = DriverManager.getConnection(dataBaseURL,dataBaseUSER,dataBasePassword);
            statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");


                while (resultSet.next() ){
                    String inBaseLogin = resultSet.getString("login");
                    String inBaseID = resultSet.getString("id");


                    if (userLoginName.equals(inBaseLogin)) {
                        createFileWithKey(inBaseID);
                    }


                }



        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (statement != null)statement.close();
                if (connection != null)connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }


    private static void createFileWithKey(String key){
        try (BufferedWriter createMainKey = new BufferedWriter(new FileWriter("src/main/java/org/example/petprohect_1/cookies/HardKey.txt")) {
        }) {
            createMainKey.write(key);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

