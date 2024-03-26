package com.example.loginpage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;

public class DBhelper extends SQLiteOpenHelper {

    private static  final String dbname = "test_cie.db";
    private static final int version = 1;

    public static final String tblname = "tbl_emp";
    public static final String eid = "_id";
    public static final String ename = "email";
    public static final String pass = "pass";


    private static final String create_table = "CREATE TABLE " + tblname +
            "( " +
            eid + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ename + " TEXT NOT NULL ," +
            pass + " TEXT NOT NULL "  +
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
        db.execSQL(" DROP TABLE IF EXISTS " + tblname );
    }
}
