package com.example.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBmanager {
    private DBhelper helper;
    private Context context;
    private SQLiteDatabase db;

    public DBmanager(Context c){
        this.context = c;
    }

    public DBmanager open() throws SQLException
    {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
        return this;
    }

    public long Add_Emp (String Ename , String Pass){
        ContentValues cv = new ContentValues();
        cv.put(helper.ename,Ename);
        cv.put(helper.pass,Pass);

        long i = db.insert(helper.tblname,null,cv);
        return i;
    }

    public Cursor GetEmplist(){
        Cursor c = db.query(helper.tblname,null,null,null,null,null,null);
        return c;
    }
}
