package com.squorpikkor.app.activitycalculator;

public class OldStuff {

    //Старые методы  и классы, которые жалко удалять


    /*public ArrayList<RA_Source> getSourceList() {   //Имя не правильное, должно быть что-то типа SourceList или loadedSourceList
        SQLiteDatabase db = database.getWritableDatabase();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<RA_Source> sourceArray = new ArrayList<>();
        RA_Source ra_source;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                ra_source = new RA_Source();
                ra_source.setName(cursor.getString(1));
                //ra_source.setMagazine(cursor.getString(2));
                sourceArray.add(ra_source);
            }
        }
        cursor.close();
        db.close();
        return sourceArray;
    }*/

    /*@Override
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
    }*/


    /////////////////////////////////////////////////////////////////////////////\\\\


    /*    //Сделать загрузку из БД не в ЛистВью, а в ЭрейЛист
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
    }*/



//    DatabaseHelper databaseHelper;
//    SQLiteDatabase db;
//    Cursor userCursor;
//    SimpleCursorAdapter userAdapter;
//    ListView userList;




//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        // Закрываем подключение и курсор
//        database.close();
////        userCursor.close();
//    }





}