package com.example.elms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "leave_management";

    // User table name
    private static final String TABLE_USER = "users";

    // User Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USER_TYPE = "user_type";

    // Create table query
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_USER_TYPE + " TEXT"
            + ")";


    // Leave request table name
    private static final String TABLE_LEAVE_REQUEST = "leave_requests";

    // Leave request table columns
    private static final String KEY_LEAVE_ID = "leave_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_REASON = "reason";
    private static final String KEY_STATUS = "status";

    // Create table query for leave requests
    private static final String CREATE_TABLE_LEAVE_REQUEST = "CREATE TABLE " + TABLE_LEAVE_REQUEST + "("
            + KEY_LEAVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_DATE + " TEXT,"
            + KEY_REASON + " TEXT,"
            + KEY_STATUS + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        // Creating tables
//        db.execSQL(CREATE_TABLE_USER);
//        db.execSQL(CREATE_TABLE_LEAVE_REQUEST);

        try {
            // Creating tables
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_LEAVE_REQUEST);
            Log.d("DatabaseHelper", "Tables created successfully");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error creating tables: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE_REQUEST);

        // Create tables again
        onCreate(db);
    }

    // Method to add a user to the database
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add data
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_USER_TYPE, user.getUserType());

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        return id;
    }

    // Method to get user details based on username and password
    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Select query
        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_USER_TYPE},
                KEY_USERNAME + "=? AND " + KEY_PASSWORD + "=?", new String[]{username, password}, null, null, null, null);

        // Check if the cursor is not null and move to the first row
        if (cursor != null)
            cursor.moveToFirst();

        // If cursor is null or no rows are returned, return null
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        // Creating a new User object with the retrieved values
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
        user.setUserType(cursor.getString(cursor.getColumnIndex(KEY_USER_TYPE)));

        // Closing cursor and database connection
        cursor.close();
        db.close();

        // Return the user object
        return user;
    }

    // Method to add a leave request to the database
    public long addLeaveRequest(String date, String reason, String status) {
        long id = -1; // Initialize id with -1 to indicate failure
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();

            // Create ContentValues to add data
            ContentValues values = new ContentValues();
            values.put(KEY_DATE, date);
            values.put(KEY_REASON, reason);
            values.put(KEY_STATUS, status);

            // Inserting Row
            id = db.insert(TABLE_LEAVE_REQUEST, null, values);
            Log.d("DatabaseHelper", "Inserted leave request with ID: " + id);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting leave request: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close(); // Closing database connection
            }
        }
        return id;
    }


    // Method to retrieve all leave requests from the database
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE_REQUEST;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setId(cursor.getInt(cursor.getColumnIndex(KEY_LEAVE_ID)));
                leaveRequest.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                leaveRequest.setReason(cursor.getString(cursor.getColumnIndex(KEY_REASON)));
                leaveRequest.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));

                // Add leave request to list
                leaveRequests.add(leaveRequest);
            } while (cursor.moveToNext());
        }

        // Close cursor and database connection
        cursor.close();
        db.close();

        // Return list of leave requests
        return leaveRequests;
    }

    // Method to update the status of a leave request
    public int updateLeaveRequestStatus(int leaveRequestId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);

        // Update the row with the specified leaveRequestId
        return db.update(TABLE_LEAVE_REQUEST, values, KEY_LEAVE_ID + " = ?",
                new String[]{String.valueOf(leaveRequestId)});
    }
}
