package com.tyza66.kuangkuangguang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class LocalJavaForJs {
    Context context;
    SQLiteDatabase db;
    WebView webView;
    public LocalJavaForJs(Context context, WebView webView, SQLiteDatabase db){
       this.context =  context;
       this.db = db;
       this.webView = webView;
       pullFloder();
    }

    @JavascriptInterface
    public void test(){
        Toast.makeText(context,"test",Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void pullFloder(){
        Cursor cursor = db.query("floder",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            webView.loadUrl("javascript:app.appendFolder('"+ id +"','"+ title +"')");
        }
        cursor.close();
    }
}
