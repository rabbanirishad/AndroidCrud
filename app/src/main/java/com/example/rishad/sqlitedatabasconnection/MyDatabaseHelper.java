package com.example.rishad.sqlitedatabasconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Info.db";
    private static  final String TABLE_NAME ="student_info";
    private static final int VERSION=4;

    private static final String ID ="Id";
    private static final String NAME ="NAme";
    private static final String AGE ="Age";
    private static final String GENDER ="Gender";

    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+AGE+" INTEGER, "+GENDER+" VARCHAR(255));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;
    private Context context;


    public MyDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"onCreate is Called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"onUpdate is Called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }catch (Exception e){
            Toast.makeText(context,"Exception"+e,Toast.LENGTH_LONG).show();
        }

    }

    public long insertData(String name, String age, String gender){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
       long rowId= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
       return rowId;

    }
   public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       Cursor cursor= sqLiteDatabase.rawQuery(SELECT_ALL,null);
       return cursor;
    }

    public boolean updateData(String id, String name, String age, String gender){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        contentValues.put(ID,id);

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return true;
    }
   public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
      return  sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }
}
