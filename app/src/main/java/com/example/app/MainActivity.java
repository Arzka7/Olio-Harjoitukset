package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.app.Settings.getInstance;


public class MainActivity extends Activity {

    private TextView text, displayText;
    private EditText editText;
    private Settings settings;
    private ConstraintLayout.LayoutParams layoutparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);
        layoutparams = (ConstraintLayout.LayoutParams) text.getLayoutParams();

        layoutparams.width = 300;
        layoutparams.height = 200;
        text.setLayoutParams(layoutparams);

        text.setMaxLines(4);
        text.setMinLines(0);
        text.setLines(2);

        editText = findViewById(R.id.editText);
        editText.setEnabled(false);

        displayText = findViewById(R.id.displayView);

        settings = Settings.getInstance();
        settings.setFontSize(30);
        settings.setSwitch(false);
        settings.setHeight(200);
        settings.setLines(2);
        settings.setWidth(300);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.settings) {
            Intent intent = new Intent(this, Settings_Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveText(View v) {
        text.setText(editText.getText().toString());
        editText.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();

        text.setTextSize(settings.getFontSize());


        layoutparams.width = settings.getWidth();
        layoutparams.height = settings.getHeight();
        text.setLayoutParams(layoutparams);




        text.setMinLines(0);
        text.setMaxLines(settings.getLines() + 10);
        text.setLines(settings.getLines());

        editText.setEnabled(settings.isSwitchOn());

        displayText.setText(settings.getDisplayString());
    }
}
