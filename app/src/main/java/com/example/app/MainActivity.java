package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends Activity {

    private TheaterList theaters = new TheaterList();
    private MoviesXMLReader moviesReader = new MoviesXMLReader();
    private Spinner spinner;
    private TextView textfield;
    private TextView multitext;
    private TextView textTimeStart;
    private TextView textTimeEnd;
    private TextView textMovieName;
    private String[] IDs = {"1014", "1015", "1016", "1017", "1041", "1018", "1019", "1021", "1022"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        theaters = moviesReader.readTheatreAreasXML(theaters);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Theater> adapter = new ArrayAdapter<Theater>(this, android.R.layout.simple_spinner_item, theaters.getTheaterList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textfield = findViewById(R.id.editText);
        multitext = findViewById(R.id.editText2);
        textTimeStart = findViewById(R.id.editText3);
        textTimeEnd = findViewById(R.id.editText4);
        textMovieName = findViewById(R.id.editText5);
    }

    public void getMovies(View v) {
        int pos = spinner.getSelectedItemPosition();
        Theater theater;
        String url, data="", id, toast = "";
        String date = textfield.getText().toString();
        String startTime = textTimeStart.getText().toString();
        String endTime = textTimeEnd.getText().toString();
        String movieName = textMovieName.getText().toString();
        boolean flag = false;

        multitext.setText("");

        if(date.equals("")) {
            date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        } else if (!Pattern.compile("\\d\\d.\\d\\d.\\d\\d\\d\\d").matcher(date).matches()) {
            toast = toast + "Päivämäärä ei kelpaa! ";
            flag = true;
        }

        if(startTime.equals("")) {
            startTime = "00:00";
        } else if (!Pattern.compile("\\d\\d:\\d\\d").matcher(startTime).matches()) {
            toast = toast + "Alkaen aika ei kelpaa! ";
            flag = true;
        }

        if(endTime.equals("")) {
            endTime = "24:00";
        } else if (!Pattern.compile("\\d\\d:\\d\\d").matcher(endTime).matches()) {
            toast = toast + "Asti aika ei kelpaa!";
            flag = true;

        }

        if (flag) {
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
            return;
        }

        if (movieName.equals("")) {
            if (pos == 0) {
                Toast.makeText(getApplicationContext(), "Valitse ensin alue/teatteri!", Toast.LENGTH_LONG).show();
                return;
            } else {
                theater = theaters.getTheaterByIndex(pos);
                id = theater.getId();
            }

            url = "http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;
            data = moviesReader.readMoviesXML(url, startTime, endTime, movieName);
        } else {
            if (pos == 0) {
                for (String i:IDs) {
                    id = i;
                    url = "http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;
                    data = data + moviesReader.readMoviesXML(url, startTime, endTime, movieName);
                }
            } else {
                theater = theaters.getTheaterByIndex(pos);
                id = theater.getId();
                url = "http://www.finnkino.fi/xml/Schedule/?area=" + id + "&dt=" + date;
                data = moviesReader.readMoviesXML(url, startTime, endTime, movieName);
            }
        }
        multitext.setText(date + "\n" + "\n" + data);
    }
}
