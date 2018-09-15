package com.squorpikkor.app.activitycalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SourceSettingActivity extends AppCompatActivity {

    private static final String TAG = "LOGGG!!!";

    EditText nameBox;
    EditText elementBox;
    EditText a0Box;
    EditText halfLifeBox;
    EditText yearBox;
    EditText monthBox;
    EditText dayBox;

    Button saveButton;
    Button deleteButton;

    Database2 database2;
    SQLiteDatabase db;
    int userId = 0;

    RA_Source ra_source;

    AlertDialog.Builder ad;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_setting);

        context = SourceSettingActivity.this;

        nameBox = findViewById(R.id.name);
        elementBox = findViewById(R.id.element);
        a0Box = findViewById(R.id.a0);
        halfLifeBox = findViewById(R.id.half_life);
        yearBox = findViewById(R.id.year);
        monthBox = findViewById(R.id.month);
        dayBox = findViewById(R.id.day);

        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);

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
            deleteButton.setVisibility(View.GONE);
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
                        ad.show();
                        break;
                }
            }
        };

        saveButton.setOnClickListener(listener);
        deleteButton.setOnClickListener(listener);

        ad = new AlertDialog.Builder(context);
        ad.setTitle("Текущий источник будет удален");  // заголовок
        ad.setMessage("Продолжить?"); // сообщение
        ad.setPositiveButton("Гори оно огнём", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(context, "Усточник удален",
                        Toast.LENGTH_LONG).show();
                database2.deleteRA_Source(ra_source);
                goHome();
            }
        });
        ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                //Toast.makeText(context, "Возможно вы правы", Toast.LENGTH_LONG)
                  //      .show();
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(context, "Вы ничего не выбрали",
                        Toast.LENGTH_LONG).show();
            }
        });
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

