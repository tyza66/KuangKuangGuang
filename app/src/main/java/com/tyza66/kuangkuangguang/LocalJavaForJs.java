package com.tyza66.kuangkuangguang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalJavaForJs {
    Context context;
    SQLiteDatabase db;
    WebView webView;

    public LocalJavaForJs(Context context, WebView webView, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
        this.webView = webView;
        pullFloder();
    }

    @JavascriptInterface
    public void test() {
        Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void pullFloder() {
        Cursor cursor = db.query("floder", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            webView.loadUrl("javascript:app.appendFolder('" + id + "','" + title + "')");
        }
        cursor.close();
    }

    @JavascriptInterface
    public void pullFloder2() {
        Handler mainHandler = new Handler(Looper.getMainLooper());

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = db.query("floder", null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String title = cursor.getString(1);
                    webView.loadUrl("javascript:app.appendFolder('" + id + "','" + title + "')");
                }
                cursor.close();
            }
        });
    }

    private void run() {
    }

    @JavascriptInterface
    public void pushFolder(String name) {
        db.beginTransaction();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String date = sdf.format(new Date());
            db.execSQL("INSERT INTO floder VALUES('" + "d" + date + "','" + name + "');");
            db.execSQL("CREATE TABLE d" + date + "(title varclar2(100),text varchar2(500),statu int);");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("sqLiteCommit", String.valueOf(e));
        } finally {
            db.endTransaction();
        }
    }

    @JavascriptInterface
    public String deleteFolder(String id) {
        String code = "1";
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM floder WHERE id = '" + id + "';");
            db.execSQL("DROP TABLE " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("sqLiteDelete", String.valueOf(e));
            code = "0";
        } finally {
            db.endTransaction();
        }
        return code;
    }

    @JavascriptInterface
    public String pushCard(String id, String title, String text) {
        String code = "1";
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO " + id + " VALUES('" + title + "','" + text + "',0);");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("sqLiteInsert", String.valueOf(e));
            code = "0";
        } finally {
            db.endTransaction();
        }
        return code;
    }

    @JavascriptInterface
    public String deleteCard(String table, String title, String text) {
        String code = "1";
        db.beginTransaction();
        try {
            //Log.d("sqLiteIDelete","DELETE FROM " + table + " WHERE title='" + title + "' AND text = '" + text + "';");
            db.execSQL("DELETE FROM " + table + " WHERE title='" + title +"';");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("sqLiteIDelete", String.valueOf(e));
            code = "0";
        } finally {
            db.endTransaction();
        }
        return code;
    }

    @JavascriptInterface
    public void pullCards(String table) {
        Handler mainHandler = new Handler(Looper.getMainLooper());

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = db.query(table, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    String title = cursor.getString(0);
                    String text = cursor.getString(1);
                    String statu = cursor.getString(2);
                    webView.loadUrl("javascript:app.appendCardData('" + title + "','" + text + "','" + statu + "')");
                }
                cursor.close();
            }
        });
    }
}
