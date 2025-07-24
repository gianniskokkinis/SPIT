package com.belunka.spit.CoursePackage;

import java.util.ArrayList;
import java.util.List;

public class CoursesList {

    private List<Course> courses; //gia ola ta mathimata

    //here we need something to store teaching dates , teaching hours and more informations about courses

    

    public CoursesList(List<Course> setCourses){
        for (int i=0; i<courses.size(); i++){
            courses.add(i,setCourses.get(i)); //replace our list with update list
            //maybe more functions
        }
    }

    //get all courses with this examin
    public List<Course> getExamineCourses(int examine){
        ArrayList<Course> returnList = new ArrayList<Course>();
        for (int i=0; i<courses.size(); i++){
            if (courses.get(i).getExamine() == examine){
                returnList.add(courses.get(i));
            }
        }
        return returnList;
    }


    public List<Course> getEtosCourses(int Etos){
        ArrayList<Course> returnList = new ArrayList<Course>();
        for (int i=0; i<courses.size(); i++){
            if (courses.get(i).getEtos() == Etos){
                returnList.add(courses.get(i));
            }
        }
        return returnList;
    }

        public List<Course> getEarinoCourses(){
            ArrayList<Course> returnList = new ArrayList<Course>();
            for (int i=0; i<courses.size(); i++){
                if (courses.get(i).getExaminoString().equals("EARINO")){ //if examinoString == EARINO
                    returnList.add(courses.get(i));
                }
            }
            return returnList;
        }

    //isos pejw bala me overloads edw


    public List<Course> getXimerinoCourses(){
        ArrayList<Course> returnList = new ArrayList<Course>();
        for (int i=0; i<courses.size(); i++){
            if (courses.get(i).getExaminoString().equals("XEIMERINO")){ //if examinoString == XEIMERINO
                returnList.add(courses.get(i));
            }
        }
        return returnList;
    }

    public List<Course> getYpoxrewtika(){
        ArrayList<Course> returnList = new ArrayList<Course>();
        for (int i=0; i<courses.size(); i++){
            if (courses.get(i).getType().equals("YPOXREWTIKO")){ //if examinoString == YPOXREWTIKO
                returnList.add(courses.get(i));
            }
        }
        return returnList;
    }

    public List<Course> getEpilogis(){
        ArrayList<Course> returnList = new ArrayList<Course>();
        for (int i=0; i<courses.size(); i++){
            if (courses.get(i).getType().equals("EPILOGIS")){ //if examinoString == EPILOGIS
                returnList.add(courses.get(i));
            }
        }
        return returnList;
    }

    //test
    public static void main(String[] args) {



    }



}
