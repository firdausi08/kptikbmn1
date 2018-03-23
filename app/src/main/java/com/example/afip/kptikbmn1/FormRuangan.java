package com.example.afip.kptikbmn1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


public class FormRuangan extends AppCompatActivity {

    private EditText edit_acara, nomerhp;
    private Spinner spinner;
    private CheckBox checkBox1 = null;
    private CheckBox checkBox2 = null;
    private CheckBox checkBox3 = null;
    private LinearLayout submit_ruangan;
    String tampil;
    ProgressDialog pd;
    int hour, minute, mYear,mMonth, mDay;

    static final int TIME_DIALOG_ID = 0, TIME_DIALOG_ID2 = 2;
    static final int DATE_DIALOG_ID = 1, DATE_DIALOG_ID2 = 3;
    private EditText txtDate, txtDate2;
    private EditText txtTime, txtTime2;
    private String[] arrMonth = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String[] arrMonth2 = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ruangan);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        edit_acara = (EditText) findViewById(R.id.edit_acara);
        spinner = (Spinner) findViewById(R.id.spinner);
        submit_ruangan = (LinearLayout) findViewById(R.id.submit_ruangan);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtDate2 = (EditText) findViewById(R.id.txtDate2);
        txtTime2 = (EditText) findViewById(R.id.txtTime2);
        nomerhp = (EditText) findViewById(R.id.nomerhp);
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
//        mYear2 = c.get(Calendar.YEAR);
//        mMonth2 = c.get(Calendar.MONTH);
//        mDay2 = c.get(Calendar.DAY_OF_MONTH);



        pd = new ProgressDialog(this);

        submit_ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FormRuangan.this, "Selected "+ spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        submit_ruangan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String acara = edit_acara.getText().toString().trim();
                // String foto = foto.getText().toString().trim();
//                if (!acara.isEmpty() && !detail.isEmpty()) {
//                    simpanData(title, detail);
//                }

//                else if (title.isEmpty()) {
//                    title.setError("title tidak boleh kosong");
//                    title.requestFocus();
//                } else if (detail.isEmpty()) {
//                    title.setError("detail gangguan tidak boleh kosong");
//                    title.requestFocus();
//                }
//
//                Intent in = new Intent(FormRuangan.this, RiwayatRuangan.class);
//                FormRuangan.this.startActivity(in);
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) tampil += checkBox1.getText().toString();
                if (checkBox2.isChecked()) tampil += ", " + checkBox2.getText().toString();
                if (checkBox3.isChecked()) tampil += ", " + checkBox3.getText().toString();
//                Toast.makeText(FormRuangan.this,
//                        "Tes " + tampil,
//                        Toast.LENGTH_SHORT).show();
//                tampil = ""; //kosongkan variabel tampil
            }
        };

        //menerapkan listener pada tombol
        submit_ruangan.setOnClickListener(listener);

        txtDate.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
                return true;
            }
        });

        txtTime.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID);
                return true;
            }
        });

        txtDate2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID2);
                return true;
            }
        });

        txtTime2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID2);
                return true;
            }
        });

    }

    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(
            this, mTimeSetListener, hour, minute, true);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(
            this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
        txtDate.setText(sdate);
    }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour)
        {
            hour = hourOfDay;
            minute = minuteOfHour;
            String stime = LPad(""+hour, "0", 2) + ":"+ LPad(""+hour, "0", 2);
            txtTime.setText(stime);
        }
    };

    private static String LPad(String schar, String spad, int len) {
        String sret = schar;
        for (int i = sret.length(); i < len; i++) {
            sret = spad + sret;
        }
        return new String(sret);
    }

    ///////////////////////////////////////////////////////////////////////////////////

//    protected Dialog onCreateDialog2(int id2, int mYear2, int mMonth2, mDay2, hour2, minute2)
//    {
//        int mYear2,mMonth2, mDay2, hour2, minute2;
//
//        switch (id2) {
//            case TIME_DIALOG_ID2:
//                return new TimePickerDialog(
//                        this, mTimeSetListener2, hour, minute, true);
//                        this, mTimeSetListener2, hour, minute, true);
//            case DATE_DIALOG_ID2:
//                return new DatePickerDialog(
//                        this, mDateSetListener2, Year, Month, Day);
//        }
//        return null;
//    }
//
//    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener()
//    {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
//            String sdate = arrMonth2[monthOfYear] + " " + LPad2(dayOfMonth + "", "0", 2) + ", " + year;
//            txtDate2.setText(sdate);
//        }
//    };
//
//    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener()
//    {
//
//        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour)
//        {
//            String stime = LPad2(""+hourOfDay, "0", 2) + ":"+ LPad(""+hourOfDay, "0", 2);
//            txtTime2.setText(stime);
//        }
//    };
//
//    private static String LPad2(String schar, String spad, int len) {
//        String sret2 = schar;
//        for (int i = sret2.length(); i < len; i++) {
//            sret2 = spad + sret2;
//        }
//        return new String(sret2);
//    }


}
