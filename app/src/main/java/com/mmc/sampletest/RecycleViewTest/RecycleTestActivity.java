package com.mmc.sampletest.RecycleViewTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmc.sampletest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 上海滩小马哥 on 2018/03/01.
 */

public class RecycleTestActivity extends AppCompatActivity {
    private RecyclerView recyclerView_one;
    private MyAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_test);

        recyclerView_one = (RecyclerView) findViewById(R.id.recycleView_one);
        data = new ArrayList<>();
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        data.add("哈哈");
        adapter = new MyAdapter(data);
        recyclerView_one.setAdapter(adapter);
        recyclerView_one.addItemDecoration(new MyItemDecoration(this));
        recyclerView_one.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> data;

        public MyAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_recycle_test, parent, false);
            return new MyHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyHolder) holder).item_text.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView item_text;

            MyHolder(View itemView) {
                super(itemView);
                item_text = (TextView) itemView.findViewById(R.id.item_text);
            }
        }
    }
}
