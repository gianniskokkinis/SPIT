package com.belunka.spit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<myViewHolder> {

    Context context;
    List<Course> items;

    int position;
    Find_Courses_Activity fCourse;
    Activity mainActivity;

    Member member;




    //maybe initialize here courses

    public MyAdapter(Context context, List<Course> items, Find_Courses_Activity fCourse, Activity mainActivity, Member update_member){
        this.context = context;
        this.items = items;
        this.fCourse = fCourse;//now we need connext to the myViewHolder
        this.mainActivity = mainActivity;
        this.member = update_member;

    }

    public void setFilteredList(List<Course> filteredList){
        this.items = filteredList;
        notifyDataSetChanged(); //change here update
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //maybe i can get button here
        myViewHolder mViewHolder = new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false));
        mViewHolder.setfCourse_inside(fCourse);
        mViewHolder.setMainActivity(mainActivity);
        mViewHolder.set_member(member);
        position = mViewHolder.get_pos();
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.courseTitle.setText(items.get(i).getName());
        myViewHolder.courseDesc.setText(items.get(i).getInfo()); //maybe change it here
        myViewHolder.imageView.setImageResource(R.drawable.baseline_book_24);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public int get_position(){
        return position;
    }






}
