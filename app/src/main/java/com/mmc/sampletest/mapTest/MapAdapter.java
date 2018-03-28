package com.mmc.sampletest.mapTest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmc.sampletest.R;
import com.mmc.sampletest.bean.PoisInfoBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 上海滩小马哥 on 2018/03/28.
 */

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MyHolder> {

    private Context context;
    public List<PoisInfoBean> data;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(PoisInfoBean bean);
    }

    public MapAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void addData(List<PoisInfoBean> data) {
        this.data.addAll(data);
        notifyItemRangeInserted(this.data.size(), data.size());
    }

    public List<PoisInfoBean> getData(){
        return data;
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.map_item_view, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final int pos = position;
        int distance = 0;

        holder.name.setText(data.get(position).name);
        holder.address.setText(data.get(position).pname + data.get(position).cityname + data.get(position).adname + data.get(position).address);

        if (TextUtils.isEmpty(data.get(position).distance) || TextUtils.equals(data.get(position).distance, "[]")){
            distance = 0;
            holder.distance.setText("距离:" + distance + "m");
        }else {
            distance = Integer.valueOf(data.get(position).distance);
            if (distance >= 1000){
                DecimalFormat format=new DecimalFormat(".00");//不足1位,会以0补足.
                String v=format.format((float)distance/1000);
                holder.distance.setText("距离:" + v + "km");
            }else {
                holder.distance.setText("距离:" + distance + "m");
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(data.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView name, address, distance;
        private View itemView;

        private MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}

