package com.belunka.spit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.belunka.spit.Background_Tasks.BackgroundService;
import com.belunka.spit.Member.Member;
import com.belunka.spit.sql_connect_tools.get_stats_from_data_base;
import com.google.gson.Gson;


public class HomeSagment extends Fragment {

    public static final String EXTRA_NUMBER ="com.belunka.spit.EXTRA_NUMBER";


    ImageButton findCoursesBtn, course_queue_today_btn;
    Activity findCoursesActivity;

    Member member;

    ImageView status_icon_home_screen;
    TextView welcome_user_name;

    TextView course_title_home_screen;
    TextView kefalaio_of_course;

    get_stats_from_data_base get_info;


    //here we initialize elments for loading screen
    ImageView element_home_1;
    ImageView element_home_2;
    ImageView element_home_3;
    ImageView elemt_home_4;
    ImageView home_element_5;
    ImageView home_element_6;
    TextView welcomeUserString8;
    ImageView imageView5;
    ImageView imageView17;
    TextView textView;
    ImageView imageView9;
    TextView welcomeUserString9;

    ProgressBar progressbar_home;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        findCoursesActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_home_sagment, container, false); //get view here
        return  rootView;
    }

    public void onStart(){
        super.onStart();

        //initialize for loading screen
        progressbar_home = getActivity().findViewById(R.id.progressbar_home);
        element_home_1 = getActivity().findViewById(R.id.element_home_1);
        element_home_2 = getActivity().findViewById(R.id.element_home_2);
        element_home_3 = getActivity().findViewById(R.id.element_home_3);
        elemt_home_4 = getActivity().findViewById(R.id.elemt_home_4);
        home_element_5 = getActivity().findViewById(R.id.home_element_5);
        home_element_6 = getActivity().findViewById(R.id.home_element_6);
        welcomeUserString8 = getActivity().findViewById(R.id.welcomeUserString8);
        imageView5 = getActivity().findViewById(R.id.imageView5);
        imageView17 = getActivity().findViewById(R.id.imageView17);
        textView = getActivity().findViewById(R.id.textView);
        imageView9 = getActivity().findViewById(R.id.imageView9);
        welcomeUserString9 = getActivity().findViewById(R.id.welcomeUserString9);

        //ends here



        findCoursesBtn = findCoursesActivity.findViewById(R.id.findCourseHomeBtn); //click listener here
        course_queue_today_btn = findCoursesActivity.findViewById(R.id.courses_queue_today); //for queue today
        status_icon_home_screen = getActivity().findViewById(R.id.set_status_home_screen_icon);

        //set here the username
        welcome_user_name = getActivity().findViewById(R.id.welcomeUserString);



        course_title_home_screen = getActivity().findViewById(R.id.course_title_display_home);
        kefalaio_of_course = getActivity().findViewById(R.id.kefalaio_of_course_home);

        /*here we are calling thread*/

        try {
            Gson gson = new Gson();
            get_info.set_home_screen_stats();

        }catch (Exception e){
            System.out.println("Something went wrong with updating home screen statements");
            e.printStackTrace();
        }





        findCoursesBtn.setOnClickListener(new View.OnClickListener() { // switch here to new activity find course
            @Override
            public void onClick(View view) {
                //set here argument for if statement
                int check = 0;
                Intent intent = new Intent(findCoursesActivity, Find_Courses_Activity.class);
                intent.putExtra(EXTRA_NUMBER, check);
                Gson gson = new Gson();
                String memberJson = gson.toJson(member);
                intent.putExtra("memberJson", memberJson);
                startActivity(intent); //activity for courses
            }
        });

        course_queue_today_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*this feature is on working on*/
                getActivity().setContentView(R.layout.coming_soon);
                //we need that for back button
                try {

                    ((MainActivity) getActivity()).setter_for_scedule(true);
                }catch (Exception E){
                    Toast.makeText(getContext(), "something went wrong in setting true", Toast.LENGTH_SHORT).show();
                }

          
            }
        });

    }


    public void setMember(Member update_member){
        this.member = update_member;
    }

    public TextView get_course_title_of_home_screen(){
        return course_title_home_screen;
    }

    public TextView get_kefalaio_course_screen(){
        return kefalaio_of_course;
    }

    public ImageView get_status_icon_home_screen(){
        return status_icon_home_screen;
    }

    public void set_get_info(get_stats_from_data_base update_get_info){
        this.get_info = update_get_info;
    }

    public ImageView  get_element_home_1(){
        return element_home_1;
    }

    public ImageView  get_element_home_2(){
        return element_home_2;
    }

    public ImageView  get_element_home_3(){
        return element_home_3;
    }

    public TextView get_welcome_user_name(){
        return welcome_user_name;
    }

    public ImageView get_elemt_home_4(){
        return elemt_home_4;
    }

    public TextView get_course_title_home_screen(){
        return course_title_home_screen;
    }

    public ImageView get_home_element_5(){
        return home_element_5;
    }

    public ImageButton get_findCoursesBtn(){
        return findCoursesBtn;
    }

    public ImageView get_home_element_6(){
        return home_element_6;
    }

    public TextView get_welcomeUserString8(){
        return welcomeUserString8;
    }


    public ImageView get_imageView5(){
        return imageView5;
    }
    public ImageView get_imageView17(){
        return imageView17;
    }
    public TextView get_textView(){
        return textView;
    }
    public ImageView get_imageView9(){
        return imageView9;
    }
    public TextView get_welcomeUserString9(){
        return welcomeUserString9;
    }

    public ProgressBar get_progressbar_home(){
        return progressbar_home;
    }


    public TextView get_username_message(){
        return welcome_user_name;
    }

}
