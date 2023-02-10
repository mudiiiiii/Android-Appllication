package com.example.mudiagaotojareri_comp304sec004_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class TeActivity extends AppCompatActivity {
    private ImageView earthView;
    private ImageView moonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_te);

        this.earthView = findViewById(R.id.earthView);
        this.moonView = findViewById(R.id.moonView);

        findViewById(R.id.startButton).setOnClickListener(v -> this.startAnimation());
        findViewById(R.id.stopButton).setOnClickListener(v -> this.stopAnimation());
    }

    private void stopAnimation() {
        Toast.makeText(this, getResources().getString(R.string.stopAnimation), Toast.LENGTH_SHORT).show();
        this.earthView.clearAnimation();
        this.moonView.clearAnimation();

    }

    private void startAnimation() {
        Toast.makeText(this, getResources().getString(R.string.startAnimation), Toast.LENGTH_SHORT).show();

        this.earthView.setImageResource(R.drawable.earth);
        this.moonView.setImageResource(R.drawable.moooon);


        Animation earthAnim = AnimationUtils.loadAnimation(this, R.anim.spin_earth);
        Animation moonAnim = AnimationUtils.loadAnimation(this, R.anim.moon_animation);

        earthAnim.setRepeatCount(Animation.INFINITE);
        moonAnim.setRepeatMode(Animation.RESTART);
        moonAnim.setRepeatCount(Animation.INFINITE);

        this.earthView.startAnimation(earthAnim);
        this.moonView.startAnimation(moonAnim);
    }
}