package com.example.byeKiloh.utils;

import android.widget.EditText;

public class VaciarEditText {

    public VaciarEditText() {    }

    public VaciarEditText(EditText x) { x.setText(""); }

    public VaciarEditText(EditText x, EditText y) { x.setText(""); y.setText(""); }

    public VaciarEditText(EditText x, EditText y, EditText z) {
        x.setText("");
        y.setText("");
        z.setText("");
    }

    public VaciarEditText(EditText x, EditText y, EditText z, EditText a) {
        x.setText("");
        y.setText("");
        z.setText("");
        a.setText("");
    }

}