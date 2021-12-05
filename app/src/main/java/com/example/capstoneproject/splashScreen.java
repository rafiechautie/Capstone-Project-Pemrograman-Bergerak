package com.example.capstoneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstoneproject.activity.MainActivity;
import com.example.capstoneproject.onboarding.onboarding;

public class splashScreen extends AppCompatActivity {

    SharedPreferences onBoardingScreen;
    Animation top_animation;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //bikin tampilan app pas on boarding jadi full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //menghilangkan actionbar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        image = findViewById(R.id.logoApp);

        image.setAnimation(top_animation);

        //menampilkan splashscreen selama 5 detik lalu di redirect ke halaman onboarding
        final Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable(){



            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime)
                {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    //setelah loading akan berpindah ke halaman onboarding1
                    Intent intent = new Intent(splashScreen.this, onboarding.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(splashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 3000); //3s = 3000

    }
}