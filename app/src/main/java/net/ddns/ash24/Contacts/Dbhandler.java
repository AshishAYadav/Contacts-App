package net.ddns.ash24.Contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="main.db";
    public static final String TABLE_NAME="CONTACT";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="SNAME";
    public static final String COL_4="EMAIL";
    public static final String COL_5="MOBILE";


    public Dbhandler(Context context){
    super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SNAME TEXT, EMAIL VARCHAR(20), MOBILE INTEGER(12) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String sname,String email, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, sname);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, mobile);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor displayContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
