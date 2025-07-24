package com.belunka.spit.Member;

import com.belunka.spit.CoursePackage.Course;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class member_my_catalogue {

    @SerializedName("my_courses_display")
    List<Course> my_courses_display = new ArrayList<Course>();

    public void add_Course(Course newCourse){
        my_courses_display.add(newCourse);
    }

    public void add_Course(Course newCourse, int i){
        my_courses_display.add(i, newCourse);
    }

    public Course removeCourse(Course removed_Course){
        Course returnCourse = removed_Course;
        my_courses_display.remove(removed_Course);
        return returnCourse;
    }


    public Course removeCourse(int i){
        Course returnCourse = my_courses_display.get(i);
        my_courses_display.remove(i);
        return returnCourse;
    }

    public List<Course> get_Course_List(){
        return my_courses_display;
    }

    public void set_Course_List(List<Course> update_course){
        this.my_courses_display = update_course;
    }



    public Course getCourse(int i){
        return my_courses_display.get(i);
    }

//    public static void main(String[] args){
//
//        String s = "Σύνδεση\\ncs05109,";
//
//
//
//
//    }



}
