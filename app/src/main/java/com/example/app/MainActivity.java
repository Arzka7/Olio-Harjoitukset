package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    Context context = null;
    private EditText textBox;
    private EditText textBox_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("KANSION SIJAINTI " + context.getFilesDir());
        textBox = (EditText) findViewById(R.id.editText);
        textBox_2 = (EditText) findViewById(R.id.editText2);

    }

    public void readFile(View v) {
        try {
            InputStream ins = context.openFileInput(textBox_2.getText().toString());
            BufferedReader br_2 = new BufferedReader(new InputStreamReader(ins));
            String s;

            textBox.setText("");
            while ((s = br_2.readLine()) != null) {
                textBox.append(s);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(textBox_2.getText().toString(), Context.MODE_PRIVATE));
            String s = textBox.getText().toString();
            ows.write(s);
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}
