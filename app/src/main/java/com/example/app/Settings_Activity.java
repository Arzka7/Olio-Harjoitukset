package com.example.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Settings_Activity extends Activity {

    private SeekBar seekbar_size, seekbar_width, seekbar_height, seekbar_lines;
    private Switch switch_1;
    private Settings settings;
    private EditText displayText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        seekbar_size = (SeekBar) findViewById(R.id.seekBar1);
        seekbar_width = (SeekBar) findViewById(R.id.seekBar2);
        seekbar_height = (SeekBar) findViewById(R.id.seekBar3);
        seekbar_lines = (SeekBar) findViewById(R.id.seekBar4);

        spinner = findViewById(R.id.spinner);

        displayText = findViewById(R.id.displayText);

        switch_1 = (Switch) findViewById(R.id.switch1);

        settings = Settings.getInstance();

        switch_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
        });

        seekbar_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar_size, int progress, boolean fromUser) {
                int temp = progress + 1;
                Toast.makeText(getApplicationContext(), "font size: " + temp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekbar_size) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekbar_size) { }
        });

        seekbar_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar_width, int progress, boolean fromUser) {
                int temp = progress + 5;
                Toast.makeText(getApplicationContext(),"width: "+temp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekbar_width){}
            @Override
            public void onStopTrackingTouch(SeekBar seekbar_width) {}
        });

        seekbar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar_height, int progress, boolean fromUser) {
                int temp = progress + 5;
                Toast.makeText(getApplicationContext(),"heigth: "+temp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekbar_height){}
            @Override
            public void onStopTrackingTouch(SeekBar seekbar_height){}
        });

        seekbar_lines.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar_lines, int progress, boolean fromUser) {
                int temp = progress + 1;
                Toast.makeText(getApplicationContext(),"lines: "+temp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekbar_lines){}
            @Override
            public void onStopTrackingTouch(SeekBar seekbar_lines){
                //MainActivity.text.setMaxLines(seekbar_lines.getProgress() + 10);
                //MainActivity.text.setMinLines(0);
                //MainActivity.text.setLines(seekbar_lines.getProgress() + 1);
            }
        });

        List<String> languages = new ArrayList<String>();
        languages.add("Suomi");
        languages.add("Englanti");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        switch_1.setChecked(settings.isSwitchOn());
        seekbar_size.setProgress(settings.getFontSize() - 1);
        seekbar_width.setProgress(settings.getWidth() - 5);
        seekbar_height.setProgress(settings.getHeight() - 5);
        seekbar_lines.setProgress(settings.getLines() - 1);

        displayText.setText(settings.getDisplayString());
    }

    public void saveSettings(View v) {
        settings.setSwitch(switch_1.isChecked());
        settings.setFontSize(seekbar_size.getProgress() + 1);
        settings.setWidth(seekbar_width.getProgress() + 5);
        settings.setHeight(seekbar_height.getProgress() + 5);
        settings.setLines(seekbar_lines.getProgress() + 1);
        settings.setDisplayString(displayText.getText().toString());

        int i = spinner.getSelectedItemPosition();

        if (i == 0) {
            //LocaleHelper.setLocale(getBaseContext(),"fi");
            System.out.println("SUOMI!");
            Locale locale = new Locale("fi");
            //Locale.setDefault(locale);

            Configuration configuration = new Configuration();//getBaseContext().getResources().getConfiguration();
            configuration.setLocale(locale);
            getResources().updateConfiguration(configuration, null);
            //recreate();
        } else if (i == 1) {
            System.out.println("SUOMI!");
            Locale locale = new Locale("en");
            //Locale.setDefault(locale);

            Configuration configuration = new Configuration();//getBaseContext().getResources().getConfiguration();
            configuration.setLocale(locale);
            getResources().updateConfiguration(configuration, null);
        }
    }
}