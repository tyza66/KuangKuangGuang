package com.tyza66.kuangkuangguang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try{
            sqLiteDatabase.execSQL("CREATE TABLE floder(id varchar2(20) PRIMARY KEY,title varchar2(100));");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String date = sdf.format(new Date());
            sqLiteDatabase.execSQL("INSERT INTO floder VALUES('" + "d" + date + "','演示集合');");
            sqLiteDatabase.execSQL("CREATE TABLE d" + date + "(title varclar2(100),text varchar2(500),statu int);" );
            sqLiteDatabase.execSQL("INSERT INTO d" + date + " VALUES('演示标题1','演示内容1',0);");
            sqLiteDatabase.execSQL("INSERT INTO d" + date + " VALUES('演示标题2','演示内容2',0);");
            sqLiteDatabase.execSQL("INSERT INTO d" + date + " VALUES('演示标题3','演示内容3',0);");
            sqLiteDatabase.setTransactionSuccessful();
        }catch(Exception e){
            Log.d("sqLiteOnCreate", String.valueOf(e));
        }finally {
            sqLiteDatabase.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
