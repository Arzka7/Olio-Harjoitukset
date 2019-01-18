package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends Activity {

    private WebView web;
    private TextView text;
    private ArrayList<String> url = new ArrayList<String>();
    private int index=0;
    private static final int MIN=0;
    private static final int MAX=9;
    private boolean flag  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        text = findViewById(R.id.editText);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);

    }

    public void getURL(View v) {
        System.out.println("getURL 1" + index);
        String entry = text.getText().toString();
        String temp = "http://";
        int size=0;

        //if (flag) {
        //    index = url.size();
        //}

        if (flag) {
            System.out.println("!!!!!!!!!!!!!!!!");
            index += 1;
            size = url.size();
            if (size-1 > index) {
                for (int i = 0; i<size-index; i++) {
                    url.remove(index);
                    System.out.println(i + " ### " + index);
                }
            }
        }

        if (index == MAX+1) {
            url.remove(0);
            index -= 1;
        }

        if (entry.startsWith("http://")) {
            url.add(index, entry);
        } else if (entry.equals("index.html")) {
            url.add(index,"file:///android_asset/index.html");
        } else {
            url.add(index,temp + entry);
        }
        System.out.println("getURL 2" + index);
        searchURL();

        flag = true;
        System.out.println("getURL 3" + index);
    }

    public void searchURL() {
        web.loadUrl(url.get(index));
        System.out.println("SearchURL" + index);
        System.out.println(url.get(index));
    }

    public void searchURL(View v) {
        web.loadUrl(url.get(index));
        System.out.println("SearchURL" + index);
        System.out.println(url.get(index));
    }

    public void executeJavaScriptShoutOut(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void executeJavaScriptInitialize(View v) {
        web.evaluateJavascript("javascript:initialize()", null);
    }

    public void previous(View v) {
        if (index == 0) {
            return;
        }

        //if (index != MIN) {
        //    index -= 1;
        //}
        index -= 1;
        System.out.println("Previous" + index);
        searchURL();
        text.setText(url.get(index));
    }

    public void next(View v) {
        if (url.size() == 0) {
            return;
        }

        if (index != url.size()-1 && index != MAX) {
            index += 1;
        }
        System.out.println("Next" + index);
        searchURL();
        text.setText(url.get(index));
    }
}
