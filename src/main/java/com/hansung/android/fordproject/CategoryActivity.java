package com.hansung.android.fordproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Button longShirt, tShirt, jacket, pants, halfPants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //뷰와 연결하기
        longShirt = findViewById(R.id.longshirt_btn);
        tShirt = findViewById(R.id.tshirts_btn);
        jacket = findViewById(R.id.jacket_btn);
        pants = findViewById(R.id.pants_btn);
        halfPants = findViewById(R.id.halfpants_btn);

        final Intent intent = new Intent(getBaseContext(), PersonalInputActivity.class);

        //클릭했을 때 다음 페이지로 이동하기
        longShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        tShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        jacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        halfPants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}