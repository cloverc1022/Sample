package com.mmc.sampletest.sqliteTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mmc.sampletest.R;

public class SqliteTestActivity extends AppCompatActivity {

    private TestGradeDao testGradeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);

        testGradeDao = new TestGradeDao(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGradeDao.insert("马超", 100, "A");
            }
        });
    }
}
