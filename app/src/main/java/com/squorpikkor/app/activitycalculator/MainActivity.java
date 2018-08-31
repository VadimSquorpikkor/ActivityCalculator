package com.squorpikkor.app.activitycalculator;

/**
 * Created by Squorpikkor 19.08.18
 **/

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.squorpikkor.app.activitycalculator.RA_Element.Cs;
import com.squorpikkor.app.activitycalculator.RA_Element.RA_Element;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final double A0_516 = 91.3;
    private final double A0_517 = 88.8;
    private final double A0_518 = 94.6;
    private final double A0_519 = 88.2;
    private final double A0_520 = 91.3;
    private final double A0_521 = 93.5;
    private final double A0_2910 = 67.7;

    RA_Element cs = new Cs();
    ActivityCalculator activityCalculator;
    ListView lvMain;
    ArrayList<RA_Source> sourceList = new ArrayList<>();
    SourceAdapter sourceAdapter;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityCalculator = new ActivityCalculator();
        sourceList.add(new RA_Source("516"));

        sourceList = new ArrayList<>();
        sourceList.add(new RA_Source("№516", cs, A0_516));
        sourceList.add(new RA_Source("№517", cs, A0_517));
        sourceList.add(new RA_Source("№518", cs, A0_518));
        sourceList.add(new RA_Source("№519", cs, A0_519));
        sourceList.add(new RA_Source("№520", cs, A0_520));
        sourceList.add(new RA_Source("№521", cs, A0_521));
        sourceList.add(new RA_Source("№2910", cs, A0_2910));

        // находим список
        //lvMain = findViewById(R.id.lvMain);

        // создаем адаптер
        //sourceAdapter = new SourceAdapter(this,
        //        R.layout.source_list_item, sourceList);

        // присваиваем адаптер списку
        //lvMain.setAdapter(sourceAdapter);

        /////////////////////////////////////////////////////////////
        databaseHelper = new DatabaseHelper(getApplicationContext());

        userList = findViewById(R.id.lvMain);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SourceSettingActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }


    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, SourceSettingActivity.class);
        startActivity(intent);
    }

    //Сделать загрузку из БД не в ЛистВью, а в ЭрейЛист
    //ЛистВью будет формироваться уже из Листа
    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_T_POL};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, R.layout.source_list_item,
                userCursor, headers, new int[]{R.id.name, R.id.activity}, 0);
        userList.setAdapter(userAdapter);
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }

}
