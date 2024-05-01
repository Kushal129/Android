package com.example.lone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_db";
    static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    static final String LTABLE_NAME = "loan_applications";
    private static final String LCOLUMN_ID = "_id";
    private static final String COLUMN_LOAN_TYPE = "loan_type";
    private static final String COLUMN_LOAN_AMOUNT = "loan_amount";
    private static final String COLUMN_LOAN_TENURE = "loan_tenure";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_LOAN_EMI = "loan_emi";


    public Dbhelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT" +
                ");");

        String CREATE_TABLE = "CREATE TABLE " + LTABLE_NAME + " (" +
                LCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOAN_TYPE + " TEXT, " +
                COLUMN_LOAN_AMOUNT + " REAL, " +
                COLUMN_LOAN_TENURE + " INTEGER, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CONTACT + " TEXT, " +
                COLUMN_LOAN_EMI + " REAL" +
                ")";
        db.execSQL(CREATE_TABLE);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + LTABLE_NAME);
        onCreate(db);
    }

    public long insertUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID},
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{username, password}, null, null, null);

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
    }

    public Cursor getUserByUsernamePassword(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID},
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{username, password}, null, null, null);
        return cursor;
    }


    public long insertLoanApplication(String loanType, double loanAmount, int loanTenure, String email, String name, String contact, double loanEMI) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOAN_TYPE, loanType);
        values.put(COLUMN_LOAN_AMOUNT, loanAmount);
        values.put(COLUMN_LOAN_TENURE, loanTenure);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_LOAN_EMI, loanEMI);
        long id = db.insert(LTABLE_NAME, null, values);
        db.close();
        return id;
    }
}