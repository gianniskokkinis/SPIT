package com.belunka.spit;

import static androidx.navigation.Navigation.findNavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.belunka.spit.Background_Tasks.BackgroundService;
import com.belunka.spit.Member.Member;
import com.belunka.spit.Member.member_my_catalogue;
import com.belunka.spit.Schedule_Plan.Schedule_Program;
import com.belunka.spit.get_url_link.MyWebViewClient;
import com.belunka.spit.sql_connect_tools.check_account;
import com.belunka.spit.sql_connect_tools.get_stats_from_data_base;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    boolean set_to_scedule = false; //this is if user tap back button from activity

    Member member;

    String am;

    boolean is_account_registered;

    WebView webView;
    MainActivity mainActivity;
    @SuppressLint("WrongViewCast")

    //depentency
    BottomNavigationView bottomNavigationView;

    //here we need something to store our arguments


    //initialize items here
    HomeSagment homeSagment = new HomeSagment();
    PersonalSagement personalSagment = new PersonalSagement(this);
    ScheduleSagement scheduleSagement = new ScheduleSagement();
    BlankFragment blankFragment = new BlankFragment();

    public boolean is_from_personal_sagment;
    /**
     * Get connection from database
     * */

    Bundle savedInstanceState;

    TextView notify_message;
    Button next_button;

    ImageView Display_image_check_account;
    ProgressBar progressBar_display_message;

    ProgressBar progressBar_signup;

    EditText get_username, get_examino, get_etos;

    String preset_am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        //this code is about remove top bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//remove title
        mainActivity = this;




        //check if pressed to be here
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String message = "12";
        if (extras != null){
            message = extras.getString("is_called_by_on_back");
            preset_am = extras.getString("preset_am");

//            Log.d("Is_logged_IN: ", is_loggedin);
        }

    
        setContentView(R.layout.activity_main);
        Button getStartedbtn = findViewById(R.id.getStartedButton);



        getStartedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

                /*set here login page to aunthenticate*/

                setContentView(R.layout.login_screen_to_get_am);
                    webView = findViewById(R.id.webview);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl("http://sso.uoi.gr");

                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            // This code runs when the page finishes loading
                            view.evaluateJavascript(
                                    "(function() { return document.body.innerText; })();",
                                    new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String text) {
                                            // This code runs when the JavaScript code returns a result
                                            // The textual content of the current page is stored in the "text" variable
                                            // Do something with the text here
                                            Log.d("WebView Text", text);
                                            String check_am = find_am_from_website(text);
                                            System.out.println("check_am :"+ check_am); //test
                                            System.out.println("length :"+ check_am.length()); //test
                                            System.out.println("StartsWith :"+ check_am.startsWith("cs")); //test
                                            System.out.println(check_am.length()!=0 && check_am.startsWith("cs")); //test
                                            if (check_am.length()!=0 && check_am.startsWith("cs")){

                                                check_am = check_am.substring(0,check_am.length()-1); //to not get comma
                                                preset_am = check_am;
                                                setContentView(R.layout.display_message_when_login); //login here

                                                Display_image_check_account = findViewById(R.id.Display_image_check_account);
                                                progressBar_display_message = findViewById(R.id.progressBar_display_message);
                                                progressBar_display_message.setVisibility(View.GONE);//by default not true

                                                notify_message = findViewById(R.id.notify_text); //get here text
                                                next_button = findViewById(R.id.next_button); //get button here

                                                check_account ch_account = new check_account(check_am,mainActivity);
                                                try{
                                                    ch_account.get_account_from_database(check_am);


                                                    //here we are just running script
                                                    String finalCheck_am = check_am;
                                                    String finalCheck_am1 = check_am;
                                                    next_button.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {

                                                                if (is_account_registered){
                                                                    try {
                                                                        get_stats_from_data_base get_info = new get_stats_from_data_base(homeSagment,personalSagment,scheduleSagement,mainActivity, finalCheck_am1);
                                                                        personalSagment.set_getinfo(get_info);
                                                                        homeSagment.set_get_info(get_info);
                                                                    } catch (SQLException e) {
                                                                        System.out.println("Something went wrong with database");
                                                                        e.printStackTrace();
                                                                    }

                                                                    //just continue
                                                                    setContentView(R.layout.client_main_menu);

                                                                    //main code

                                                                    bottomNavigationView = findViewById(R.id.bottomNavigationView);
                                                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeSagment).commit();

                                                                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                        @Override
                                                                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                                                                            switch (menuItem.getItemId()){
                                                                                case R.id.Home:  //switch to home fragment
                                                                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeSagment).commit();
                                                                                    return true;
                                                                                case R.id.personal:  //switch to personal fragment
                                                                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,personalSagment).commit();
                                                                                    return true;
                                                                                case R.id.schedule: //switch to schedule fragment
                                                                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,scheduleSagement).commit();
                                                                                    return true;
                                                                            }
                                                                            return false;
                                                                        }


                                                                    });
                                                                }else{
                                                                    //den drethike o logariasmos
//                                                                    notify_message.setText("Απ' ότι φαίνεται δεν έχεις κάνει εγγραφή");
//                                                                    next_button.setText("REGISTER");
                                                                    //here we are register
                                                                    setContentView(R.layout.signup_page);
                                                                    progressBar_signup = findViewById(R.id.progressBar);
                                                                    progressBar_signup.setVisibility(View.GONE);
                                                                    Button eggrafiButton;
                                                                    get_username = findViewById(R.id.get_username);
                                                                    get_examino = findViewById(R.id.get_examino);
                                                                    get_etos = findViewById(R.id.get_etos);
                                                                    eggrafiButton = findViewById(R.id.eggrafi_button);

                                                                    eggrafiButton.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            //first get the text
                                                                            if(get_username.getText().toString().length()!=0 && !(get_username.getText().toString().toLowerCase().equals("admin")) && get_examino.getText().toString().length()!=0 && get_etos.getText().toString().length() != 0){
                                                                                try{
                                                                                    ch_account.register_new_user_to_database(get_username.getText().toString(), get_examino.getText().toString(), get_etos.getText().toString());
                                                                                    //restart app


                                                                                    setContentView(R.layout.welcome_screen_1);
                                                                                    Button welcome_screen_btn = findViewById(R.id.welcome_screen_btn);
                                                                                    welcome_screen_btn.setOnClickListener(new View.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(View view) {
                                                                                            setContentView(R.layout.welcome_screen_2);
                                                                                            Button welcome_screen_btn2 = findViewById(R.id.welcome_screen_btn2);
                                                                                            welcome_screen_btn2.setOnClickListener(new View.OnClickListener() {
                                                                                                @Override
                                                                                                public void onClick(View view) {
                                                                                                    setContentView(R.layout.welcome_screen_3);
                                                                                                    Button welcome_screen_btn3 = findViewById(R.id.welcome_screen_btn3);
                                                                                                    welcome_screen_btn3.setOnClickListener(new View.OnClickListener() {
                                                                                                        @Override
                                                                                                        public void onClick(View view) {
                                                                                                            setContentView(R.layout.welcome_screen_4);
                                                                                                            Button welcome_screen_btn4 = findViewById(R.id.welcome_screen_btn4);
                                                                                                            welcome_screen_btn4.setOnClickListener(new View.OnClickListener() {
                                                                                                                @Override
                                                                                                                public void onClick(View view) {
                                                                                                                    setContentView(R.layout.welcome_screen_6);
                                                                                                                    Button welcome_screen_btn6 = findViewById(R.id.welcome_screen_bt6);
                                                                                                                    welcome_screen_btn6.setOnClickListener(new View.OnClickListener() {
                                                                                                                        @Override
                                                                                                                        public void onClick(View view) {
                                                                                                                            setContentView(R.layout.welcome_screen_5);
                                                                                                                            Button welcome_screen_btn5 = findViewById(R.id.welcome_screen_btn5);
                                                                                                                            welcome_screen_btn5.setOnClickListener(new View.OnClickListener() {
                                                                                                                                @Override
                                                                                                                                public void onClick(View view) {
                                                                                                                                    //prototype
                                                                                                                                    Intent intent = new Intent(mainActivity,MainActivity.class);
                                                                                                                                    startActivity(intent);
                                                                                                                                }
                                                                                                                            });



                                                                                                                        }
                                                                                                                    });
                                                                                                                }
                                                                                                            });
                                                                                                        }
                                                                                                    });
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    });



//


                                                                                }catch (Exception e){
                                                                                    setContentView(R.layout.no_internet_connection);
                                                                                    System.out.println("Something went wrong");
                                                                                    e.printStackTrace();
                                                                                }
                                                                            }else{
                                                                                Toast.makeText(MainActivity.this, "Please insert values to boxes and don't use ADMIN as username", Toast.LENGTH_SHORT).show();
                                                                            }


                                                                        }
                                                                    });
                                                                }



                                                            }
                                                        });

                                                }catch (Exception e){
                                                    System.out.println("Something went wrong");
                                                    e.printStackTrace();
                                                    setContentView(R.layout.no_internet_connection);
                                                }


                                            }
                                        }
                                    }
                            );
                        }
                    });








                }

            });

            //check here
            if (message != null){
                if (message.length()!=0 && message.equals("yes")){
                    //for back button
                    try {
                        get_stats_from_data_base get_info = new get_stats_from_data_base(homeSagment,personalSagment,scheduleSagement,mainActivity, preset_am);
                        personalSagment.set_getinfo(get_info);
                        homeSagment.set_get_info(get_info);
                    } catch (SQLException e) {
                        System.out.println("Something went wrong with database");
                        e.printStackTrace();
                    }

                    //just continue
                    setContentView(R.layout.client_main_menu);

                    //main code

                    bottomNavigationView = findViewById(R.id.bottomNavigationView);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeSagment).commit();

                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                            switch (menuItem.getItemId()){
                                case R.id.Home:  //switch to home fragment
                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeSagment).commit();
                                    return true;
                                case R.id.personal:  //switch to personal fragment
                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,personalSagment).commit();
                                    return true;
                                case R.id.schedule: //switch to schedule fragment
                                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,scheduleSagement).commit();
                                    return true;
                            }
                            return false;
                        }


                    });

                    //end test
                }
            }


    }

    public void setMember(Member update_Member){
        this.member = update_Member;
    }

    public boolean get_is_from_personal_sagment(){
        return is_from_personal_sagment;
    }

    public void set_is_from_personal_sagment(boolean value){
        this.is_from_personal_sagment = value;
    }


    @Override
    public void onBackPressed(){
        //test here code
        if (set_to_scedule){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("is_called_by_on_back", "yes");
            intent.putExtra("preset_am",preset_am);
            startActivity(intent);
            finish();
            Log.d("more", "yes is spectating pressed ");
            //need here something to switch to schedule sagment

            set_to_scedule = false;
        }else{
            Log.d("more", "no , is spectating not pressed ");
            super.onBackPressed();
        }

    }


    public void setter_for_scedule(boolean update){
        this.set_to_scedule = update;
    }

    public String find_am_from_website(String input){

        String[] words = input.split(" "); // χωρίζουμε το string σε λέξεις

        // ελέγχουμε κάθε λέξη για την αρχή της
        for (String word : words) {
            System.out.println("Word: "+word);
            if (word.startsWith("cs")) {
                return word;
            }else{
                //case 2
                String[] check_word = word.split("n");
                for (String checkWord: check_word){
                    System.out.println("checkWord : "+checkWord);
                    System.out.println(checkWord.startsWith("cs"));
                    if (checkWord.startsWith("cs")){
                        System.out.println("True");
                        return checkWord;
                    }
                }


            }
        }






        return "";

    }







    public String get_Am(){
        return am;
    }

    public void set_Am(String update_am){
        this.am = update_am;
    }

    public WebView get_Webview(){
        return webView;
    }

    public void set_Webview(WebView update_webview){
        this.webView = update_webview;
    }

    public boolean get_is_account_registered(){
        return is_account_registered;
    }

    public void set_is_account_registered(boolean update_value){
        this.is_account_registered = update_value;
    }


    public TextView get_notify_message(){
        return notify_message;
    }

    public Button get_next_button(){
        return next_button;
    }

    public ImageView get_Display_image_check_account(){
        return Display_image_check_account;
    }

    public ProgressBar get_progressBar_display_message(){
        return progressBar_display_message;
    }

    public ProgressBar get_progressBar_signup(){
        return progressBar_signup;
    }

    public EditText get_get_username(){
        return get_username;
    }

    public EditText get_get_examino(){
        return get_examino;
    }

    public EditText get_get_etos(){
        return get_etos;
    }


    public HomeSagment get_homesagment(){
        return homeSagment;
    }

    public PersonalSagement get_personalSagment(){
        return personalSagment;
    }

    public ScheduleSagement get_ScheduleSagement(){
        return scheduleSagement;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        stopService(getIntent());
        Log.d("Moldova", "onDestroy: destroyed");
    }


}
