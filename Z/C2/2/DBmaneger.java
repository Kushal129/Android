package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DBmaneger {

    DBhelper helper;
    Context context;
    SQLiteDatabase db;

    public DBmaneger(Context c) {
        this.context = c;
    }

    public DBmaneger open() throws SQLException{
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }


    public long add_emp (String pass , String email , String gender , String role , String vima){
        ContentValues cv = new ContentValues();
        cv.put(helper.Emp_email , email);
        cv.put(helper.Emp_pass , pass);
        cv.put(helper.gender,gender);
        cv.put(helper.role ,role);
        cv.put(helper.vima ,vima);

        long i = db.insert(helper.tblname,null,cv);
        return i;
    }

    public Cursor GetEmplist(){
        Cursor c = db.query(helper.tblname,null,null,null,null,null,null);
        return c;
    }
}
