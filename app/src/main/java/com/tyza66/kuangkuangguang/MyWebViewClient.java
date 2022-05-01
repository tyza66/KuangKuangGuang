package com.tyza66.kuangkuangguang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    Context context;
    SQLiteDatabase db;
    WebView webView;
    public MyWebViewClient(Context context, WebView webView, SQLiteDatabase db){
        this.context =  context;
        this.db = db;
        this.webView = webView;
    }

    @Override
    public void onPageFinished(WebView webView, String url) {
        webView.addJavascriptInterface(new LocalJavaForJs(context,webView,db),"tyza66");
    }

}

