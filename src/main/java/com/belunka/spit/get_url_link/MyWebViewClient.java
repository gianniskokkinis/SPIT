package com.belunka.spit.get_url_link;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.belunka.spit.Course_Programma_Katalogos.ProgrammaMathimaton;
import com.belunka.spit.MainActivity;
import com.belunka.spit.R;

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

public class MyWebViewClient extends WebViewClient {

    MainActivity mainActivity;


    public MyWebViewClient(MainActivity update_Activity){
        this.mainActivity = update_Activity;
        take_am_first();
    }


    //thread here

    public void take_am_first() {



        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                new Thread(() -> {



                    mainActivity.get_Webview().setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            // Πάρε το περιεχόμενο της σελίδας σε ένα string
                            mainActivity.get_Webview().evaluateJavascript("(function() { return document.documentElement.outerHTML; })();", new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String value) {
                                    // Το περιεχόμενο της σελίδας είναι στο value
                                    String webpageContent = value;
                                    String check_am = find_am_from_website(webpageContent);
                                    Log.d("Webview", "Webview: "+webpageContent);
                                    if (check_am.length()!=0 && check_am.startsWith("cs")){
                                        mainActivity.setContentView(R.layout.coming_soon);
                                        scheduler.shutdown();
                                    }
                                }
                            });
                        }
                    });



                }).start();

            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    public String find_am_from_website(String input){

        String[] words = input.split(" "); // χωρίζουμε το string σε λέξεις

        // ελέγχουμε κάθε λέξη για την αρχή της
        for (String word : words) {
            if (word.startsWith("cs")) {
                return word;
            }
        }

        return "";

    }


}