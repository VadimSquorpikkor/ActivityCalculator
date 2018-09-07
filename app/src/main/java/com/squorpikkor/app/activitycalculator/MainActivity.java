package com.squorpikkor.app.activitycalculator;

/**
 * Created by Squorpikkor 19.08.18
 * **/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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
    ListView lvMain;
    ArrayList<RA_Source> sourceList = new ArrayList<>();
    SourceAdapter sourceAdapter;
    Database2 database2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database2 = new Database2(this);

        sourceList.add(new RA_Source("№516", cs, A0_516, 2016, 10, 12));
        sourceList.add(new RA_Source("№517", cs, A0_517, 2016, 10, 12));
        sourceList.add(new RA_Source("№518", cs, A0_518, 2016, 10, 12));
        sourceList.add(new RA_Source("№519", cs, A0_519, 2016, 10, 12));
        sourceList.add(new RA_Source("№520", cs, A0_520, 2016, 10, 12));
        sourceList.add(new RA_Source("№521", cs, A0_521, 2016, 10, 12));
        sourceList.add(new RA_Source("№2910", cs, A0_2910, 2016, 5, 17));


//        sourceList.add(new RA_Source("#516", "Cesium"));
        //database2.addRA_Source(new RA_Source("#516", "Cesium"));



        //sourceList.addAll(database2.getAllRA_Sources());этот
//        //////sourceList.addAll(getSourceList());



        // находим список
        lvMain = findViewById(R.id.lvMain);

        // создаем адаптер
       sourceAdapter = new SourceAdapter(this,
                R.layout.source_list_item, sourceList);

        // присваиваем адаптер списку
        lvMain.setAdapter(sourceAdapter);



    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, SourceSettingActivity.class);
        startActivity(intent);
    }



}
