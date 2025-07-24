package com.belunka.spit.sql_connect_tools;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.belunka.spit.MainActivity;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.MyAdapter;
import com.belunka.spit.R;
import com.belunka.spit.myViewHolder_Schedule_Courses;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class update_course_status_to_database {
    
    /*Fill credentials with ur database*/
    public static final String DATABASE_NAME = "";
    public static final String url = "" +
            DATABASE_NAME;

    public static final String username = "", password = "";

    public static final String TABLE_NAME = "STUDENTS";
    int value;
    String courseName;

    String user_am;

    myViewHolder_Schedule_Courses myViewHolder;

    boolean allow_read;

    Activity main_activity;


    public update_course_status_to_database(int update_value, String update_courseName, String update_user_am, myViewHolder_Schedule_Courses update_myViewHolder, Activity update_main_activity){
        this.value = update_value;
        this.courseName = update_courseName;
        this.user_am = update_user_am;
        this.myViewHolder = update_myViewHolder;
        this.main_activity = update_main_activity;

    }


    public void set_allow_read(boolean update_value){
        this.allow_read = update_value;
    }

    public boolean get_allow_read(){
        return allow_read;
    }



    /**
     * THREADS
     *
     * */
    public void set_value_to_database() throws SQLException {
        new Thread(() -> {
            

            main_activity.runOnUiThread(() -> { //change UI Stuff
                myViewHolder.get_course_examine4().setVisibility(View.GONE);
                myViewHolder.get_changed_by().setVisibility(View.GONE);
                myViewHolder.get_kefalaio().setVisibility(View.GONE);
                myViewHolder.get_room_target().setVisibility(View.GONE);
                myViewHolder.get_imageView22().setVisibility(View.GONE);
                myViewHolder.get_course_title().setVisibility(View.GONE);
                myViewHolder.get_status_icon().setVisibility(View.GONE);
                myViewHolder.get_stop_button().setVisibility(View.GONE);
                myViewHolder.get_break_button().setVisibility(View.GONE);
                myViewHolder.get_start_button().setVisibility(View.GONE);
                myViewHolder.get_set_kefalaio_button().setVisibility(View.GONE);
                myViewHolder.get_get_kefalaio_plain().setVisibility(View.GONE);


                myViewHolder.get_progressBar_course_status().setVisibility(View.VISIBLE);

            });

            StringBuilder records = new StringBuilder();
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                String sql = "UPDATE COURSES SET is_active_now = ?,last_user_update_am = ? WHERE course_title = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setInt(1,value); //update here value field
                stmt.setString(2,user_am);
                stmt.setString(3,courseName); //search via course name

                int rowsAffected = stmt.executeUpdate();

                stmt.close();
                connection.close();

                //end connection here
            } catch (Exception e) {
                e.printStackTrace();
            }


            main_activity.runOnUiThread(() -> { //change UI Stuff
                myViewHolder.get_progressBar_course_status().setVisibility(View.GONE);
                myViewHolder.get_course_examine4().setVisibility(View.VISIBLE);
                myViewHolder.get_changed_by().setVisibility(View.VISIBLE);
                myViewHolder.get_kefalaio().setVisibility(View.VISIBLE);
                myViewHolder.get_room_target().setVisibility(View.VISIBLE);
                myViewHolder.get_imageView22().setVisibility(View.VISIBLE);
                myViewHolder.get_course_title().setVisibility(View.VISIBLE);
                myViewHolder.get_status_icon().setVisibility(View.VISIBLE);
                myViewHolder.get_stop_button().setVisibility(View.VISIBLE);
                myViewHolder.get_break_button().setVisibility(View.VISIBLE);
                myViewHolder.get_start_button().setVisibility(View.VISIBLE);
                myViewHolder.get_set_kefalaio_button().setVisibility(View.VISIBLE);
                myViewHolder.get_get_kefalaio_plain().setVisibility(View.VISIBLE);




            });


        }).start();



    }



    public void get_kefalaio_of_course_from_database() throws SQLException {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //info for user
                for (int i=0; i<10; i++){
                    System.out.println("New thread start");
                    Log.d("Thread", "New thread start");
                }

                new Thread(() -> {

                    //visible
                    main_activity.runOnUiThread(() -> { //change on ui thread changes
                        myViewHolder.get_course_examine4().setVisibility(View.GONE);
                        myViewHolder.get_changed_by().setVisibility(View.GONE);
                        myViewHolder.get_kefalaio().setVisibility(View.GONE);
                        myViewHolder.get_room_target().setVisibility(View.GONE);
                        myViewHolder.get_imageView22().setVisibility(View.GONE);
                        myViewHolder.get_course_title().setVisibility(View.GONE);
                        myViewHolder.get_status_icon().setVisibility(View.GONE);
                        myViewHolder.get_stop_button().setVisibility(View.GONE);
                        myViewHolder.get_break_button().setVisibility(View.GONE);
                        myViewHolder.get_start_button().setVisibility(View.GONE);
                        myViewHolder.get_set_kefalaio_button().setVisibility(View.GONE);
                        myViewHolder.get_get_kefalaio_plain().setVisibility(View.GONE);


                        myViewHolder.get_progressBar_course_status().setVisibility(View.VISIBLE);

                    });

                    //do your work THREAD

                    StringBuilder records = new StringBuilder();
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, username, password);
                        Statement statement = connection.createStatement();
                        System.out.println("CONNECTION SUCCED");

                        String sql = "SELECT course_kefalaio FROM COURSES WHERE course_title = ?";
                        PreparedStatement stmt = connection.prepareStatement(sql);


                        stmt.setString(1, courseName);
                        Log.d("CourseName:", courseName);
                        System.out.println("sql: "+stmt.toString());

                        //read kefalaio
                        ResultSet rs = stmt.executeQuery();
                        String output="";
                        while(rs.next()){
                            Log.d("RS", "RS IS WRITING NOW ");//test
                            //set kefalaio here
                            output = rs.getString(1);
                        }
                        System.out.println("Output: "+output);
                        //update here the fields

                        if (output == null || (output.length()==0) ){
                            //DEN EVALE KANENAS KEFALAIO
                            main_activity.runOnUiThread(() -> { //change UI Stuff
                                myViewHolder.get_kefalaio().setText("Δεν δήλωσε κανείς");
                            });

                        }else{
                            //update kefalaio
                            String finalOutput = output;
                            main_activity.runOnUiThread(() -> { //change on ui thread changes
                                myViewHolder.get_kefalaio().setText(finalOutput+"(για να το αλλάξετε πατήστε εδώ)");
                            });

                        }
                        rs.close();
                        connection.close();

                        //end connection here
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //unvisible
                    main_activity.runOnUiThread(() -> { //change UI Stuff
                        myViewHolder.get_progressBar_course_status().setVisibility(View.GONE);
                        myViewHolder.get_course_examine4().setVisibility(View.VISIBLE);
                        myViewHolder.get_changed_by().setVisibility(View.VISIBLE);
                        myViewHolder.get_kefalaio().setVisibility(View.VISIBLE);
                        myViewHolder.get_room_target().setVisibility(View.VISIBLE);
                        myViewHolder.get_imageView22().setVisibility(View.VISIBLE);
                        myViewHolder.get_course_title().setVisibility(View.VISIBLE);
                        myViewHolder.get_status_icon().setVisibility(View.VISIBLE);
                        myViewHolder.get_stop_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_break_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_start_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_set_kefalaio_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_get_kefalaio_plain().setVisibility(View.VISIBLE);




                    });

                }).start();

                if (allow_read == false){
                    scheduler.shutdown(); //shutdown here
                }
            }
        }, 0, 1, TimeUnit.MINUTES);




    }


    public void set_kefalaio_of_course_from_database(String set_Kefalaio) throws SQLException {
        new Thread(() -> {

            //invisible
            main_activity.runOnUiThread(() -> { //change UI Stuff
                myViewHolder.get_course_examine4().setVisibility(View.GONE);
                myViewHolder.get_changed_by().setVisibility(View.GONE);
                myViewHolder.get_kefalaio().setVisibility(View.GONE);
                myViewHolder.get_room_target().setVisibility(View.GONE);
                myViewHolder.get_imageView22().setVisibility(View.GONE);
                myViewHolder.get_course_title().setVisibility(View.GONE);
                myViewHolder.get_status_icon().setVisibility(View.GONE);
                myViewHolder.get_stop_button().setVisibility(View.GONE);
                myViewHolder.get_break_button().setVisibility(View.GONE);
                myViewHolder.get_start_button().setVisibility(View.GONE);
                myViewHolder.get_set_kefalaio_button().setVisibility(View.GONE);
                myViewHolder.get_get_kefalaio_plain().setVisibility(View.GONE);


                myViewHolder.get_progressBar_course_status().setVisibility(View.VISIBLE);

            });

            StringBuilder records = new StringBuilder();
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                String sql = "UPDATE COURSES SET course_kefalaio = ?,last_user_update_am = ? WHERE course_title = ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1,set_Kefalaio); //update here value field
                stmt.setString(2,user_am);
                stmt.setString(3,courseName); //search via course name

                int rowsAffected = stmt.executeUpdate();

                stmt.close();
                connection.close();

                //end connection here
            } catch (Exception e) {
                e.printStackTrace();
            }


            main_activity.runOnUiThread(() -> { //change UI Stuff
                myViewHolder.get_progressBar_course_status().setVisibility(View.GONE);
                myViewHolder.get_course_examine4().setVisibility(View.VISIBLE);
                myViewHolder.get_changed_by().setVisibility(View.VISIBLE);
                myViewHolder.get_kefalaio().setVisibility(View.VISIBLE);
                myViewHolder.get_room_target().setVisibility(View.VISIBLE);
                myViewHolder.get_imageView22().setVisibility(View.VISIBLE);
                myViewHolder.get_course_title().setVisibility(View.VISIBLE);
                myViewHolder.get_status_icon().setVisibility(View.VISIBLE);
                myViewHolder.get_stop_button().setVisibility(View.VISIBLE);
                myViewHolder.get_break_button().setVisibility(View.VISIBLE);
                myViewHolder.get_start_button().setVisibility(View.VISIBLE);
                myViewHolder.get_set_kefalaio_button().setVisibility(View.VISIBLE);
                myViewHolder.get_get_kefalaio_plain().setVisibility(View.VISIBLE);

            });


        }).start();



    }



    public void get_status_of_course() throws SQLException {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {


                new Thread(() -> {

                    //visible
                    main_activity.runOnUiThread(() -> { //change UI Stuff
                        myViewHolder.get_course_examine4().setVisibility(View.GONE);
                        myViewHolder.get_changed_by().setVisibility(View.GONE);
                        myViewHolder.get_kefalaio().setVisibility(View.GONE);
                        myViewHolder.get_room_target().setVisibility(View.GONE);
                        myViewHolder.get_imageView22().setVisibility(View.GONE);
                        myViewHolder.get_course_title().setVisibility(View.GONE);
                        myViewHolder.get_status_icon().setVisibility(View.GONE);
                        myViewHolder.get_stop_button().setVisibility(View.GONE);
                        myViewHolder.get_break_button().setVisibility(View.GONE);
                        myViewHolder.get_start_button().setVisibility(View.GONE);
                        myViewHolder.get_set_kefalaio_button().setVisibility(View.GONE);
                        myViewHolder.get_get_kefalaio_plain().setVisibility(View.GONE);
                        myViewHolder.get_progressBar_course_status().setVisibility(View.VISIBLE);

                    });

                    StringBuilder records = new StringBuilder();
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, username, password);
                        Statement statement = connection.createStatement();
                        System.out.println("CONNECTION SUCCED");

                        String sql = "SELECT is_active_now,last_user_update_am FROM COURSES WHERE course_title = ?";
                        PreparedStatement stmt = connection.prepareStatement(sql);


                        stmt.setString(1, courseName);
                        Log.d("CourseName:", courseName);
                        System.out.println("sql: "+stmt.toString());

                        //read kefalaio
                        ResultSet rs = stmt.executeQuery();
                        int output=0;
                        String am=""; //this is to get update am
                        while(rs.next()){
                            Log.d("RS", "RS IS READING NOW ");//test
                            //set kefalaio here
                            output = rs.getInt(1); //get status
                            am = rs.getString(2); //get am
                        }
                        System.out.println("Output: "+output);
                        //update here the fields

                        if (output == 1){ //if is active
                            String finalAm = am;
                            main_activity.runOnUiThread(() -> { //change on ui thread changes
                                myViewHolder.get_status_icon().setImageDrawable(main_activity.getDrawable(R.drawable.active_course));
                                myViewHolder.get_changed_by().setText("Changed by "+ finalAm);
                            });
                        }
                        if (output == 0){ //if is active
                            String finalAm = am;
                            main_activity.runOnUiThread(() -> { //change on ui thread changes
                                myViewHolder.get_status_icon().setImageDrawable(main_activity.getDrawable(R.drawable.non_active_course));
                                myViewHolder.get_changed_by().setText("Changed by "+ finalAm);
                            });
                        }
                        if (output == 2){ //if is active
                            String finalAm = am;
                            main_activity.runOnUiThread(() -> { //change on ui thread changes
                                myViewHolder.get_status_icon().setImageDrawable(main_activity.getDrawable(R.drawable.course_break));
                                myViewHolder.get_changed_by().setText("Changed by "+ finalAm);
                            });
                        }

                        rs.close();
                        connection.close();
                        

                        //end connection here
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //invisible
                    main_activity.runOnUiThread(() -> { //change UI Stuff
                        myViewHolder.get_progressBar_course_status().setVisibility(View.GONE);
                        myViewHolder.get_course_examine4().setVisibility(View.VISIBLE);
                        myViewHolder.get_changed_by().setVisibility(View.VISIBLE);
                        myViewHolder.get_kefalaio().setVisibility(View.VISIBLE);
                        myViewHolder.get_room_target().setVisibility(View.VISIBLE);
                        myViewHolder.get_imageView22().setVisibility(View.VISIBLE);
                        myViewHolder.get_course_title().setVisibility(View.VISIBLE);
                        myViewHolder.get_status_icon().setVisibility(View.VISIBLE);
                        myViewHolder.get_stop_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_break_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_start_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_set_kefalaio_button().setVisibility(View.VISIBLE);
                        myViewHolder.get_get_kefalaio_plain().setVisibility(View.VISIBLE);




                    });


                }).start();

                if (allow_read == false){
                    scheduler.shutdown(); //shutdown here
                }
            }
        }, 0, 1, TimeUnit.MINUTES);




    }




}
