package com.example.viva.locationtracker10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viva on 9/29/2017.
 */

public class DBConnections extends SQLiteOpenHelper {
    public static final String DBNAME ="trackLocation.db" ;
    public static int virsion =1 ;
    public DBConnections(Context context) {
        super(context, DBNAME, null, virsion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists location (id INTEGER primary  key ,Location text)");
        sqLiteDatabase.execSQL("create table if not exists sender (id INTEGER primary  key ,Sender text)");
        sqLiteDatabase.execSQL("create table if not exists code (id INTEGER primary  key ,Code text)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists location");
        sqLiteDatabase.execSQL("Drop table if exists sender");
        sqLiteDatabase.execSQL("Drop table if exists code");


        onCreate( sqLiteDatabase);


    }
    public void insertRowSender (String location){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase() ;
        ContentValues contentValues =new ContentValues ();
        contentValues.put("Sender" ,location);
        sqLiteDatabase.insert("sender",null,contentValues) ;


    }
    public String getSender(){
        SQLiteDatabase  sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from sender",null);
        res.moveToFirst();
        String location= res.getString(res.getColumnIndex("Sender"));
        return location ;
    }

    public  void deleteSender(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from sender where id=1");

    }
    public void updateSender(String location ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update location set Sender ='"+location+"'where id =1");
    }
// metheds for location
    public void insertRowLocation (String location){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase() ;
        ContentValues contentValues =new ContentValues ();
        contentValues.put("Location" ,location);
        sqLiteDatabase.insert("location",null,contentValues) ;


    }
    public String getLocation(){
        SQLiteDatabase  sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from location",null);
        res.moveToFirst();
        String location= res.getString(res.getColumnIndex("Location"));
        return location ;
    }

    public  void deleteLocation(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from location where id=1");

    }
    public void updateLocation(String location ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update location set Location ='"+location+"'where id =1");
    }
    /// code
    public void insertRowCode (String code){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase() ;
        ContentValues contentValues =new ContentValues ();
        contentValues.put("code" ,code);
        sqLiteDatabase.insert("code",null,contentValues) ;


    }
    public String getCode(){
        SQLiteDatabase  sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from code",null);
        res.moveToFirst();
        String code= res.getString(res.getColumnIndex("Code"));
        return code ;
    }

    public  void deleteCode(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from code where id=1");

    }
    public void updateCode(String code ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update code set Code ='"+code+"'where id =1");
    }

}
