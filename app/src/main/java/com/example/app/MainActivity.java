package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Context context = null;
    private BottleDispenser Pulloautomaatti = BottleDispenser.getInstance();
    private TextView text;
    private SeekBar seek;
    private Spinner spinner;
    private String identifier = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        System.out.println("KANSION SIJAINTI " + context.getFilesDir());

        text = (TextView) findViewById(R.id.editText);
        seek = (SeekBar) findViewById(R.id.seekBar);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.products, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        seek_Bar();
    }


    public void seek_Bar() {
        double amount = ((double) seek.getProgress()) * 0.1;
        text.setText(String.format("%.2f €", amount));
        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    private double amount;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        amount = ((double) progress) * 0.1;
                        if (seek.getProgress() != 0) {
                            text.setText(String.format("%.2f €", amount));
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    public void addMoney(View v) {
        double temp;
        temp = ((double) seek.getProgress()) * 0.1;
        text.setText(Pulloautomaatti.addMoney(temp));
        seek.setProgress(0);
    }

    public void returnMoney(View v) {
        text.setText(Pulloautomaatti.returnMoney());
    }

    public void printReceipt(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));
            ows.write("KUITTI\n" + identifier + "\n");
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KUITTI TULOSTETTU");
        }
    }

    public void buyBottle(View v) {
        int pos = spinner.getSelectedItemPosition();

        if (pos == 0) {
            text.setText("Valitse ensin tuote!");
        } else if (pos == 1) {
            String name = "Pepsi Max";
            String size = "0.5";
            identifier = name + " " + size + "L 1.80€";
            int index = Pulloautomaatti.searchBottle(name, size);
            text.setText(Pulloautomaatti.buyBottle(index));

        } else if (pos == 2) {
            String name = "Pepsi Max";
            String size = "1.5";
            identifier = name + " " + size + "L 2.20€";
            int index = Pulloautomaatti.searchBottle(name, size);
            text.setText(Pulloautomaatti.buyBottle(index));

        } else if (pos == 3) {
            String name = "Coca-Cola Zero";
            String size = "0.5";
            identifier = name + " " + size + "L 2.00€";
            int index = Pulloautomaatti.searchBottle(name, size);
            text.setText(Pulloautomaatti.buyBottle(index));

        } else if (pos == 4) {
            String name = "Coca-Cola Zero";
            String size = "1.5";
            identifier = name + " " + size + "L 2.50€";
            int index = Pulloautomaatti.searchBottle(name, size);
            text.setText(Pulloautomaatti.buyBottle(index));

        } else if (pos == 5) {
            String name = "Fanta Zero";
            String size = "0.5";
            identifier = name + " " + size + "L 1.95€";
            int index = Pulloautomaatti.searchBottle(name, size);
            text.setText(Pulloautomaatti.buyBottle(index));

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

}