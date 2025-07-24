package com.belunka.spit.Course_Programma_Katalogos;

import com.belunka.spit.CoursePackage.Course;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaMathimaton {

    List<Course> courses; //this contains the program

    List<String> argies_Dates;



    public List<Course> get_Katalogo_Me_Wres(){
        return this.courses;
    }

    public void set_Katalogo_Me_Wres(List<Course> update_course){
        this.courses = update_course;
    }



    public static void main(String[] args){
        Course course = new Course("Course1", "NO_TEACHER",1,1,"info","Earino","Ypoxrewtiko");
        List<String> teaching_hours = new ArrayList<String>();
        List<String> teaching_days = new ArrayList<String>();
        teaching_days.add("Monday");
        teaching_hours.add("16:00-18:00");
        teaching_days.add("Thursday");
        teaching_hours.add("16:00-18:00");
        teaching_days.add("Friday");
        teaching_hours.add("18:00-20:00");

        course.set_TeachingDays(teaching_days);
        course.set_TeachingHours(teaching_hours);

        ProgrammaMathimaton programmaMathimaton = new ProgrammaMathimaton();
        List<Course> courses = new ArrayList<Course>();
        courses.add(course);
        programmaMathimaton.set_Katalogo_Me_Wres(courses);


        System.out.println(courses.get(0).getTeaching_days().contains("Monday")); //test






    }
}
