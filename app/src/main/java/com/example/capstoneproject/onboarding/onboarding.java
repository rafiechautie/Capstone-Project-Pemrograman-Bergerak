package com.example.capstoneproject.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.capstoneproject.R;
import com.example.capstoneproject.adapter.SliderAdapter;

public class onboarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int posisiSekarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bikin tampilan app pas on boarding jadi full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.sliderOnBoarding1);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.getStarted);

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }

    public void skip(View view)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void next(View view)
    {
        viewPager.setCurrentItem(posisiSekarang + 1);
    }

    private void addDots(int position)
    {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i<dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }


    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            posisiSekarang = position;

            if (position == 0)
            {
                letsGetStarted.setVisibility(View.INVISIBLE);
            }else if (position == 1)
            {
                letsGetStarted.setVisibility(View.INVISIBLE);
            }else
            {
                animation = AnimationUtils.loadAnimation(onBoarding1Activity.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}