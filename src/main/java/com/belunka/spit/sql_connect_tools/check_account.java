package com.belunka.spit.sql_connect_tools;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.belunka.spit.MainActivity;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.R;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class check_account {



    String account;
    TextView set_notify_title;
    Button next_button;
    MainActivity activity;

    public check_account(String update_account, MainActivity update_activity){
        this.account = update_account;
        this.activity = update_activity;
    }


    public void get_account_from_database(String finalCheck_am) throws SQLException {
        
        /*Fill credentials with ur database*/
        final String DATABASE_NAME = "";
        final String url = "" +
                DATABASE_NAME;

        final String username = "", password = "";

        final String TABLE_NAME = "STUDENTS";

        new Thread(() -> {
            //remove and let only progress bar
            activity.runOnUiThread(() -> { //change on ui thread changes
                //change here
                activity.get_notify_message().setVisibility(View.GONE);
                activity.get_next_button().setVisibility(View.GONE);
                activity.get_Display_image_check_account().setVisibility(View.GONE);
                activity.get_progressBar_display_message().setVisibility(View.VISIBLE);

            });


            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                String sql = "SELECT username FROM STUDENTS WHERE AM= ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, account);

                ResultSet rs = stmt.executeQuery();

                String output=null;

                if (rs.next()){
                    //change here update
                    activity.set_is_account_registered(true);
                    stmt.close();
                    connection.close();

                    activity.runOnUiThread(() -> { //change on ui thread changes
                        activity.get_notify_message().setText("Welcome back "+ finalCheck_am);
                        activity.get_notify_message().setVisibility(View.VISIBLE);
                        activity.get_next_button().setVisibility(View.VISIBLE);
                        activity.get_Display_image_check_account().setVisibility(View.VISIBLE);
                        activity.get_progressBar_display_message().setVisibility(View.GONE);

                    });


                }else{
                    //change here updates
                    activity.set_is_from_personal_sagment(false);
                    stmt.close();
                    connection.close();

                    activity.runOnUiThread(() -> { //change on ui thread changes
                        activity.get_notify_message().setText("Απ' ότι φαίνεται δεν έχεις κάνει εγγραφή");
                        activity.get_notify_message().setVisibility(View.VISIBLE);
                        activity.get_next_button().setText("REGISTER");
                        activity.get_next_button().setVisibility(View.VISIBLE);
                        activity.get_Display_image_check_account().setVisibility(View.VISIBLE);
                        activity.get_progressBar_display_message().setVisibility(View.GONE);

                    });

                }


            }catch (Exception E){
                activity.setContentView(R.layout.no_internet_connection);
                E.printStackTrace();
                System.out.println("Something went wrong in searching account");
            }



        }).start();
    }


    public void register_new_user_to_database(String get_username, String get_examino, String get_Etos) throws SQLException {
        
        /*Fill credentials with ur database*/
        final String DATABASE_NAME = "";
        final String url = "" +
                DATABASE_NAME;

        final String username = "", password = "";

        final String TABLE_NAME = "STUDENTS";

        new Thread(() -> {

            activity.runOnUiThread(() -> { //change on ui thread changes
                //change here
                activity.get_progressBar_signup().setVisibility(View.VISIBLE);
                activity.get_get_username().setVisibility(View.GONE);
                activity.get_get_examino().setVisibility(View.GONE);
                activity.get_get_etos().setVisibility(View.GONE);

            });

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            System.out.println("CONNECTION SUCCED");

            String sql = "INSERT INTO STUDENTS (username, AM, Banned, BAN_UNTIL, Admin_user, operator_user, myCourseScript, Examino, Etos) values ( ? , ? , ? , ? , ? , ? , ? , ? , ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,get_username);
            stmt.setString(2,account);
            stmt.setInt(3,0);
            stmt.setString(4,"null");
            stmt.setInt(5,0);
            stmt.setInt(6,0);
            String s = "{\"my_courses_display\":[{\"Etos\":3,\"Examine\":6,\"TeacherName\":\"Στέργιος Αναστασιάδης\",\"alert_Anaplirwsi\":false,\"examinoString\":\"Εαρινό Εξάμηνο\",\"info\":\"Εισαγωγή και ιστορία υπολογιστικών συστημάτων, διεργασίες, νήματα, συγχρονισμός, αδιέξοδο, χρονοδρομολόγηση επεξεργαστή, διαχείριση μνήμης, εικονική μνήμη, διαχείριση συσκευών, συστήματα αρχείων, ασφάλεια.\",\"name\":\"Λειτουργικά Συστήματα\",\"room_target\":\"I5\",\"teaching_days\":[\"Tuesday\",\"Thursday\",\"Friday\"],\"teaching_hours\":[\"15:00-17:00\",\"14:00-16:00\",\"16:00-18:00\"],\"type\":\"Υποχρεωτικό\"},{\"Etos\":2,\"Examine\":4,\"TeacherName\":\"Γεώργιος Τσιατούχας\",\"alert_Anaplirwsi\":false,\"examinoString\":\"Εαρινό Εξάμηνο\",\"info\":\"Εισαγωγή στην ηλεκτρονική. Ενισχυτές – Τελεστικοί ενισχυτές. Θεωρία ημιαγωγών. Η p-n επαφή – Δίοδοι. Κυκλώματα διόδων (ανορθωτές, ψαλιδιστές). Τρανζίστορ επίδρασης πεδίου και διπολικά τρανζίστορ επαφής: α) δομή και φυσική λειτουργία, β) χαρακτηριστικές ρεύματος-τάσης, γ) DC λειτουργία – πόλωση, δ) μοντέλα ασθενούς σήματος. Βασικές τοπολογίες ενισχυτών με τρανζίστορ: πόλωση και τρόποι λειτουργίας. Διαφορικοί ενισχυτές. Ενισχυτές πολλών σταδίων. Απόκριση συχνότητας. Ανάδραση.\",\"name\":\"Ηλεκτρονική\",\"room_target\":\"I5\",\"teaching_days\":[\"Wednesday\",\"Thursday\"],\"teaching_hours\":[\"16:00-18:00\",\"16:00-18:00\"],\"type\":\"Υποχρεωτικό\"}]}";
            stmt.setString(7,s);
            stmt.setString(8,get_examino);
            stmt.setString(9,get_Etos);


            int rowsAffected = stmt.executeUpdate();

            stmt.close();
            connection.close();

            //write here
            activity.set_is_account_registered(true);

        }catch (Exception E){
//            activity.setContentView(R.layout.no_internet_connection);
            E.printStackTrace();
            System.out.println("Something went wrong in searching account");
        }


            activity.runOnUiThread(() -> { //change on ui thread changes
                //change here
                activity.get_progressBar_signup().setVisibility(View.GONE);
                activity.get_get_username().setVisibility(View.VISIBLE);
                activity.get_get_examino().setVisibility(View.VISIBLE);
                activity.get_get_etos().setVisibility(View.VISIBLE);
            });

        }).start();

    }



}
