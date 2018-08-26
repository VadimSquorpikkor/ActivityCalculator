package com.squorpikkor.app.activitycalculator;

/**
 * Created by Squorpikkor 19.08.18
 **/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    RA_Element cs = new Cs();
    ActivityCalculator activityCalculator;
    ListView lvMain;
    ArrayList<RA_Source> sourceList = new ArrayList<>();
    SourceAdapter sourceAdapter;

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

        // находим список
        lvMain = findViewById(R.id.lvMain);

        // создаем адаптер
        sourceAdapter = new SourceAdapter(this,
                R.layout.source_list_item, sourceList);

        // присваиваем адаптер списку
        lvMain.setAdapter(sourceAdapter);
    }


}
