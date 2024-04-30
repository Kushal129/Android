package com.example.a129_prectical12;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button setDateButton, setTimeButton, setNameButton;
    TextView dateTextView, timeTextView, nameTextView;
    int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener (findViewById (R.id.main), ( v, insets ) -> {
            Insets systemBars = insets.getInsets (WindowInsetsCompat.Type.systemBars ());
            v.setPadding (systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setDateButton = findViewById (R.id.setDateButton);
        setTimeButton = findViewById (R.id.setTimeButton);
        setNameButton = findViewById (R.id.setNameButton);
        dateTextView = findViewById (R.id.dateTextView);
        timeTextView = findViewById (R.id.timeTextView);
        nameTextView = findViewById (R.id.nameTextView);
        setDateButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                showDatePickerDialog ();
            }
        });
        setTimeButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                showTimePickerDialog ();
            }
        });
        setNameButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                showNameInputDialog ();
            }
        });
    }

    protected void showDatePickerDialog () {
        final Calendar c = Calendar.getInstance ();
        mYear = c.get (Calendar.YEAR);
        mMonth = c.get (Calendar.MONTH);
        mDay = c.get (Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog (this, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet (
                            DatePicker view, int year, int
                            monthOfYear, int dayOfMonth
                    ) {
                        dateTextView.setText ("Selected Date: " + dayOfMonth + "-" + ( monthOfYear + 1 ) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show ();
    }

    protected void showTimePickerDialog () {
        final Calendar c = Calendar.getInstance ();
        mHour = c.get (Calendar.HOUR_OF_DAY);
        mMinute = c.get (Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog (this, new TimePickerDialog.OnTimeSetListener () {
                    @Override
                    public void onTimeSet (
                            TimePicker view, int
                            hourOfDay,
                            int minute
                    ) {
                        timeTextView.setText ("Selected Time: " + hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show ();
    }

    protected void showNameInputDialog () {
        final Dialog dialog = new Dialog (this);
        dialog.setTitle ("Enter Name:");
        dialog.setContentView (R.layout.custom_dialog);
        Button submitButton =
                dialog.findViewById (R.id.submitButton);
        final TextView inputName =
                dialog.findViewById (R.id.inputName);
        submitButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                String name = inputName.getText ().toString ();
                nameTextView.setText ("Entered Name: " + name);
                dialog.dismiss ();
            }
        });
        dialog.show ();
    }
}