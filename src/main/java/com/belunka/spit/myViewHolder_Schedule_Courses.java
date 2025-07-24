package com.belunka.spit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.belunka.spit.CoursePackage.Course;
import com.belunka.spit.Member.Member;
import com.belunka.spit.sql_connect_tools.update_course_status_to_database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

import java.util.List;

public class myViewHolder_Schedule_Courses extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView courseTitle, courseDesc;

    List<Course> items_to_change;

    Activity main_activity;
    Button view_course_btn;
    ImageButton remove_from_library, add_to_library;

    Member member;

    TextView course_title;
    TextView room_target;
    TextView kefalaio;
    TextView changed_by;

    myViewHolder_Schedule_Courses param_viewHOlder = this;

    EditText get_kefalaio_plain;
    Button set_kefalaio_button;

    ImageView status_icon;

    TextView course_examine4;
    ImageView imageView22;
    Button start_button, break_button, stop_button;

    ProgressBar progressBar_course_status;


    public myViewHolder_Schedule_Courses(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview_course_thunbail); //initialize here imageview
        courseTitle = itemView.findViewById(R.id.name_course_title);
        courseDesc = itemView.findViewById(R.id.course_time_display);
        view_course_btn = itemView.findViewById(R.id.spectate_button);
        Context context = itemView.getContext();


        view_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //we need that for back button
                try {

                    ((MainActivity) main_activity).setter_for_scedule(true);
                }catch (Exception E){
                    Toast.makeText(context, "something went wrong in setting true", Toast.LENGTH_SHORT).show();
                }

                int position = getAdapterPosition();
//                Toast.makeText(context, "Position: "+position, Toast.LENGTH_SHORT).show();
                //set content view
                main_activity.setContentView(R.layout.course_status_layout);
                imageView22 = main_activity.findViewById(R.id.imageView22);
                course_examine4 = main_activity.findViewById(R.id.course_examine4);
                progressBar_course_status = main_activity.findViewById(R.id.progressBar_course_status);
                progressBar_course_status.setVisibility(View.GONE);

                get_kefalaio_plain=main_activity.findViewById(R.id.get_kefalaio_edit_text);
                set_kefalaio_button= main_activity.findViewById(R.id.set_kefalaio_button);

                //by default here we are waiting
                get_kefalaio_plain.setVisibility(View.GONE);
                set_kefalaio_button.setVisibility(View.GONE);



                status_icon = main_activity.findViewById(R.id.status_icon);


                course_title = main_activity.findViewById(R.id.lesson_title);
                room_target = main_activity.findViewById(R.id.room_target_update);
                kefalaio = main_activity.findViewById(R.id.kefalaio);
                changed_by = main_activity.findViewById(R.id.changed_by_label);

                if (items_to_change.get(position).getName().length() > 24){
                    course_title.setText(items_to_change.get(position).getName().substring(0,24)+"...");
                }else{
                    course_title.setText(items_to_change.get(position).getName()); //set name here
                }
                room_target.setText(items_to_change.get(position).get_target_room());
                kefalaio.setText("Πατήστε εδώ για να ενημερώσετε που βρίσκεται το μάθημα"); //here we are getting kefalaio from database
                /*UPATE HERE FROM THREAD*/
                update_course_status_to_database get_kefalaio_from_database = new update_course_status_to_database(0, items_to_change.get(position).getName(),member.getAM(),param_viewHOlder,main_activity);
                get_kefalaio_from_database.set_allow_read(true);
                try{
                    get_kefalaio_from_database.get_kefalaio_of_course_from_database();
                    get_kefalaio_from_database.get_status_of_course();
                }catch (Exception e){
                    Log.d("when is calling ", "thread is calling");
                    e.printStackTrace();
                }

                /*UPDATE HERE FROM THREAD*/
                stop_button = main_activity.findViewById(R.id.stop_button_course);
                break_button = main_activity.findViewById(R.id.break_button_course);
                start_button = main_activity.findViewById(R.id.start_button_course);



                //set red cicle
                stop_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /**
                         * Set cicle and update database
                         * */

                        status_icon.setImageDrawable(main_activity.getDrawable(R.drawable.non_active_course));



                        //update database

                        changed_by.setText("Changed by "+member.getUsername()); //this is test and we need to update here with thread

                        update_course_status_to_database read_from_database = new update_course_status_to_database(0, items_to_change.get(position).getName(),member.getAM(),param_viewHOlder,main_activity);
                        try{
                            read_from_database.set_value_to_database();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d("Eroor", "write to databae");
                        }

                        /**
                         *  statements means
                         *  0 - stop teaching
                         *  1 - start teaching
                         *  2 - break time
                         * */


                    }
                });


                break_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        status_icon.setImageDrawable(main_activity.getDrawable(R.drawable.course_break));

                        //update data base
                        changed_by.setText("Changed by "+member.getUsername()); //this is test and we need to update here with thread

                        update_course_status_to_database write_break_to_database = new update_course_status_to_database(2, items_to_change.get(position).getName(),member.getAM(),param_viewHOlder,main_activity);
                        try{
                            write_break_to_database.set_value_to_database();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d("Eroor", "write to databae");
                        }

                        /**
                         *  statements means
                         *  0 - stop teaching
                         *  1 - start teaching
                         *  2 - break time
                         * */

                    }
                });


                start_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /**
                         *
                         * set cicle and update database
                         * */
                        status_icon.setImageDrawable(main_activity.getDrawable(R.drawable.active_course));

                        //update database

                        changed_by.setText("Changed by "+member.getUsername()); //this is test and we need to update here with thread

                        update_course_status_to_database write_start_to_database = new update_course_status_to_database(1, items_to_change.get(position).getName(),member.getAM(),param_viewHOlder,main_activity);
                        try{
                            write_start_to_database.set_value_to_database();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d("Eroor", "write to databae");
                        }


                        /**
                         *  statements means
                         *  0 - stop teaching
                         *  1 - start teaching
                         *  2 - break time
                         * */

                    }
                });

                //update here fields


                //here is for visible kefalaio
                kefalaio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //just enable buttons
                        get_kefalaio_plain.setVisibility(View.VISIBLE);
                        set_kefalaio_button.setVisibility(View.VISIBLE);


                    }
                });

                //here is for set kefalaio
                set_kefalaio_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String set_kefalaio_to_data_base_input = get_kefalaio_plain.getText().toString();
                        if (set_kefalaio_to_data_base_input.length() > 32){
                            Toast.makeText(context, "Please write a message less than 32 characters", Toast.LENGTH_SHORT).show();
                        }else{
                            update_course_status_to_database write_to_data_base = new update_course_status_to_database(0,items_to_change.get(position).getName(), member.getAM(), param_viewHOlder,main_activity);
                            try{
                                //update to database
                                write_to_data_base.set_kefalaio_of_course_from_database(set_kefalaio_to_data_base_input);
                                kefalaio.setText(set_kefalaio_to_data_base_input);
                            }catch (Exception e){
                                Log.d("Error", "in writing kefalaio to database");
                                e.printStackTrace();
                            }
                        }



                        //gone here when we are done
                        get_kefalaio_plain.setVisibility(View.GONE);
                        set_kefalaio_button.setVisibility(View.GONE);
                    }
                });




            }
        });

    }

    public void set_Activity(Activity update_activity){
        this.main_activity = update_activity;
    }

    public void set_Member(Member update_member){
        this.member = update_member;
    }

    public TextView get_kefalaio(){
        return kefalaio;
    }

    public ImageView get_status_icon(){
        return status_icon;
    }

    public TextView get_changed_by(){
        return changed_by;
    }

    public TextView get_course_examine4(){
        return course_examine4;
    }

    public TextView get_room_target(){
        return room_target;
    }

    public ImageView get_imageView22(){
        return imageView22;
    }

    public TextView get_course_title(){
        return course_title;
    }

    public Button get_stop_button(){
        return stop_button;
    }

    public Button get_break_button(){
        return break_button;
    }

    public Button get_start_button(){
        return start_button;
    }

    public Button get_set_kefalaio_button(){
        return set_kefalaio_button;
    }

    public EditText get_get_kefalaio_plain(){
        return get_kefalaio_plain;
    }

    public ProgressBar get_progressBar_course_status(){
        return progressBar_course_status;
    }






}
