package com.example.timetimer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

@SuppressLint("Registered")
public class Main_page extends AppCompatActivity {

    TextView drink,time;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__timer_);
        drink = findViewById(R.id.drink);
        time = findViewById(R.id.time);
        viewPager = findViewById(R.id.fragment_layout);
        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);

            }
        });
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void onChangeTab(int position){

        if(position==0){

            time.setTextSize(25);
            time.setTextColor(ContextCompat.getColor(this ,R.color.light));

            drink.setTextSize(18);
            drink.setTextColor(Color.WHITE);


        }
        if(position==1){

            time.setTextSize(18);
            time.setTextColor(Color.WHITE);

            drink.setTextSize(25);
            drink.setTextColor(ContextCompat.getColor(this ,R.color.light));


        }



    }

}
