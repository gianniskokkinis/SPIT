package com.belunka.spit.Read_from_json;

import com.belunka.spit.Member.member_my_catalogue;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Json_Managment {


    /*Fill credentials with ur database*/

    public static final String DATABASE_NAME = "";
    public static final String url = "" +
            DATABASE_NAME;

    public static final String username = "", password = "";

    public static final String TABLE_NAME = "STUDENTS";



    Gson gson = new Gson();






    public member_my_catalogue get_Catalogue_from_json_script(String script){

        member_my_catalogue return_object = gson.fromJson(script, member_my_catalogue.class);

        return return_object;
    }


    public void update_catalogue_to_database(member_my_catalogue set_json_script){

        String json = gson.toJson(set_json_script);

        //update database here


    }


    public void get_Catalogue_from_script() throws SQLException {
        String output = "noscript found";
        new Thread(() -> {

            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                ResultSet rs = statement.executeQuery("SELECT myCourseScript FROM STUDENTS WHERE username='Giannis'");


                while (rs.next()) { //script here
                    String json = rs.getString(1);

                }


                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }).start();

    }

}
