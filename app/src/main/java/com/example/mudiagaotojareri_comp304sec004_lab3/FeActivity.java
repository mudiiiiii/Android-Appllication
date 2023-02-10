package com.example.mudiagaotojareri_comp304sec004_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FeActivity extends AppCompatActivity {

    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;
    private ImageView drawingView;
    private TextView xPos;
    private TextView yPos;
    private int curXPos;
    private int curYPos;
    private int xIncrement;
    private int yIncrement;
    private int noIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fe);
        
        this.drawingView = findViewById(R.id.drawingView);
        this.painting();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                this.drawLineDown();
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                this.drawLineLeft();
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                this.drawLineUp();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                this.drawLineRight();
                return true;
        }
        return false;
    }

    private void painting() {
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
        this.paint.setStrokeWidth(getResources().getInteger(R.integer.strokeWidth));
        this.bitmap = Bitmap.createBitmap(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager()
                     .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
        this.drawingView = findViewById(R.id.drawingView);
        this.drawingView.setImageBitmap(this.bitmap);
        this.drawingView.setVisibility(View.VISIBLE);
        this.xPos = findViewById(R.id.xPos);
        this.yPos = findViewById(R.id.yPos);
        this.xIncrement = getResources().getInteger(R.integer.xIncrement);
        this.yIncrement = getResources().getInteger(R.integer.yIncrement);
        this.noIncrement = getResources().getInteger(R.integer.noIncrement);
        this.clearCanvas();

        final RadioButton redLineColorRadioBtn = findViewById(R.id.redLineColor);
        redLineColorRadioBtn.setChecked(true);

        // attach event handlers
        findViewById(R.id.rightArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FeActivity.this.drawLineRight();
                return true;
            }
        });
        findViewById(R.id.upArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FeActivity.this.drawLineUp();
                return true;
            }
        });
        findViewById(R.id.leftArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FeActivity.this.drawLineLeft();
                return true;
            }
        });
        findViewById(R.id.downArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FeActivity.this.drawLineDown();
                return true;
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeActivity.this.clearCanvas();
            }
        });

        final RadioGroup lineColorRadioGroup = findViewById(R.id.lineColor);
        lineColorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idChecked) {
                switch (idChecked) {
                    case R.id.redLineColor:
                        FeActivity.this.paint.setColor(Color.RED);
                        break;
                    case R.id.yellowLineColor:
                        FeActivity.this.paint.setColor(Color.YELLOW);
                        break;
                    case R.id.cyanLineColor:
                        FeActivity.this.paint.setColor(Color.CYAN);
                        break;
                }
            }
        });

        final Spinner lineThicknessSpinner = findViewById(R.id.lineThickness);
        lineThicknessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                FeActivity.this.paint.setStrokeWidth(Integer.parseInt(getResources().getStringArray(R.array.lineThicknessArr)[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void drawLine(Canvas canvas, int xIncrement, int yIncrement) {
        this.xPos.setText("X: " + this.curXPos);
        this.yPos.setText("Y: " + this.curYPos);
        this.canvas.drawLine(curXPos, curYPos, curXPos += xIncrement, curYPos += yIncrement, this.paint);
    }

    private void drawLineLeft() {
        if (this.curXPos - this.xIncrement < 0) {
            Toast.makeText(this, getResources().getString(R.string.leftEdgelimit), Toast.LENGTH_SHORT).show();
        } else {
            this.drawLine(this.canvas, -this.xIncrement, this.noIncrement);
            this.drawingView.invalidate();
        }
    }

    private void drawLineUp() {
        if (this.curYPos - this.yIncrement < 0) {
            Toast.makeText(this, getResources().getString(R.string.upEdgeLimit), Toast.LENGTH_SHORT).show();
        } else {
            this.drawLine(this.canvas, this.noIncrement, -this.yIncrement);
            this.drawingView.invalidate();
        }
    }

    private void drawLineDown() {
        if (this.curYPos + this.yIncrement > this.bitmap.getHeight()) {
            Toast.makeText(this, getResources().getString(R.string.bottomEdgeLimit), Toast.LENGTH_SHORT).show();
        } else {
            this.drawLine(this.canvas, this.noIncrement, this.yIncrement);
            this.drawingView.invalidate();
        }
    }

    private void drawLineRight() {
        if (this.curXPos + this.xIncrement > this.bitmap.getWidth()) {
            Toast.makeText(this, getResources().getString(R.string.rightEdgeLimit), Toast.LENGTH_SHORT).show();
        } else {
            this.drawLine(this.canvas, this.xIncrement, this.noIncrement);
            this.drawingView.invalidate();
        }
    }

    private void clearCanvas() {
        this.canvas.drawColor(Color.WHITE);
        this.curXPos = this.curYPos = 0;
        this.startDrawing();
        this.xPos.setText("X: " + this.curXPos);
        this.yPos.setText("Y: " + this.curYPos);
    }

    private void startDrawing() {
        this.canvas.drawPoint(0, 0, this.paint);
    }
}