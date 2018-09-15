package com.squorpikkor.app.activitycalculator;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SourceSettingActivity extends AppCompatActivity {

    private static final String TAG = "LOGGG!!!";

    EditText nameBox;
    EditText elementBox;
    EditText a0Box;
    EditText halfLifeBox;
    EditText yearBox;
    EditText monthBox;
    EditText dayBox;

    Button delButton;
    Button saveButton;

    Database2 database2;
    SQLiteDatabase db;
    int userId = 0;

    RA_Source ra_source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_setting);

        nameBox = findViewById(R.id.name);
        elementBox = findViewById(R.id.element);
        a0Box = findViewById(R.id.a0);
        halfLifeBox = findViewById(R.id.half_life);
        yearBox = findViewById(R.id.year);
        monthBox = findViewById(R.id.month);
        dayBox = findViewById(R.id.day);

        delButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);

        database2 = new Database2(this);
        db = database2.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            ra_source = database2.getRA_Source(userId);
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }

        Log.e(TAG, "USER_ID: " + userId);
        ra_source = database2.getRA_Source(userId);
        setTextViewByRa_source();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.saveButton:
                        setRa_sourceByTextView();
                        database2.updateRA_Source(ra_source);
                        goHome();
                        break;
                    case R.id.deleteButton:
                        Log.e("LOGG!!", "delete!!!");
                        database2.deleteRA_Source(ra_source);
                        goHome();
                        break;

                }
            }
        };

        saveButton.setOnClickListener(listener);
        delButton.setOnClickListener(listener);
    }

    private void goHome() {
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void setRa_sourceByTextView() {
        ra_source.setName(nameBox.getText().toString());
        ra_source.setElement(elementBox.getText().toString());
        ra_source.setA0(Double.parseDouble(a0Box.getText().toString()));
        ra_source.setHalfLife(Double.parseDouble(halfLifeBox.getText().toString()));
        ra_source.setYear(Integer.parseInt(yearBox.getText().toString()));
        ra_source.setMonth(Integer.parseInt(monthBox.getText().toString()));
        ra_source.setDay(Integer.parseInt(dayBox.getText().toString()));

    }

    private void setTextViewByRa_source() {
        nameBox.setText(ra_source.getName());
        elementBox.setText(ra_source.getElement());
        a0Box.setText(String.valueOf(ra_source.getA0()));
        halfLifeBox.setText(String.valueOf(ra_source.getHalfLife()));
        yearBox.setText(String.valueOf(ra_source.getYear()));
        monthBox.setText(String.valueOf(ra_source.getMonth()));
        dayBox.setText(String.valueOf(ra_source.getDay()));
    }

}

