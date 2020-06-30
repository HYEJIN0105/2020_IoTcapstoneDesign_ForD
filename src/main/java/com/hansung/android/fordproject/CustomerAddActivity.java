package com.hansung.android.fordproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomerAddActivity extends AppCompatActivity {
    TinyDB tinyDB;
    TextView showDay;
    Button dateSet, next;
    EditText name, age, height, weight, gender;
    String date;
    ArrayList<String> nameS =new ArrayList<String>();
    ArrayList<String> ageS =new ArrayList<String>();
    ArrayList<String> heightS=new ArrayList<String>();
    ArrayList<String>  weightS=new ArrayList<String>();
    ArrayList<String> genderS = new ArrayList<String>();
    ArrayList<String> dateS = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);
        tinyDB = new TinyDB(getBaseContext());

        showDay = findViewById(R.id.date_show_tv);
        next = findViewById(R.id.next_btn);
        name = findViewById(R.id.name_et);
        age = findViewById(R.id.age_et);
        height = findViewById(R.id.height_et);
        weight = findViewById(R.id.weight_et);
        gender = findViewById(R.id.gender_et);
        dateSet = findViewById(R.id.time_set_btn);
        dateSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dated();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //전에 넣어뒀던 db값 불러오기
                nameS = tinyDB.getListString("name");
                ageS = tinyDB.getListString("age");
                heightS = tinyDB.getListString("height");
                weightS = tinyDB.getListString("weight");
                genderS = tinyDB.getListString("gender");
                dateS = tinyDB.getListString("date");


                //리스트에 새로 넣어준 값 추가하기
                nameS.add(name.getText().toString());
                ageS.add(age.getText().toString());
                heightS.add(height.getText().toString());
                weightS.add(weight.getText().toString());
                genderS.add(gender.getText().toString());
                dateS.add(date);
                //db에 넣어주기
                tinyDB.putListString("date",dateS);
                tinyDB.putListString("name", nameS);
                tinyDB.putListString("age",ageS);
                tinyDB.putListString("height",heightS);
                tinyDB.putListString("weight", weightS);
                tinyDB.putListString("gender",genderS);

                Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    //달력에서 날짜 선택하기
    public void dated(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, mDateSetListener,year,month,day);
        datePickerDialog.show();
    }

    //날짜 저장하기, 0000년 00월 00일 형식으로 저장
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            if(i1 < 8){
                if(i2 < 8) {
                    date = String.format("%d0%d0%d", i, i1 + 1, i2);
                    showDay.setText(String.format("%d년 0%d월 0%d일", i, i1 + 1, i2));
                }else{
                    date = String.format("%d0%d%d", i, i1 + 1, i2);
                    showDay.setText(String.format("%d년 0%d월 %d일", i, i1 + 1, i2));
                }
            }else {
                date = String.format("%d%d%d", i, i1 + 1, i2);
                showDay.setText(String.format("%d년 %d월 %d일", i, i1 + 1, i2));
            }
        }
    };


}