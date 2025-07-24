package com.belunka.spit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Course_Programma_Katalogos.ProgrammaMathimaton;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.Schedule_Plan.Schedule_Program;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ScheduleSagement extends Fragment {

    public static final String EXTRA_NUMBER ="com.belunka.spit.EXTRA_NUMBER";
    private RecyclerView recyclerView;
    ImageButton button_course_status, search_courses;
    ProgrammaMathimaton programmaMathimaton;
    Member member;
    //to test
    private List<Course> items_for_schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule_sagement, container, false);
        return root;
    }

    @Override
    public void onStart(){
        super.onStart();

        

        recyclerView = getActivity().findViewById(R.id.schedule_recycler_view);


        

        Schedule_Program schedule_program = new Schedule_Program();







        



        CalendarView calendarView = getActivity().findViewById(R.id.calendarView);


        set_default_list(schedule_program);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                // Create a Calendar instance with the selected date
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                // Get the name of the day corresponding to the selected date
                String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());


                // Store the selected date in a string variable
                String output = dayOfMonth + "-" + (month + 1) + "-" + year + "( " + dayOfWeek + " )";


                // Do something with the selected date
                Log.d("SelectedDate", output);
                Toast.makeText(getContext(), output, Toast.LENGTH_SHORT).show();





                /*Here we are checking dates*/

                // MAYBE HERE WE NEED BETTER SEARCH
                items_for_schedule = new ArrayList<Course>();
                for (int i=0; i<schedule_program.getMathimata().size(); i++){

                    if (schedule_program.getMathimata().get(i).getTeaching_days() == null || schedule_program.getMathimata().get(i).getTeaching_hours() == null){
                        /*do nothing just skip*/
                    }else{
                        Log.d("Course: ", schedule_program.getMathimata().get(i).getName());

                        Log.d("Course Output:",schedule_program.getMathimata().get(i).getName()+":"+schedule_program.getMathimata().get(i).getTeaching_days().contains(dayOfWeek));//test
                        if (schedule_program.getMathimata().get(i).getTeaching_days().contains(dayOfWeek)){ //check dates today
                            items_for_schedule.add(schedule_program.getMathimata().get(i));
                        }

                    }

                }

                //sort here


                MyAdapter_Schedule_Courses mScheduleAdapter = new MyAdapter_Schedule_Courses(getActivity().getApplicationContext(), items_for_schedule, getActivity(), getActivity(), member, dayOfWeek);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(mScheduleAdapter);


            }
        });






        button_course_status = getActivity().findViewById(R.id.course_status_button);
        search_courses = getActivity().findViewById(R.id.search_courses);

        button_course_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setContentView(R.layout.coming_soon);
                try {

                    ((MainActivity) getActivity()).setter_for_scedule(true);
                }catch (Exception E){
                    Toast.makeText(getContext(), "something went wrong in setting true", Toast.LENGTH_SHORT).show();
                }

            }
        });

        search_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = 0;
                Intent intent = new Intent(getActivity(), Find_Courses_Activity.class);
                intent.putExtra(EXTRA_NUMBER, check);
                startActivity(intent); //activity for courses
            }
        });



    }

    public void setMember(Member update_member){
        this.member = update_member;
    }


    public List<Course> get_items_for_schedule(){
        return items_for_schedule;
    }


    public List<Course> sort_by_times(List<Course> courses){
        List<Course> sorted_List = new ArrayList<Course>();





        return null;
    }


    private void set_default_list(Schedule_Program schedule_program){
        Calendar calendar = Calendar.getInstance();
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
        Toast.makeText(getContext(), "Today", Toast.LENGTH_SHORT).show();
        // MAYBE HERE WE NEED BETTER SEARCH
        items_for_schedule = new ArrayList<Course>();
        for (int i=0; i<schedule_program.getMathimata().size(); i++){

            if (schedule_program.getMathimata().get(i).getTeaching_days() == null || schedule_program.getMathimata().get(i).getTeaching_hours() == null){
                /*do nothing just skip*/
            }else{
                Log.d("Course: ", schedule_program.getMathimata().get(i).getName());

                Log.d("Course Output:",schedule_program.getMathimata().get(i).getName()+":"+schedule_program.getMathimata().get(i).getTeaching_days().contains(dayOfWeek));//test
                if (schedule_program.getMathimata().get(i).getTeaching_days().contains(dayOfWeek)){ //check dates today
                    items_for_schedule.add(schedule_program.getMathimata().get(i));
                }

            }

        }

        //sort here


        MyAdapter_Schedule_Courses mScheduleAdapter = new MyAdapter_Schedule_Courses(getActivity().getApplicationContext(), items_for_schedule, getActivity(), getActivity(), member, dayOfWeek);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mScheduleAdapter);


    }
}
