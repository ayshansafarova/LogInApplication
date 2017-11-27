package com.example.admin.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;


/**
 * Created by Admin on 24.11.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "registrate.db";
    private static final String TABLE_NAME = "UsersRegistration";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_SURNAME = "Surname";
    private static final String COLUMN_EMAIL = "Username";
    private static final String COLUMN_PASSWORD = "Password";

    private static final String TABLE_CREATE = "create table if not exists Users (id integer primary key not null , " +
            "name varchar(255) not null , surname varchar(255) not null , username varchar(255) not null,password varchar(255) not null)";
    private static  final String TABLE_DROP = "drop table if exists Users";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TABLE_DROP);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(Users user){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();

        String usedEmail = "select * from " + TABLE_NAME + " where "+COLUMN_EMAIL+"=\'"+user.getEmail()+"\';";
        Cursor cursor1;
        cursor1 = db.rawQuery(usedEmail, null);


        if(cursor1.getCount() != 0)
        {
            return false;
        }else{
            values.put(COLUMN_ID, count);
            values.put(COLUMN_NAME, user.getName());
            values.put(COLUMN_SURNAME, user.getSurname());
            values.put(COLUMN_EMAIL, user.getEmail());
            values.put(COLUMN_PASSWORD, user.getPassword());

            db.insert(TABLE_NAME, null, values);
//        db.close();

            return true;
        }
    }

    public String searchPassword(String username){
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String UNAME, PASS;
        PASS = "not found";

        if(cursor.moveToFirst()){
            do{
                UNAME = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                if(UNAME.equals(username)){
                    PASS = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    break;
                }

            }while (cursor.moveToNext());
        }

        return PASS;
    }
}
