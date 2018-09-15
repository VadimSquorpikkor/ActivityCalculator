package com.squorpikkor.app.activitycalculator;

/*Created by Squorpikkor 19.08.18**/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    RA_Element cs = new Cs();
    ListView lvMain;
    ArrayList<RA_Source> sourceList = new ArrayList<>();
    SourceAdapter sourceAdapter;
    Database2 database2;
    Button addNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database2 = new Database2(this);

        addNewButton = findViewById(R.id.add_new_button);

        // находим список
        lvMain = findViewById(R.id.lvMain);

        // создаем адаптер
       sourceAdapter = new SourceAdapter(this,
                R.layout.source_list_item, sourceList);

        // присваиваем адаптер списку
        //lvMain.setAdapter(sourceAdapter);

        //Лисенер для элемента ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SourceSettingActivity.class);
                int pos = sourceList.get((int)id).getID();
                intent.putExtra("id", pos);
                startActivity(intent);
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_new_button:
                        database2.addRA_Source();
                        sourceList.clear();
                        sourceList.addAll(database2.getAllRA_Sources());
                        lvMain.setAdapter(sourceAdapter);
                }
            }
        };

        addNewButton.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sourceList.clear();
        sourceList.addAll(database2.getAllRA_Sources());
        lvMain.setAdapter(sourceAdapter);

    }

}
