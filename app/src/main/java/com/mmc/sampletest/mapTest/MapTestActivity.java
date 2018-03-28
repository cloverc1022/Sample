package com.mmc.sampletest.mapTest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mmc.sampletest.R;
import com.mmc.sampletest.bean.PoisInfoBean;
import com.mmc.sampletest.bean.SearchResultBean;
import com.mmc.sampletest.utils.FileHelper;
import com.mmc.sampletest.utils.ProgressDialogManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapTestActivity extends AppCompatActivity {
    public static final String LOADING = "LOADING";
    public static final String AK = "6dcc7c15012ab7601dbdbf492e2d83b6";
    public String TAG = this.getClass().getSimpleName();
    private EditText et_city, et_position;
    private Button search1, reset1;
    private TextView tv_center;
    private EditText et_keyword, et_radius;
    private Button search2, reset2, save;
    private RecyclerView rv_result;
    private Dialog progressDialog;
    private MapAdapter adapter;
    private String location;
    private int positionPage = 1;
    private int perimeterPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);

        et_city = (EditText) findViewById(R.id.et_city);
        et_position = (EditText) findViewById(R.id.et_position);
        et_keyword = (EditText) findViewById(R.id.et_keyword);
        et_radius = (EditText) findViewById(R.id.et_radius);
        search1 = (Button) findViewById(R.id.search1);
        reset1 = (Button) findViewById(R.id.reset1);
        search2 = (Button) findViewById(R.id.search2);
        reset2 = (Button) findViewById(R.id.reset2);
        save = (Button) findViewById(R.id.save);
        tv_center = (TextView) findViewById(R.id.tv_center);
        rv_result = (RecyclerView) findViewById(R.id.rv_result);
        adapter = new MapAdapter(this);
        rv_result.setAdapter(adapter);
        rv_result.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv_result.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new MapAdapter.OnItemClickListener() {
            @Override
            public void onClick(PoisInfoBean bean) {
                location = bean.location;
                tv_center.setText(bean.name);
            }
        });
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionPage = 1;
                adapter.clearData();
                String city = et_city.getText().toString().trim();
                String position = et_position.getText().toString().trim();
                ProgressDialogManager.getDialogManager().showDialog(LOADING,MapTestActivity.this,"加载中", null, 600000);
                doSearchPosition(city, position);
            }
        });
        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionPage = 0;
                et_city.setText("");
                et_position.setText("");
            }
        });
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perimeterPage = 1;
                adapter.clearData();
                String city = et_city.getText().toString().trim();
                String keyword = et_keyword.getText().toString().trim();
                String radius = et_radius.getText().toString().trim();
                ProgressDialogManager.getDialogManager().showDialog(LOADING,MapTestActivity.this,"加载中", null, 600000);
                doSearchPerimeter(city, keyword, radius);
            }
        });
        reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_center.setText("");
                et_keyword.setText("");
                et_radius.setText("");
                perimeterPage = 0;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialogManager.getDialogManager().showDialog(LOADING,MapTestActivity.this,"加载中", null, 600000);
                List<PoisInfoBean> data = adapter.getData();
                StringBuilder stringBuilder = new StringBuilder();
                for (PoisInfoBean bean : data) {
                    stringBuilder.append(bean.name);
                    stringBuilder.append("\r\n");
                    stringBuilder.append(bean.pname);
                    stringBuilder.append(bean.cityname);
                    stringBuilder.append(bean.adname);
                    stringBuilder.append(bean.address);
                    stringBuilder.append("\r\n");
                    stringBuilder.append("\r\n");
                }
                FileHelper.saveMapData(stringBuilder.toString());
                ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
            }
        });
    }

    private void doSearchPosition(final String city, final String position) {
        if (positionPage == 0
                || TextUtils.isEmpty(city)
                || TextUtils.isEmpty(position)) {
            ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
            Toast.makeText(this, "参数缺失", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://restapi.amap.com/v3/place/text?" + "key=" + AK + "&keywords=" + position + "&city=" + city + "&citylimit=true" + "&offset=25" + "&page=" + positionPage + "&output=JSON";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    int code = response.code();
                    final String result = response.body().string();
                    Log.d(TAG, "获取数据成功了");
                    Log.d(TAG, "response.code()==" + code);
                    Log.d(TAG, "response.body().string()==" + result);

                    final SearchResultBean bean = JSON.parseObject(result, SearchResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (null != bean.pois) {
                                adapter.addData(bean.pois);
                                if (bean.pois.size() == 25) {
                                    positionPage++;
                                    doSearchPosition(city, position);
                                } else {
                                    ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
                                    Toast.makeText(MapTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
                                Toast.makeText(MapTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void doSearchPerimeter(final String city, final String keywords, final String radius) {
        if (perimeterPage == 0 || TextUtils.isEmpty(location)
                || TextUtils.isEmpty(city)
                || TextUtils.isEmpty(keywords)
                || TextUtils.isEmpty(radius)) {
            ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
            Toast.makeText(this, "参数缺失", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://restapi.amap.com/v3/place/around?" + "key=" + AK + "&location=" + location + "&keywords=" + keywords + "&city=" + city + "&radius=" + radius + "&offset=25" + "&page=" + perimeterPage + "&output=JSON";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    int code = response.code();
                    final String result = response.body().string();
                    Log.d(TAG, "获取数据成功了");
                    Log.d(TAG, "response.code()==" + code);
                    Log.d(TAG, "response.body().string()==" + result);

                    final SearchResultBean bean = JSON.parseObject(result, SearchResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (null != bean.pois) {
                                adapter.addData(bean.pois);
                                if (bean.pois.size() == 25) {
                                    perimeterPage++;
                                    doSearchPerimeter(city, keywords, radius);
                                } else {
                                    ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
                                    Toast.makeText(MapTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                ProgressDialogManager.getDialogManager().dimissDialog(LOADING, 0);
                                Toast.makeText(MapTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
