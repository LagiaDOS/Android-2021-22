package com.icpicking.intracloud.a02bmissatgeria;


import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.design.widget.Snackbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn;

        // Toast corto
        btn = findViewById(R.id.btnToastCorto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Message", Toast.LENGTH_SHORT).show();

            }
        });

        // Toast largo
        btn = findViewById(R.id.btnToastLargo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "text", Toast.LENGTH_LONG).show();

            }
        });

        // Snackbar bottom API 21
        btn = findViewById(R.id.btnSnackBar01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarBasico();
            }
        });

        // Snackbar TOP API 21
        btn = findViewById(R.id.btnSnackBar02);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarTop();
            }
        });

        // Snackbar Personalizado API 21
        btn = findViewById(R.id.btnSnackBar03);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarPersonalizado();
            }
        });

        // Dialogo básico
        btn = findViewById(R.id.btnDialogo01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialogBasico();
            }
        });

        // Dialogo solicitando dato
        btn = findViewById(R.id.btnDialogo02);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConEditText();
            }
        });

    }

    private void snackBarPersonalizado() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snack = Snackbar.make(parentLayout, "Message", Snackbar.LENGTH_LONG);

        // Forzamos el TOP
        Snackbar sb = Snackbar.make(parentLayout, "Message", Snackbar.LENGTH_LONG);

        // Cambiamos el color de fondo del snackbar.
        View sbv = sb.getView();
        sbv.setBackgroundColor(Color.parseColor("#f44141"));


        // Cambiamos el tamaño de texto.
        TextView snackbarActionTextView = (TextView) sbv.findViewById( android.support.design.R.id.snackbar_text);
        snackbarActionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        sb.show();
    }

    private void snackBarTop() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snack = Snackbar.make(parentLayout, "Message", Snackbar.LENGTH_LONG);

        // Forzamos el TOP
        View viewSnack = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams) viewSnack.getLayoutParams();
        params.gravity = Gravity.TOP;
        viewSnack.setLayoutParams(params);

        snack.show();
    }

    private void snackBarBasico() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar sb = Snackbar.make(parentLayout, "Message", Snackbar.LENGTH_SHORT);
        sb.show();
    }

    private void dialogBasico() {
        AlertDialog ad;

        ad = new AlertDialog.Builder(this).create();
        ad.setTitle("Título de la caja");
        ad.setMessage("Dato a solicitar");

        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Se ha apretado el botón ACEPTAR", Toast.LENGTH_LONG).show();
            }
        });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // no fem res.
            }
        });
        ad.show();
    }

        // el Show es asíncrono.

    private void dialogConEditText() {
        AlertDialog ad;

        ad = new AlertDialog.Builder(this).create();
        ad.setTitle("Título de la caja");
        ad.setMessage("Dato a solicitar");

        // Ahora forzamos que aparezca el editText
        final EditText edtValor = new EditText(this);
        ad.setView(edtValor);

        ad.setButton(AlertDialog.BUTTON_POSITIVE,"Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Se ha apretado el botón ACEPTAR, el valor escrito es " + edtValor.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // no fem res.
            }
        });
        ad.show();

        // el Show es asíncrono.

    }
}
