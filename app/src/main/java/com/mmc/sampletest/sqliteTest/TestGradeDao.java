package com.mmc.sampletest.sqliteTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TestGradeDao {

    private Context context;
    private SQLiteDatabase db;
    private MySqliteOpenHelper dbHelper;

    public TestGradeDao(Context context) {
        this.context = context;
        dbHelper = new MySqliteOpenHelper(context);
    }

    public void insert(String name, int grade, String level){
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String sql = "insert into " + MySqliteOpenHelper.TABLE_NAME + "(name, grade, level) values " + "(" + "'" + name + "'" + ","+ grade + ","+ "'"+ level + "'"+ ")";
        db.execSQL(sql);
        db.setTransactionSuccessful();
    }

    public void delete(String name){

    }

    public void update(String name, int grade, String level){

    }

    public void search(String name){

    }
}
