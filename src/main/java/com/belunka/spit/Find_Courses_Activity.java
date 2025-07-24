package com.belunka.spit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Schedule_Plan.Schedule_Program;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Find_Courses_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    boolean is_course_info_now = false;

    Schedule_Program schedule_program = new Schedule_Program();
    private List<Course> mathimata;

    Member member;

    private SearchView searchView;

    //this list is about collecting courses from search
    List<Course> get_filteredList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        int number = intent.getIntExtra(HomeSagment.EXTRA_NUMBER, 0);

        //update member here
        String memberJson = getIntent().getStringExtra("memberJson");
        Gson gson = new Gson();
        Member member = gson.fromJson(memberJson, Member.class);


        Toast.makeText(this, "Check "+number, Toast.LENGTH_SHORT).show();//test
        if (number == 1){ /*this code is about display queue*/

            setContentView(R.layout.display_course_queue);

            mathimata = schedule_program.getMathimata();
            MyAdapter mAdapter = new MyAdapter(getApplicationContext(), mathimata, this, null, member);
            recyclerView = findViewById(R.id.recyclerView_courses_queue);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(mAdapter);

            /*more info here*/


            //this is for


        }else{  /*this code is about search course*/

            setContentView(R.layout.activity_find_courses);
        //need here something to remove top bar


        //initialize here courses





        MyAdapter mAdapter = new MyAdapter(getApplicationContext(), schedule_program.getMathimata(), this, null, member);
        recyclerView = findViewById(R.id.recyclerView_courses_queue);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        //need something here for add listener



        searchView = findViewById(R.id.searchView_Courses);
        //and searchview focus
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s, mAdapter);
                return false;
            }
        });


        //maybe signal here

        }

    }

    private void filterList(String s, MyAdapter myAdapter) {
        List<Course> filteredList = new ArrayList<>();
        for (Course course : schedule_program.getMathimata()){
            if (course.getName().toLowerCase().contains(s.toLowerCase())){
                filteredList.add(course);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this,"No course found", Toast.LENGTH_SHORT).show();
        }else{
            myAdapter.setFilteredList(filteredList);
            
            get_filteredList = filteredList;

        }

    }


    @Override
    public void onBackPressed(){
        //test here code
        if (is_course_info_now){
            Intent intent = getIntent();
            super.onBackPressed();
            startActivity(intent);

        }
        super.onBackPressed();
    }





}
