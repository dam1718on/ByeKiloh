package com.example.byekiloh;

import android.content.Context;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.widget.Toast;

public class Mensaje {

    public Mensaje() {    }

    public Mensaje(Context context, String cad) {
        Spannable centeredText = new SpannableString(cad);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0,
            cad.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast toast= Toast.makeText(context, centeredText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
        toast.show();
    }

//Otra Versión para Toast, en desuso
/*TextView textview = new TextView(getApplicationContext());
textview.setText("Revise los datos introducidos.\nTodos los campos son obligatorios");
textview.setBackgroundColor(Color.GRAY);
textview.setTextColor(Color.BLUE);
textview.setPadding(10,10,10,10);
Toast toast = new Toast(getApplicationContext());
toast.setView(textview);
toast.setDuration(Toast.LENGTH_LONG);
toast.setGravity(Gravity.BOTTOM, 0, 0);
toast.show();*/

}