package com.belunka.spit.Background_Tasks;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.belunka.spit.Course_Programma_Katalogos.ProgrammaMathimaton;
import com.belunka.spit.Member.Member;
import com.belunka.spit.R;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;



    Member member;

    private static final String TAG = "BackgroundService";
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            //my code here
            // Δημιουργία της notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "my_channel_id")
                    .setSmallIcon(R.drawable.spit)
                    .setContentTitle("Test notification")
                    .setContentText("This is a notification from my BackgroundService")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Εμφάνιση της notification
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, builder.build());

            Log.d("Notification", "just now");




            Log.d(TAG, "Code executed");
            mHandler.postDelayed(this, 60000); // Run this code again after 1 minute
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Gson gson = new Gson();
        mHandler.post(mRunnable); // Start the handler
        String json = intent.getStringExtra("myString");
        member =gson.fromJson(json, Member.class); // get here
        Log.d("Member:", member.getAM()); //test
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable); // Stop the handler when the service is destroyed
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void check_here_if_any_course_is_active() throws SQLException {

        /*Fill credentials with ur database*/

        final String DATABASE_NAME = "";
        final String url = "" +
                DATABASE_NAME;

        final String username = "", password = "";

        final String TABLE_NAME = "STUDENTS";




        ProgrammaMathimaton programmaMathimaton = new ProgrammaMathimaton();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                new Thread(() -> {


                    //if day off
                    SimpleDateFormat formatter = new SimpleDateFormat("HH");
                    Date currentDate = new Date();
                    String currentDateString = formatter.format(currentDate);
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, username, password);
                        Statement statement = connection.createStatement();
                        System.out.println("CONNECTION SUCCED");



                        String sql = "SELECT course_title,course_kefalaio FROM COURSES WHERE is_active_now=1";
                        PreparedStatement stmt = connection.prepareStatement(sql);

                        ResultSet rs = stmt.executeQuery();


                        String course_title = "";
                        String kefalaio = "";
                        while (rs.next()){
                            course_title = rs.getString(1); //get the course
                            kefalaio = rs.getString(2); // get the info of course here
                            if (course_title.length()!=0 && is_on_my_library_this_course(course_title)){ // and one statement here
                                //notification here

                                break; //get out of while
                            }
                        }

                        //if not anything found check here

                        if (course_title.length() == 0){

                            sql = "SELECT course_title,course_kefalaio FROM COURSES WHERE is_active_now=2";
                            stmt = connection.prepareStatement(sql);
                            rs = stmt.executeQuery();

                            while (rs.next()){
                                course_title = rs.getString(1); //get the course
                                kefalaio = rs.getString(2); // get the info of course here
                                if (course_title.length()!=0 && is_on_my_library_this_course(course_title)){ // and one statement here

                                    //notification here

                                    break; //get out of while
                                }
                            }

                        }

                        connection.close();

                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }




//                }







                }).start();

            }


        }, 0, 1, TimeUnit.MINUTES);

    }

    private boolean is_on_my_library_this_course(String course_name){
        for (int i=0; i<member.get_Member_Catalogue().get_Course_List().size(); i++){
            if (member.get_Member_Catalogue().get_Course_List().get(i).getName().equals(course_name)){
                return true;
            }
        }
        return false;
    }







}
