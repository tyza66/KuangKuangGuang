package com.tyza66.kuangkuangguang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //加载webview
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.wv_webview);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setBackgroundColor(0);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/home.html");
        //获取数据库
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this,"user.db",null,1);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        //js与原生Android交互
        webView.setWebViewClient(new MyWebViewClient(this,webView,db));
        webView.addJavascriptInterface(new LocalJavaForJs(this,webView,db),"tyza66");
    }
}