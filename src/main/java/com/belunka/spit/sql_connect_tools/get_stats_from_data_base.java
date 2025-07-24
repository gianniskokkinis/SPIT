package com.belunka.spit.sql_connect_tools;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.belunka.spit.BlankFragment;
import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Course_Programma_Katalogos.ProgrammaMathimaton;
import com.belunka.spit.HomeSagment;
import com.belunka.spit.MainActivity;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.MyAdapter;
import com.belunka.spit.PersonalSagement;
import com.belunka.spit.R;
import com.belunka.spit.ScheduleSagement;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class get_stats_from_data_base {

    Activity mainActivity;
    Member member;
    HomeSagment homeSagment;
    PersonalSagement personalSagment;
    ScheduleSagement scheduleSagement;
    BlankFragment blankFragment;

    /**
     *
     * SPIT DATA BASE INFO
     *
     * */
    //need update
    public static final String DATABASE_NAME = "";
    public static final String url = "" +
            DATABASE_NAME;

    public static final String username = "", password = "";

    public static final String TABLE_NAME = "";

    String json, username_from_database, banned_from_database, BanUntil_Date, Admin_user, operator_user, Examino_from_database, Etos_from_database;

    String account;

  



    public get_stats_from_data_base(HomeSagment homeSagment, PersonalSagement personalSagment, ScheduleSagement scheduleSagement, Activity update_mainActivity, String update_account) throws SQLException {
        this.homeSagment = homeSagment;
        this.personalSagment = personalSagment;
        this.scheduleSagement = scheduleSagement;
        this.mainActivity = update_mainActivity;
        this.account = update_account;
        try{
            get_Catalogue_from_script();
        }catch (Exception e){
            System.out.println("Something went wrong with the DATABASE");
            e.printStackTrace();
        }

    }


    public boolean is_on_my_library_this_course(String course_name){
        for (int i=0; i<member.get_Member_Catalogue().get_Course_List().size(); i++){
            if (member.get_Member_Catalogue().get_Course_List().get(i).getName().equals(course_name)){
                return true;
            }
        }
        return false;
    }


    public void get_Catalogue_from_script() throws SQLException {
        new Thread(() -> {
            



            StringBuilder records = new StringBuilder();
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");


                String sql = "SELECT * FROM STUDENTS WHERE AM= ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,account);
                ResultSet rs = stmt.executeQuery();


                while (rs.next()) { //script here
                    Log.d("RS", "RS IS READING NOW ");//test
                    json = rs.getString(8);
                    username_from_database = rs.getString(2);
                    banned_from_database = rs.getString(4);
                    BanUntil_Date = rs.getString(5);
                    Admin_user = rs.getString(6);
                    operator_user = rs.getString(7);
                    Examino_from_database = rs.getString(9);
                    Etos_from_database = rs.getString(10);

                }

                //we are collection datahere


                connection.close(); //we dont need anymore connection with database

                if (Integer.parseInt(banned_from_database) == 1){
                    Log.d("Yes ", "This account has been banned");
                    //so we banned account
                    mainActivity.runOnUiThread(() -> { //change on ui thread changes
                        mainActivity.setContentView(R.layout.banned_account);
                        //so display this image

                    });

                }

                if(json == null){
                    System.out.println("ERROR NOT GATHER DATA");
                    return; //if did't get any result something went wrong
                }


                /**
                 *  Here we need to update personal fields
                 *
                 * */
                member = new Member(username_from_database,Examino_from_database, account, Etos_from_database);
                //initialize here the objects



                Gson gson = new Gson();
                System.out.println(json); //test

                member_my_catalogue get_course_from_script = gson.fromJson(json, member_my_catalogue.class);
                member.set_Member_Catalogue(get_course_from_script); //get here the script

                System.out.println(get_course_from_script.getCourse(0).getName());//test

                homeSagment.setMember(member);
                personalSagment.setMember(member);
                scheduleSagement.setMember(member);






                //end connection here
            } catch (Exception e) {
                e.printStackTrace();
            }



        }).start();



    }








    public void set_home_screen_stats() throws SQLException {

        ProgrammaMathimaton programmaMathimaton = new ProgrammaMathimaton();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            new Thread(() -> {

                mainActivity.runOnUiThread(() -> { //change on ui thread changes
                    //change here
                    homeSagment.get_course_title_of_home_screen().setVisibility(View.GONE);
                    homeSagment.get_kefalaio_course_screen().setVisibility(View.GONE);
                    homeSagment.get_status_icon_home_screen().setVisibility(View.GONE);
                    homeSagment.get_element_home_1().setVisibility(View.GONE);
                    homeSagment.get_element_home_2().setVisibility(View.GONE);
                    homeSagment.get_element_home_3().setVisibility(View.GONE);
                    homeSagment.get_welcome_user_name().setVisibility(View.GONE);
                    homeSagment.get_elemt_home_4().setVisibility(View.GONE);
                    homeSagment.get_course_title_home_screen().setVisibility(View.GONE);
                    homeSagment.get_home_element_5().setVisibility(View.GONE);
                    homeSagment.get_findCoursesBtn().setVisibility(View.GONE);
                    homeSagment.get_home_element_6().setVisibility(View.GONE);
                    homeSagment.get_welcomeUserString8().setVisibility(View.GONE);
                    homeSagment.get_imageView5().setVisibility(View.GONE);
                    homeSagment.get_imageView17().setVisibility(View.GONE);
                    homeSagment.get_textView().setVisibility(View.GONE);
                    homeSagment.get_imageView9().setVisibility(View.GONE);
                    homeSagment.get_welcomeUserString9().setVisibility(View.GONE);
                    homeSagment.get_username_message().setVisibility(View.GONE);
                    homeSagment.get_progressbar_home().setVisibility(View.VISIBLE);
                });



                //if day off
                SimpleDateFormat formatter = new SimpleDateFormat("HH");
                Date currentDate = new Date();
                String currentDateString = formatter.format(currentDate);

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    System.out.println("CONNECTION SUCCED");



                    String sql = "SELECT course_title,course_kefalaio FROM COURSES WHERE is_active_now=1";
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    ResultSet rs = stmt.executeQuery();


                    String course_title = "";
                    String kefalaio = "";
                    while (rs.next()){
                        Log.d("RS", "RS IS READING NOW ");//test
                        course_title = rs.getString(1); //get the course
                        kefalaio = rs.getString(2); // get the info of course here
                        if (course_title.length()!=0 && is_on_my_library_this_course(course_title)){ // and one statement here
                            //give info to home database
                            String finalCourse_title = course_title;
                            String finalKefalaio = kefalaio;
                            mainActivity.runOnUiThread(() -> { //change on ui thread changes
                                homeSagment.get_course_title_of_home_screen().setText(finalCourse_title);
                                homeSagment.get_kefalaio_course_screen().setText(finalKefalaio);
                                homeSagment.get_status_icon_home_screen().setImageDrawable(mainActivity.getDrawable(R.drawable.active_course)); //set to active course
                            });
                            break; //get out of while
                        }
                    }

                    //if not anything found check here

                    if (course_title.length() == 0){

                        sql = "SELECT course_title,course_kefalaio FROM COURSES WHERE is_active_now=2";
                        stmt = connection.prepareStatement(sql);
                        rs = stmt.executeQuery();

                        while (rs.next()){
                            Log.d("RS", "RS IS READING NOW ");//test
                            course_title = rs.getString(1); //get the course
                            kefalaio = rs.getString(2); // get the info of course here
                            if (course_title.length()!=0 && is_on_my_library_this_course(course_title)){ // and one statement here
                                //give info to home database
                                String finalCourse_title = course_title;
                                String finalKefalaio = kefalaio;
                                mainActivity.runOnUiThread(() -> { //change on ui thread changes
                                    homeSagment.get_course_title_of_home_screen().setText(finalCourse_title);
                                    homeSagment.get_kefalaio_course_screen().setText(finalKefalaio);
                                    homeSagment.get_status_icon_home_screen().setImageDrawable(mainActivity.getDrawable(R.drawable.course_break)); //set to active course
                                });
                                break; 
                            }
                        }

                    }

                    connection.close();

                    

                } catch (Exception e) {
                    e.printStackTrace();
                }









                mainActivity.runOnUiThread(() -> { //change UI stuff
                    
                    homeSagment.get_progressbar_home().setVisibility(View.GONE);
                    homeSagment.get_course_title_of_home_screen().setVisibility(View.VISIBLE);
                    homeSagment.get_kefalaio_course_screen().setVisibility(View.VISIBLE);
                    homeSagment.get_status_icon_home_screen().setVisibility(View.VISIBLE);
                    homeSagment.get_element_home_1().setVisibility(View.VISIBLE);
                    homeSagment.get_element_home_2().setVisibility(View.VISIBLE);
                    homeSagment.get_element_home_3().setVisibility(View.VISIBLE);
                    homeSagment.get_welcome_user_name().setVisibility(View.VISIBLE);
                    homeSagment.get_elemt_home_4().setVisibility(View.VISIBLE);
                    homeSagment.get_course_title_home_screen().setVisibility(View.VISIBLE);
                    homeSagment.get_home_element_5().setVisibility(View.VISIBLE);
                    homeSagment.get_findCoursesBtn().setVisibility(View.VISIBLE);
                    homeSagment.get_home_element_6().setVisibility(View.VISIBLE);
                    homeSagment.get_welcomeUserString8().setVisibility(View.VISIBLE);
                    homeSagment.get_imageView5().setVisibility(View.VISIBLE);
                    homeSagment.get_imageView17().setVisibility(View.VISIBLE);
                    homeSagment.get_textView().setVisibility(View.VISIBLE);
                    homeSagment.get_imageView9().setVisibility(View.VISIBLE);
                    homeSagment.get_welcomeUserString9().setVisibility(View.VISIBLE);
                    homeSagment.get_username_message().setVisibility(View.VISIBLE);

                });


                }).start();

            }
        }, 0, 1, TimeUnit.MINUTES);

    }







}
