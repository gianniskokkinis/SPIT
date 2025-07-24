package com.belunka.spit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class myViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView courseTitle, courseDesc;
    Activity edit_course_activity;
    Button view_course_btn;
    Button remove_from_library, add_to_library;
    int position = 0;
    Find_Courses_Activity fCourse_inside;
    Activity mainActivity; 

    Member member;

    TextView title;
    TextView examino_info;
    TextView etos_info;
    TextView course_info_section;
    TextView course_teacher_name;

    ImageView imageView12;
    TextView course_examine2;

    ProgressBar progressBar_course_info;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview_course_thunbail); //initialize here imageview
        courseTitle = itemView.findViewById(R.id.name_course_title);
        courseDesc = itemView.findViewById(R.id.course_time_display);
        view_course_btn = itemView.findViewById(R.id.course_view_button);
        Context context = itemView.getContext();
        






        view_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(view.getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();//test
                position = getAdapterPosition();
//                Toast.makeText(view.getContext(), "Position "+position, Toast.LENGTH_SHORT).show(); //test




//                fCourse_inside.is_course_info_now = true;
                if (fCourse_inside != null){

                    fCourse_inside.setContentView(R.layout.course_info); //this is for connext
                    progressBar_course_info = fCourse_inside.findViewById(R.id.progressBar_course_info);
                    progressBar_course_info.setVisibility(View.GONE);
                    //here we are updating stats about subject
                    imageView12 = fCourse_inside.findViewById(R.id.imageView12);
                    course_examine2 = fCourse_inside.findViewById(R.id.course_examine2);
                    title = fCourse_inside.findViewById(R.id.course_title);
                    examino_info = fCourse_inside.findViewById(R.id.course_examine);
                    etos_info =  fCourse_inside.findViewById(R.id.course_etos);
                    course_info_section = fCourse_inside.findViewById(R.id.course_info_section);
                    course_teacher_name = fCourse_inside.findViewById(R.id.course_teacher_name);

                    if (fCourse_inside.get_filteredList.isEmpty()){

                        if (fCourse_inside.schedule_program.getMathimata().get(position).getName().length() > 32){
                            title.setText(fCourse_inside.schedule_program.getMathimata().get(position).getName().substring(0,25)+"..."); //set title here
                        }else{
                            title.setText(fCourse_inside.schedule_program.getMathimata().get(position).getName()); //set title here
                        }

                        examino_info.setText("Εξάμηνο: "+fCourse_inside.schedule_program.getMathimata().get(position).getExamine()+ "ο ("+fCourse_inside.schedule_program.getMathimata().get(position).getExaminoString()+") "); //set examino here
                        etos_info.setText("Έτος: "+fCourse_inside.schedule_program.getMathimata().get(position).getEtos()+"ο");
                        course_info_section.setText(fCourse_inside.schedule_program.getMathimata().get(position).getInfo());
                        course_teacher_name.setText("Καθηγητής: "+ fCourse_inside.schedule_program.getMathimata().get(position).getTeacherName());

                        //need buttons here
                        remove_from_library = fCourse_inside.findViewById(R.id.remove_from_library);
                        add_to_library = fCourse_inside.findViewById(R.id.add_to_library);
                        remove_from_library.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (member.get_Member_Catalogue().get_Course_List().size()==1){
                                    Toast.makeText(view.getContext(), "You need to add 1 more course to delete this course from your library", Toast.LENGTH_SHORT).show();
                                }

                                else if (member.get_Member_Catalogue().get_Course_List().size()==0){
                                    Toast.makeText(view.getContext(), "Your library is empty", Toast.LENGTH_SHORT).show();
                                }else if (is_course_in_my_list(member.get_Member_Catalogue().get_Course_List(), fCourse_inside.schedule_program.getMathimata().get(position).getName())){
                                    Gson gson = new Gson();
                                    //remove here
                                    member.get_Member_Catalogue().get_Course_List().remove(get_index_of_course(member.get_Member_Catalogue().get_Course_List(),fCourse_inside.schedule_program.getMathimata().get(position).getName()));

                                    String json = gson.toJson(member.get_Member_Catalogue());
                                    try {
                                        update_data_base_my_courses(json);
                                        Toast.makeText(view.getContext(), "Course removed from your Library", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }else{
                                    Toast.makeText(view.getContext(), "This course is not in your library", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        add_to_library.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Gson gson = new Gson();
                                if (member.get_Member_Catalogue().get_Course_List().contains(fCourse_inside.schedule_program.getMathimata().get(position))){
                                    Toast.makeText(view.getContext(), "You have already added this course", Toast.LENGTH_SHORT).show();
                                }
                                if (member.get_Member_Catalogue().get_Course_List().size() == 9){
                                    Toast.makeText(view.getContext(), "You reached the limit of 9 courses, please remove 1 course to continue", Toast.LENGTH_SHORT).show();
                                }else{
                                    member.get_Member_Catalogue().get_Course_List().add(fCourse_inside.schedule_program.getMathimata().get(position));
                                    String json = gson.toJson(member.get_Member_Catalogue());
                                    try {
                                        update_data_base_my_courses(json);
                                        Toast.makeText(view.getContext(), "Course added to your Library", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }



                            }
                        });
                        //continue with this code

                    }else{ //if user click search button

                        if (fCourse_inside.get_filteredList.get(position).getName().length() > 32){
                            title.setText(fCourse_inside.get_filteredList.get(position).getName().substring(0,25)+"..."); //set title here
                        }else{
                            title.setText(fCourse_inside.get_filteredList.get(position).getName()); //set title here
                        }

                        examino_info.setText("Εξάμηνο: "+fCourse_inside.get_filteredList.get(position).getExamine()+ "ο ("+fCourse_inside.schedule_program.getMathimata().get(position).getExaminoString()+") "); //set examino here
                        etos_info.setText("Έτος: "+fCourse_inside.get_filteredList.get(position).getEtos()+"ο");
                        course_info_section.setText(fCourse_inside.get_filteredList.get(position).getInfo());
                        course_teacher_name.setText("Καθηγητής: "+ fCourse_inside.get_filteredList.get(position).getTeacherName());

                        //need buttons here
                        remove_from_library = fCourse_inside.findViewById(R.id.remove_from_library);
                        add_to_library = fCourse_inside.findViewById(R.id.add_to_library);
                        remove_from_library.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //test
                                if (member.get_Member_Catalogue().get_Course_List().size()==1){
                                    Toast.makeText(view.getContext(), "You need to add 1 more course to delete this course from your library", Toast.LENGTH_SHORT).show();
                                }

                                else if (member.get_Member_Catalogue().get_Course_List().size()==0){
                                    Toast.makeText(view.getContext(), "Your library is empty", Toast.LENGTH_SHORT).show();
                                }else if (is_course_in_my_list(member.get_Member_Catalogue().get_Course_List(), fCourse_inside.get_filteredList.get(position).getName())){
                                    Gson gson = new Gson();
                                    //remove here
                                    member.get_Member_Catalogue().get_Course_List().remove(get_index_of_course(member.get_Member_Catalogue().get_Course_List(),fCourse_inside.get_filteredList.get(position).getName()));

                                    String json = gson.toJson(member.get_Member_Catalogue());
                                    try {
                                        update_data_base_my_courses(json);
                                        Toast.makeText(view.getContext(), "Course removed from your Library", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }else{
                                    Toast.makeText(view.getContext(), "This course is not in your library", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        add_to_library.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Gson gson = new Gson();
                                if (member.get_Member_Catalogue().get_Course_List().size()==0){
                                    //for the case if List is empty
                                    member.get_Member_Catalogue().get_Course_List().add(fCourse_inside.get_filteredList.get(position));
                                    String json = gson.toJson(member.get_Member_Catalogue());
                                    try {
                                        update_data_base_my_courses(json);
                                        Toast.makeText(view.getContext(), "Course added to your Library", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }
                                if (member.get_Member_Catalogue().get_Course_List().contains(fCourse_inside.get_filteredList.get(position))){
                                    Toast.makeText(view.getContext(), "You have already added this course", Toast.LENGTH_SHORT).show();
                                }
                                if (member.get_Member_Catalogue().get_Course_List().size() == 9){
                                    Toast.makeText(view.getContext(), "You reached the limit of 9 courses, please remove 1 course to continue", Toast.LENGTH_SHORT).show();
                                }else{
                                    member.get_Member_Catalogue().get_Course_List().add(fCourse_inside.get_filteredList.get(position));
                                    String json = gson.toJson(member.get_Member_Catalogue());
                                    try {
                                        update_data_base_my_courses(json);
                                        Toast.makeText(view.getContext(), "Course added to your Library", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                        //continue with this code

                    }



                }else{
                    mainActivity.setContentView(R.layout.course_info);
                    progressBar_course_info = mainActivity.findViewById(R.id.progressBar_course_info);
                    progressBar_course_info.setVisibility(View.GONE);
                    imageView12 = mainActivity.findViewById(R.id.imageView12);
                    course_examine2 = mainActivity.findViewById(R.id.course_examine2);
                    //we need that for back button
                    try {
                        ((MainActivity) mainActivity).setter_for_scedule(true);
                    }catch (Exception E){
                        Toast.makeText(context, "something went wrong in setting true", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(context, "Position: "+position, Toast.LENGTH_SHORT).show();

                    title = mainActivity.findViewById(R.id.course_title);
                    examino_info = mainActivity.findViewById(R.id.course_examine);
                    etos_info = mainActivity.findViewById(R.id.course_etos);
                    course_info_section = mainActivity.findViewById(R.id.course_info_section);
                    course_teacher_name = mainActivity.findViewById(R.id.course_teacher_name);

                    if (member.get_Member_Catalogue().get_Course_List().get(position).getName().length() > 32){
                        title.setText(member.get_Member_Catalogue().get_Course_List().get(position).getName().substring(0,25)+"..."); //set title here
                    }else{
                        title.setText(member.get_Member_Catalogue().get_Course_List().get(position).getName()); //set title here
                    }

                    examino_info.setText("Εξάμηνο: "+member.get_Member_Catalogue().get_Course_List().get(position).getExamine()+ "ο ("+member.get_Member_Catalogue().get_Course_List().get(position).getExaminoString()+") "); //set examino here
                    etos_info.setText("Έτος: "+member.get_Member_Catalogue().get_Course_List().get(position).getEtos()+"ο");
                    course_info_section.setText(member.get_Member_Catalogue().get_Course_List().get(position).getInfo());
                    course_teacher_name.setText("Καθηγητής: "+ member.get_Member_Catalogue().get_Course_List().get(position).getTeacherName());

                    //need buttons here
                    remove_from_library = mainActivity.findViewById(R.id.remove_from_library);
                    add_to_library = mainActivity.findViewById(R.id.add_to_library);
                    remove_from_library.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //test
                            if (member.get_Member_Catalogue().get_Course_List().size()==1){
                                Toast.makeText(view.getContext(), "You need to add 1 more course to delete this course from your library", Toast.LENGTH_SHORT).show();
                            }

                            else if (member.get_Member_Catalogue().get_Course_List().size()==0){
                                Toast.makeText(view.getContext(), "Your library is empty", Toast.LENGTH_SHORT).show();
                            }else if (is_course_in_my_list(member.get_Member_Catalogue().get_Course_List(), member.get_Member_Catalogue().get_Course_List().get(position).getName())){
                                Gson gson = new Gson();
                                //remove here
                                member.get_Member_Catalogue().get_Course_List().remove(get_index_of_course(member.get_Member_Catalogue().get_Course_List(),member.get_Member_Catalogue().get_Course_List().get(position).getName()));

                                String json = gson.toJson(member.get_Member_Catalogue());
                                try {
                                    update_data_base_my_courses(json);
                                    Toast.makeText(view.getContext(), "Course removed from your Library", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }else{
                                Toast.makeText(view.getContext(), "This course is not in your library", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    add_to_library.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Gson gson = new Gson();
                            if (member.get_Member_Catalogue().get_Course_List().size()==0){
                                //for the case if List is empty
                                member.get_Member_Catalogue().get_Course_List().add(member.get_Member_Catalogue().get_Course_List().get(position));
                                String json = gson.toJson(member.get_Member_Catalogue());
                                try {
                                    update_data_base_my_courses(json);
                                    Toast.makeText(view.getContext(), "Course added to your Library", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                            if (member.get_Member_Catalogue().get_Course_List().contains(member.get_Member_Catalogue().get_Course_List().get(position))){
                                Toast.makeText(view.getContext(), "You have already added this course", Toast.LENGTH_SHORT).show();
                            }
                            if (member.get_Member_Catalogue().get_Course_List().size() == 9){
                                Toast.makeText(view.getContext(), "You reached the limit of 9 courses, please remove 1 course to continue", Toast.LENGTH_SHORT).show();
                            }else{
                                member.get_Member_Catalogue().get_Course_List().add(member.get_Member_Catalogue().get_Course_List().get(position));
                                String json = gson.toJson(member.get_Member_Catalogue());
                                try {
                                    update_data_base_my_courses(json);
                                    Toast.makeText(view.getContext(), "Course added to your Library", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Toast.makeText(view.getContext(), "Something went wrong in connecting to library", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                        }
                    });



                }

            }
        });


    }

    public boolean is_course_in_my_list(List<Course> check_list, String course_name){
        for (int i=0; i<check_list.size(); i++){
            if (check_list.get(i).getName().equals(course_name)){
                return true;
            }
        }
        return false;
    }

    public int get_index_of_course(List<Course> check_list, String course_name){
        for (int i=0; i<check_list.size(); i++){
            if (check_list.get(i).getName().equals(course_name)){
                return i;
            }
        }
        return -1;
    }

    public int get_pos(){
        return position;
    }

    public void setfCourse_inside(Find_Courses_Activity fCourse_inside){
        this.fCourse_inside = fCourse_inside;
    }

    public void setMainActivity(Activity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void set_member(Member update_member){
        this.member = update_member;
    }


    //threads here

    /** Fill database info here */
    public void update_data_base_my_courses(String json_script) throws SQLException {

        final String DATABASE_NAME = "";
        final String url = "" +
                DATABASE_NAME;

        final String username = "", password = "";



        new Thread(() -> {
            

            //visible

            if (mainActivity != null){
                mainActivity.runOnUiThread(() -> { //change UI Stuff
                    title.setVisibility(View.GONE);
                    examino_info.setVisibility(View.GONE);
                    etos_info.setVisibility(View.GONE);
                    imageView12.setVisibility(View.GONE);
                    course_examine2.setVisibility(View.GONE);
                    course_teacher_name.setVisibility(View.GONE);
                    course_info_section.setVisibility(View.GONE);
                    remove_from_library.setVisibility(View.GONE);
                    add_to_library.setVisibility(View.GONE);
                    progressBar_course_info.setVisibility(View.VISIBLE);
                });
            }else{
                fCourse_inside.runOnUiThread(() -> { //change UI Stuff
                    title.setVisibility(View.GONE);
                    examino_info.setVisibility(View.GONE);
                    etos_info.setVisibility(View.GONE);
                    imageView12.setVisibility(View.GONE);
                    course_examine2.setVisibility(View.GONE);
                    course_teacher_name.setVisibility(View.GONE);
                    course_info_section.setVisibility(View.GONE);
                    remove_from_library.setVisibility(View.GONE);
                    add_to_library.setVisibility(View.GONE);
                    progressBar_course_info.setVisibility(View.VISIBLE);


                });
            }




            StringBuilder records = new StringBuilder();
            try {


                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                System.out.println("CONNECTION SUCCED");

                String sql = "UPDATE STUDENTS SET myCourseScript = ? WHERE AM = ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1,json_script); //we are updating database here
                stmt.setString(2,member.getAM()); //we are updating database here


                int rowsAffected = stmt.executeUpdate();

                stmt.close();
                connection.close();

                //end connection here
            } catch (Exception e) {
                e.printStackTrace();
            }


            //visible
            if (mainActivity != null){
                mainActivity.runOnUiThread(() -> { //change UI Stuff
                    progressBar_course_info.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    examino_info.setVisibility(View.VISIBLE);
                    etos_info.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    course_examine2.setVisibility(View.VISIBLE);
                    course_teacher_name.setVisibility(View.VISIBLE);
                    course_info_section.setVisibility(View.VISIBLE);
                    remove_from_library.setVisibility(View.VISIBLE);
                    add_to_library.setVisibility(View.VISIBLE);

                });
            }else{
                fCourse_inside.runOnUiThread(() -> { //change UI Stuff
                    progressBar_course_info.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    examino_info.setVisibility(View.VISIBLE);
                    etos_info.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    course_examine2.setVisibility(View.VISIBLE);
                    course_teacher_name.setVisibility(View.VISIBLE);
                    course_info_section.setVisibility(View.VISIBLE);
                    remove_from_library.setVisibility(View.VISIBLE);
                    add_to_library.setVisibility(View.VISIBLE);


                });
            }


        }).start();



    }







}
