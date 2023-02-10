package com.example.mudiagaotojareri_comp304sec004_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SeActivity extends AppCompatActivity {
    private AnimationDrawable galleryAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se);

        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeActivity.this.startAnimation();
            }
        });

        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeActivity.this.stopAnimation();
            }
        });
    }
    private void startAnimation() {
        ImageView view = findViewById(R.id.imageView);
        Drawable[] images = new Drawable[11];
        images[0] = getDrawable(R.drawable.image1);
        images[1] = getDrawable(R.drawable.image2);
        images[2] = getDrawable(R.drawable.image1);
        images[3] = getDrawable(R.drawable.image7);
        images[4] = getDrawable(R.drawable.image4);
        images[5] = getDrawable(R.drawable.image5);
        images[6] = getDrawable(R.drawable.image6);
        images[7] = getDrawable(R.drawable.image7);
        images[8] = getDrawable(R.drawable.image8);
        images[9] = getDrawable(R.drawable.image9);
        images[10] = getDrawable(R.drawable.image10);

        this.galleryAnimation = new AnimationDrawable();
        this.galleryAnimation.setOneShot(false);

        for (Drawable image : images) {
            this.galleryAnimation.addFrame(image, getResources().getInteger(R.integer.animationDur));
        }

        view.setBackgroundDrawable(this.galleryAnimation);
        this.galleryAnimation.start();
        Toast.makeText(this, getResources().getString(R.string.startAnimation), Toast.LENGTH_SHORT).show();

    }

    private void stopAnimation() {
        this.galleryAnimation.stop();
        Toast.makeText(this, getResources().getString(R.string.stopAnimation), Toast.LENGTH_SHORT).show();
    }


}