package com.hansung.android.fordproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<String[]> sample;

    public MyAdapter(Context context, ArrayList<String[]> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //총 몇페이지인지 계산
    @Override
    public int getCount() {
        return sample.size();
    }

    //클릭했을 때 몇번째 아이템인지 계싼
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 버튼의 포지션 값 가져오기
    @Override
    public String getItem(int position) {
        return sample.get(0)[position];
    }

    //xml의 텍스트뷰와 연결
    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_name, null);

        TextView movieName = (TextView) view.findViewById(R.id.name_tv);
        movieName.setText(sample.get(position)[0]);

        return view;
    }
}