package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private static final String dbname = "tbl_emp.db";
    private static final int version = 1;

    public static final String tblname = "tbl_emp";
    public static final String eid = "_id";
    public static final String Emp_email = "email";
    public static final String Emp_pass = "pass";

    public static final String gender = "gender";

    public static final String role = "role";

    public static final String vima = "vima";

public static final String create_table =  "CREATE TABLE " + tblname +
        "( " +
        eid + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
        Emp_email + " TEXT NOT NULL ," +
        Emp_pass + " TEXT NOT NULL , "  +
        gender + " TEXT NOT NULL ," +
        role + " TEXT NOT NULL ," +
        vima + " TEXT NOT NULL " +
        " ); ";


    public DBhelper(@Nullable Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("  DROP TABLE IF EXISTS  " + tblname);
    onCreate(db);
    }
}
