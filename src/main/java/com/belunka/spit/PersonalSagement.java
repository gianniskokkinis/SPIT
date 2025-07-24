package com.belunka.spit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.sql_connect_tools.get_stats_from_data_base;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SuppressLint("ValidFragment")
public class PersonalSagement extends Fragment {
    private RecyclerView recyclerView;
    List<Course> items_for_queue = new ArrayList<Course>(); //for the reason if no items to display

    Member member;

    TextView warning_message_set, user_username, user_am, user_etos;

    MainActivity mainActivity;

    ImageView imageView11;
    ImageView imageView2;

    ProgressBar progressBar_Personal_Sagment;

    /**
     *
     * SPIT DATA BASE INFO
     *
     * */
    
    /*Fill credentials with ur database*/
    public static final String DATABASE_NAME = "";
    public static final String url = "" +
            DATABASE_NAME;

    public static final String username = "", password = "";

    public static final String TABLE_NAME = "STUDENTS";

    String json;


    get_stats_from_data_base get_info;

    ImageButton edit_course, edit_profile;


    public PersonalSagement(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal_sagement, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        imageView11 = getActivity().findViewById(R.id.imageView11);
        imageView2 = getActivity().findViewById(R.id.imageView2);
        progressBar_Personal_Sagment = getActivity().findViewById(R.id.progressBar_Personal_Sagment);
        progressBar_Personal_Sagment.setVisibility(View.GONE);
        edit_profile = getActivity().findViewById(R.id.edit_profile);
        edit_course = getActivity().findViewById(R.id.edit_course);
        edit_course.setVisibility(View.GONE);
        edit_profile.setVisibility(View.GONE);



        try{
            get_info.get_Catalogue_from_script(); //update every time we get in personal tab
        }catch (Exception e){
            //maybe here updating with the case if internet connection is null
            System.out.println("Something went wrong here");
            e.printStackTrace();
        }


        warning_message_set = getActivity().findViewById(R.id.ta_mathimata_mu);


        user_username = getActivity().findViewById(R.id.Username_field);
        user_username.setText(member.getUsername());
        user_am = getActivity().findViewById(R.id.AM_field);
        user_am.setText(member.getAM());
        user_etos = getActivity().findViewById(R.id.Etos_Field);
        user_etos.setText(member.getEtos()+"o");


        


        recyclerView = getActivity().findViewById(R.id.recyclerView_courses_queue);
//        recyclerView.setVisibility(View.GONE);
        mainActivity.set_is_from_personal_sagment(true);
        MyAdapter mAdapter = new MyAdapter(getActivity().getApplicationContext(), member.get_Member_Catalogue().get_Course_List(), null, getActivity(), member);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //maybe getActivirty().getContext()
        recyclerView.setAdapter(mAdapter);



        




    }

    

    public String utilFun() throws SQLException {
        new Thread(() -> {
            

            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                ResultSet rs = statement.executeQuery("SELECT * FROM STUDENTS");
                while (rs.next()) { //script here
                    json = rs.getString(2);
                    for (int i=0; i<10; i++){
                        System.out.println(json);
                    }
                }


                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }).start();

        return json;
    }


    public static void addTemp(String name_str, String place_str) {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                // add to RDS DB:

                statement.execute("INSERT INTO " + TABLE_NAME + "(name, place) VALUES('" + name_str + "', '" + place_str + "')");

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    //ENDS PROTOTYPE HERE



    //this functions is about synchronize personal stats from database

    public void get_Catalogue_from_script() throws SQLException {
        new Thread(() -> {
            

            mainActivity.runOnUiThread(() -> { //change UI Stuff
                //change here

                imageView11.setVisibility(View.GONE);
                user_username.setVisibility(View.GONE);
                user_am.setVisibility(View.GONE);
                user_etos.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                warning_message_set.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                progressBar_Personal_Sagment.setVisibility(View.VISIBLE);

            });

            StringBuilder records = new StringBuilder();
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                ResultSet rs = statement.executeQuery("SELECT myCourseScript FROM STUDENTS WHERE username='Giannis'");


                while (rs.next()) { //script here
                    json = rs.getString(1);
                }


                connection.close(); //we dont need anymore connection with database

                if(json == null){
                    System.out.println("ERROR NOT GATHER DATA");
                    return; //if did't get any result something went wrong
                }

                Gson gson = new Gson();
                System.out.println(json); //test

                member_my_catalogue get_course_from_script = gson.fromJson(json, member_my_catalogue.class);
                items_for_queue = get_course_from_script.get_Course_List();

                System.out.println(get_course_from_script.getCourse(0).getName());//test

                getActivity().runOnUiThread(() -> { //change UI Stuff
                    MyAdapter mAdapter = new MyAdapter(getActivity().getApplicationContext(), get_course_from_script.get_Course_List(), null, getActivity(), member);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); 
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setVisibility(View.VISIBLE); //now our item is visible

                    //here update some fields

                });


                //end connection here
            } catch (Exception e) {
                getActivity().setContentView(R.layout.no_internet_connection);
                e.printStackTrace();
            }


            mainActivity.runOnUiThread(() -> { //change UI Stuff
                //change here

                imageView11.setVisibility(View.VISIBLE);
                user_username.setVisibility(View.VISIBLE);
                user_am.setVisibility(View.VISIBLE);
                user_etos.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                warning_message_set.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                progressBar_Personal_Sagment.setVisibility(View.GONE);

            });

        }).start();



    }




    public void setMember(Member update_member){
        this.member = update_member;
    }

    public void set_getinfo(get_stats_from_data_base update_get_info){
        this.get_info = update_get_info;
    }


    public void onBackPressed(){
        //test here code
        Intent intent = getActivity().getIntent();
        super.getActivity().onBackPressed();
        startActivity(intent);

        super.getActivity().onBackPressed();
    }


    

}
