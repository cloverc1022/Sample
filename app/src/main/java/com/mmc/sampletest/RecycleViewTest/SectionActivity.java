package com.mmc.sampletest.RecycleViewTest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmc.sampletest.R;
import com.mmc.sampletest.utils.Trans2PinYin;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView_one;
    private MyAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        recyclerView_one = (RecyclerView) findViewById(R.id.recycleView_one);
        data = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }

        try {
            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = this.getContentResolver().query(contactUri,
                    new String[]{"display_name", "sort_key", "contact_id","data1"},
                    null, null, "sort_key");
            String contactName;
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if (contactName!=null)
                    data.add(contactName);
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题

        }catch (Exception e){
            e.printStackTrace();
        }

        adapter = new MyAdapter(data);
        recyclerView_one.setAdapter(adapter);
        recyclerView_one.addItemDecoration(new SectionDecoration(this, new SectionDecoration.DecorationCallback() {
            @Override
            public String getFristLetter(int position) {
                if (data.size() == 0){
                    return null;
                }else {
                    return Trans2PinYin.trans2PinYin(data.get(position)).substring(0, 1).toUpperCase();
                }
            }

            @Override
            public long getGroupId(int position) {
                if (data.size() == 0){
                    return 0;
                }else {
                    return Character.toUpperCase(Trans2PinYin.trans2PinYin(data.get(position)).toUpperCase().charAt(0));
                }
            }
        }));
        recyclerView_one.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
