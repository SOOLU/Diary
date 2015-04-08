package com.lmntrx.soolu.mydailydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class DatabaseAdapter  {

    DatabaseHelper helper;
    public DatabaseAdapter(Context context){
        helper=new DatabaseHelper(context);
    }

    public long insertEntry(String entry,String date){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.DIARY_ENTRY,entry);
        contentValues.put(helper.DATE,date);
        long id=db.insert(helper.TABLE_NAME,null,contentValues);
        return id;

    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "diaryDB.db";
        public static final String TABLE_NAME = "TABLE_DIARY";
        public static final String ID = "_id";
        public static final String DIARY_ENTRY = "Diary";
        public static final String DATE = "Date";
        public static final int DATABASE_VERSION = 1;
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DIARY_ENTRY + " VARCHAR(255)," + DATE + " VARCHAR(10))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
