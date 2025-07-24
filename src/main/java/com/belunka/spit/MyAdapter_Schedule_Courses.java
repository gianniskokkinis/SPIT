package com.belunka.spit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;

import java.util.List;

public class MyAdapter_Schedule_Courses extends RecyclerView.Adapter<myViewHolder_Schedule_Courses> {

    Context context;
    List<Course> items;
    FragmentActivity schedule;
    Activity mainActivity;

    Member member;
    String day;

    public MyAdapter_Schedule_Courses(Context context, List<Course> items, FragmentActivity update_schedule, Activity mainActivity, Member member, String day){
        this.context = context;
        this.items = items;
        this.schedule = update_schedule;
        this.mainActivity = mainActivity;
        this.member = member;
        this.day = day; //this is for update day

    }

    @NonNull
    @Override
    public myViewHolder_Schedule_Courses onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        myViewHolder_Schedule_Courses myViewHolder_schedule_courses = new myViewHolder_Schedule_Courses(LayoutInflater.from(context).inflate(R.layout.item_view_display_schedule_courses, viewGroup, false));
        return myViewHolder_schedule_courses;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder_Schedule_Courses myViewHolder_schedule_courses, int i) {
        myViewHolder_schedule_courses.courseTitle.setText(items.get(i).getName());
        int index = items.get(i).get_TeachingDays().indexOf(day);
        myViewHolder_schedule_courses.courseDesc.setText(items.get(i).get_TeachingHours().get(index)); //maybe change it here
        myViewHolder_schedule_courses.items_to_change = items; //this is items
        myViewHolder_schedule_courses.imageView.setImageResource(R.drawable.baseline_book_24);
        myViewHolder_schedule_courses.set_Activity(mainActivity); //conect here
        myViewHolder_schedule_courses.set_Member(member);
        //connect our list with that


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}
