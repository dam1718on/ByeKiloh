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
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, cad.length() - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast toast= Toast.makeText(context,
                centeredText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
        toast.show();
    }

}