package com.example.app;

public class Settings {
    private int fontSize;
    private boolean switchOn;
    private int width;
    private int height;
    private int lines;
    private String displayString;
    private String locale;

    private static final Settings instance = new Settings();

    private Settings(){}

    public static Settings getInstance() {
        return instance;
    }

    public String getLocale() {
        return locale;
    }

    public String getDisplayString() {
        return displayString;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getHeight() {
        return height;
    }

    public int getLines() {
        return lines;
    }

    public int getWidth() {
        return width;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setLocale(String lc) {
        this.locale = lc;
    }

    public void setDisplayString(String dS) {
        this.displayString = dS;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setSwitch(boolean switchOn) {
        this.switchOn = switchOn;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}


