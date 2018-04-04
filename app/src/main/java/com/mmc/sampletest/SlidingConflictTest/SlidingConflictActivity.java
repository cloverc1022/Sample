package com.mmc.sampletest.SlidingConflictTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mmc.sampletest.R;

import java.util.ArrayList;
import java.util.List;

public class SlidingConflictActivity extends AppCompatActivity {

    private ListView lv_1,lv_2,lv_3,lv_4,lv_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_conflict);

        lv_1 = (ListView) findViewById(R.id.lv_1);
        lv_2 = (ListView) findViewById(R.id.lv_2);
        lv_3 = (ListView) findViewById(R.id.lv_3);
        lv_4 = (ListView) findViewById(R.id.lv_4);
        lv_5 = (ListView) findViewById(R.id.lv_5);
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        lv_1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv_2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv_3.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv_4.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv_5.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
    }
}
