package com.example.randompasswordgenerator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.randompasswordgenerator.model.Password;

import java.util.ArrayList;
import java.util.List;

public class PasswordDatabase extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "DATABASE";
    private static final String TABLE_NAME = "PASSWORD_TABLE";
    private static final int DB_VERSION = 1;
    public static final String KEY_PASSWORD = "PASSWORD";
    public static final String KEY_LENGTH = "LENGTH";
    public static final String KEY_ID = "_id";
    private static final String CREATE = "create table " + TABLE_NAME + "( " + KEY_ID + " integer primary key autoincrement, " + KEY_PASSWORD + " text," + KEY_LENGTH + " text)";

    public PasswordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void savePassword(Password p) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_PASSWORD, p.getPassword());
        cv.put(KEY_LENGTH, p.getLength());
        database.insert(TABLE_NAME, null, cv);
        database.close();
    }

    public List<Password> getPasswords() {
        List<Password> passwordList = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String[] columns = new String[]{KEY_ID, KEY_PASSWORD, KEY_LENGTH};
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Password p = new Password();
            p.password = cursor.getString(1);
            p.length = cursor.getString(2);
            passwordList.add(p);
        }
        return passwordList;
    }

    public void clearAll() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
        database.close();
    }
}
