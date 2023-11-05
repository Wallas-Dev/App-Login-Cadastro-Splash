package com.example.appbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "HomeLog";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                String email = sp.getString("email", "default");
                Log.d("Auth", "Valor do email: " + email);

                if(email.equals("default")){
                    Intent it = new Intent(splash.this, Login.class);
                    startActivity(it);
                }else{
                    Intent it = new Intent(splash.this, Principal.class);
                    startActivity(it);
                }


            }
        }, 2000);

    }
}