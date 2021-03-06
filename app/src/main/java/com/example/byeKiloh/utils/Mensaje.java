package com.example.byeKiloh.utils;

import android.content.Context;

import android.text.Layout;
import android.text.style.AlignmentSpan;
import android.text.Spannable;
import android.text.SpannableString;

import android.view.Gravity;

import android.widget.Toast;

public class Mensaje {

    public Mensaje() {    }

    public Mensaje(Context context, String cad) {

        Spannable centeredText = new SpannableString(cad);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0,
        cad.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast toast= Toast.makeText(context, centeredText, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 340);
        toast.show();

    }

}