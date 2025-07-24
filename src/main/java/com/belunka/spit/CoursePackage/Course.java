package com.belunka.spit.CoursePackage;

import java.util.List;

public class Course {

    private String name;
    private String TeacherName;
    private int Examine;
    private int Etos;
    private String info;

    private String examinoString; //EARINO || XEIMERINO GREKLISH ONLY
    private String type; // YPOXREWTIKO  || EPILOGIS GREKLISH ONLY


    private List<String> teaching_days; // type : ["Monday", "Tuesday", "Thirsday"]
    private List<String> teaching_hours; // type : ["17:00-18:00", "18:00-20:00" and we are splitting using "-"



    private String Alert_date;
    private String Alert_Teaching_Time;

    boolean alert_Anaplirwsi; //check if we have time here

    private String room_target;

    private String Kefalaio;




    public Course(String updateName, String updateTeacherName, int updateExamine, int updateEtos, String updateInfo, String updateExaminoString, String updateType){
        this.name = updateName;
        this.TeacherName = updateTeacherName;
        this.Examine = updateExamine;
        this.Etos = updateEtos;
        this.info = updateInfo;
        this.examinoString = updateExaminoString;
        this.type = updateType;
    }

    public String getName(){
        return name;
    }

    public String getTeacherName(){
        return TeacherName;
    }

    public int getExamine(){
        return Examine;
    }

    public int getEtos(){
        return Etos;
    }


    public String getInfo(){
        return info;
    }

    public String getExaminoString(){
        return  examinoString;
    }

    public String getType(){
        return  type;
    }


    public List<String> getTeaching_days(){
        return teaching_days;
    }


    public void setTeaching_days(List<String> update_Teaching_Days){
        this.teaching_days = update_Teaching_Days;
    }

    public List<String> getTeaching_hours(){
        return teaching_hours;
    }

    public void setTeaching_hours(List<String> update_Teaching_hours){
        this.teaching_hours = update_Teaching_hours;
    }

    public void set_TeachingDays(List<String> update_Teaching_dates){
        this.teaching_days = update_Teaching_dates;
    }

    public List<String> get_TeachingDays(){
        return this.teaching_days;
    }

    public List<String> get_TeachingHours(){
        return this.teaching_hours;
    }

    public void set_TeachingHours(List<String> update_Teaching_Hours){
        this.teaching_hours = update_Teaching_Hours;
    }

    public String get_target_room(){
        return this.room_target;
    }

    public void set_target_room(String room){
        this.room_target = room;
    }

    public String get_kefalaio(){
        return this.Kefalaio;
    }

    public void set_kefalaio(String update_kefalaio){
        this.Kefalaio = update_kefalaio;
    }





}
