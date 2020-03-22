package com.example.timetimer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class                     Time_reminder extends Fragment {



    private boolean isRunning;
    private int hour , min , sec = 0;
    private TextToSpeech tts;
    private TextView spent_time;
    private CountDownTimer cdt;
    private Button b ,stop;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_main,container,false);

        stop = view.findViewById(R.id.stop);
        b = view.findViewById(R.id.start);
        spent_time = view.findViewById(R.id.time_spent);

        isRunning = false;
        final Spinner s = view.findViewById(R.id.pickTime);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),R.array.time,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(!isRunning) {

                    hour = 0;
                    min = 0;
                    sec = 0;
                    spent_time.setText(hour + " hr: " + min + " min: " + sec + " sec.");

                    //spent_time.setVisibility(View.INVISIBLE);

                    long st = Long.parseLong((String) s.getSelectedItem()) * 60 * 1000;
                    cdt = new CountDownTimer(st, 1000) {


                        @Override
                        public void onTick(long millisUntilFinished) {

                            sec++;
                            spent_time.setText(hour + " hr: " + min + " min: " + sec + " sec.");
                            if (sec == 60) {
                                sec = 0;
                                min++;
                                spent_time.setText(hour + " hr: " + min + " min: " + sec + " sec.");

                                if (min == 60) {
                                    hour++;
                                    min = 0;
                                    spent_time.setText(hour + " hr: " + min + " min: " + sec + " sec.");
                                }
                            }
                            isRunning = true;
                            b.setBackgroundColor(Color.GRAY);
                            stop.setBackgroundColor(Color.RED);
                        }


                        @Override
                        public void onFinish() {
                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                            tts.speak("The time is " + currentTime, TextToSpeech.QUEUE_FLUSH, null);
                            isRunning = false;
                            cdt.start();

                        }
                    }.start();}
                else

                {
                    Toast.makeText(getContext(), getString(R.string.toast), Toast.LENGTH_SHORT).show();
                }


            }});

        stop.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (isRunning) {
                    cdt.cancel();
                    spent_time.setVisibility(View.VISIBLE);
                    spent_time.setText(hour + " hr: "+min+" min: "+sec+" sec.");
                    stop.setBackgroundColor(Color.GRAY);
                    b.setBackgroundColor(Color.GREEN);
                    isRunning = false;
                }
            }


        } );




return view;
    }
}
