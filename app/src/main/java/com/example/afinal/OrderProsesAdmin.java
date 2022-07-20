package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderProsesAdmin extends AppCompatActivity {
    // creating variable for button
    private Button AviewCoursesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_proses_admin);
        AviewCoursesBtn = findViewById(R.id.AidBtnViewCourses);
        AviewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity on button click
                Intent i = new Intent(OrderProsesAdmin.this,OrderDetails.class);
                startActivity(i);
            }
        });
    }
}