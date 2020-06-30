package com.hansung.android.fordproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class ByDayActivity extends AppCompatActivity {
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    MaterialCalendarView materialCalendarView;
    TinyDB tinyDB;
    ArrayList<String> nameS = new ArrayList<String>();
    ArrayList<String> ageS = new ArrayList<String>();
    ArrayList<String> heightS = new ArrayList<String>();
    ArrayList<String> weightS = new ArrayList<String>();
    ArrayList<String> genderS = new ArrayList<String>();
    ArrayList<String> dateS = new ArrayList<String>();
    ArrayList<String> armsS = new ArrayList<String>();
    ArrayList<String> legsS = new ArrayList<String>();
    ArrayList<String> shouldersS = new ArrayList<String>();
    ArrayList<String> chestS = new ArrayList<String>();
    ArrayList<String> waistS = new ArrayList<String>();
    ArrayList<String> hipS = new ArrayList<String>();
    ArrayList<Character> nameFirst = new ArrayList<Character>();
    ArrayList<String[]> person = new ArrayList<String[]>();
    ArrayList<String> resultS = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_day);

        tinyDB = new TinyDB(getBaseContext());

        //db에서 가져온 값 Arraylist에 넣어주기
        nameS = tinyDB.getListString("name");
        ageS = tinyDB.getListString("age");
        heightS = tinyDB.getListString("height");
        weightS = tinyDB.getListString("weight");
        genderS = tinyDB.getListString("gender");
        armsS = tinyDB.getListString("arms");
        legsS = tinyDB.getListString("legs");
        shouldersS = tinyDB.getListString("shoulders");
        chestS = tinyDB.getListString("chest");
        waistS = tinyDB.getListString("waist");
        hipS = tinyDB.getListString("hip");
        dateS = tinyDB.getListString("date");

        //person이라는 리스트에 각 사람별 이름, 나이~~~ 다 하나의 리스트로 묶어주기
        for (int i = 0; i < nameS.size(); i++) {
            person.add(new String[]{nameS.get(i), ageS.get(i), heightS.get(i), weightS.get(i), genderS.get(i), armsS.get(i), legsS.get(i), shouldersS.get(i), chestS.get(i), waistS.get(i), hipS.get(i), dateS.get(i)});
        }

        // date에 해당되는 칸 색갈 바꿔주기
        for (int i = 0; i < nameS.size(); i++) {
            String date = dateS.get(i);
            String year = date.substring(0,4);
            String month = date.substring(4,6);
            String day = date.substring(6,8);
            resultS.add(year+","+month+","+day);
            Log.e(year, month + day);

        }
        //날짜 배열 리스트 예시 생성
        String[] result = {"d" ,"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"
                ,"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};

        //사람 수 만큼 result 결과값 배열에 넣어주기
        for(int i=0; i<resultS.size(); i++){
            result[i] = resultS.get(i);
            Log.e("result", result.length +"    "+ resultS.get(i));
        }


        materialCalendarView = findViewById(R.id.calendarView);

        //달력 뷰 생성
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //주말에는 칸 색을 다르게 해서 보여주기
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);


        // 값이 들어있는 날짜에는 다른 모양으로 표시
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();
                String month, day;
                if (Month < 9) {
                    month = "0" + Month;
                } else {
                    month = String.valueOf(Month);
                }
                if (Day < 9) {
                    day = "0" + Day;
                } else {
                    day = String.valueOf(Day);
                }

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = String.valueOf(Year) + month + day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();
                for (int i = 0; i < nameS.size(); i++) {
                    if (shot_Day.equals(dateS.get(i))) {
                        Intent intent = new Intent(getBaseContext(), PersonalShowActivity.class);
                        intent.putExtra("name", person.get(i)[0]);
                        intent.putExtra("age", person.get(i)[1]);
                        intent.putExtra("height", person.get(i)[2]);
                        intent.putExtra("weight", person.get(i)[3]);
                        intent.putExtra("gender", person.get(i)[4]);
                        intent.putExtra("arms", person.get(i)[5]);
                        intent.putExtra("shoulders", person.get(i)[6]);
                        intent.putExtra("chest", person.get(i)[7]);
                        intent.putExtra("waist", person.get(i)[8]);
                        intent.putExtra("hip", person.get(i)[9]);
                        intent.putExtra("legs", person.get(i)[10]);
                        intent.putExtra("date", person.get(i)[11]);
                        startActivity(intent);
                    } else {
                    }
                }
            }
        });
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result) {
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for (int i = 0; i < Time_Result.length; i++) {
                int year = 0, month = 0, dayy = 0;
                CalendarDay day = CalendarDay.from(calendar);
                if(Time_Result[i].equals("a")){}else {
                    String[] time = Time_Result[i].split(",");
                    year = Integer.parseInt(time[0]);
                    month = Integer.parseInt(time[1]);
                    dayy = Integer.parseInt(time[2]);
                }
                Log.e("Asdf", Time_Result[i]);

                dates.add(day);
                calendar.set(year, month -1, dayy);
            }


            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays, ByDayActivity.this));
        }
    }
}