package com.example.calculadoradivises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AlertDialogLayout;
*/

enum Divises {EURO, BITCOIN, CARDANO, ETHEREUM, LITECOIN;}



public class MainActivity extends AppCompatActivity {

    private double valor=0;

    private TextView output;
    private TextView input;
    private Button boto0;
    private Button boto1;
    private Button boto2;
    private Button boto3;
    private Button boto4;
    private Button boto5;
    private Button boto6;
    private Button boto7;
    private Button boto8;
    private Button boto9;
    private Button botoComa;
    private Button botoCE;
    private Button botoFlecha;

    // moneda inicial

    private Button botoEuroFirst;
    private Button botoBitcoinFirst;
    private Button botoCardanoFirst;
    private Button botoEthereumFirst;
    private Button botoLitecoinFirst;

    // moneda a convertir

    private Button botoEuroSecond;
    private Button botoBitcoinSecond;
    private Button botoCardanoSecond;
    private Button botoEthereumSecond;
    private Button botoLitecoinSecond;


    private double valorinput = 1;
    private double valoroutput = 1;

    private double valorEuro=1;
    private double valorBitcoin = 0;
    private double valorCardano = 0;
    private double valorEthereum = 0;
    private double valorLitecoin = 0;

    private String monedaFirst ="EURO";

    double[][] conversiones = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};


    @Override

    protected void onCreate(Bundle savedInstanceState)    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();


    }

    private void initListeners() {
        boto0.setOnClickListener(view -> {clickNumero("0");});
        boto1.setOnClickListener(view -> {clickNumero("1");});
        boto2.setOnClickListener(view -> {clickNumero("2");});
        boto3.setOnClickListener(view -> {clickNumero("3");});
        boto4.setOnClickListener(view -> {clickNumero("4");});
        boto5.setOnClickListener(view -> {clickNumero("5");});
        boto6.setOnClickListener(view -> {clickNumero("6");});
        boto7.setOnClickListener(view -> {clickNumero("7");});
        boto8.setOnClickListener(view -> {clickNumero("8");});
        boto9.setOnClickListener(view -> {clickNumero("9");});

        botoComa.setOnClickListener(view -> {clickComa();});
        botoCE.setOnClickListener(view -> {clickClear();});
        botoFlecha.setOnClickListener(view -> {clickBorrar();});

        botoEuroFirst.setOnClickListener(view -> {cambiarcolorsinput(Divises.EURO); monedaFirst = "EURO"; clickDivisaInput(Divises.EURO); });
        botoBitcoinFirst.setOnClickListener(view -> {cambiarcolorsinput(Divises.BITCOIN); monedaFirst = "BITCOIN";clickDivisaInput(Divises.BITCOIN); });
        botoCardanoFirst.setOnClickListener(view -> {cambiarcolorsinput(Divises.CARDANO); monedaFirst = "CARDANO";clickDivisaInput(Divises.CARDANO); });
        botoEthereumFirst.setOnClickListener(view -> {cambiarcolorsinput(Divises.ETHEREUM); monedaFirst = "ETHEREUM";clickDivisaInput(Divises.ETHEREUM); });
        botoLitecoinFirst.setOnClickListener(view -> {cambiarcolorsinput(Divises.LITECOIN); monedaFirst = "LITECOIN";clickDivisaInput(Divises.LITECOIN); });

        botoEuroSecond.setOnClickListener(view -> {cambiarcolorsoutput(Divises.EURO); clickDivisaOutput(Divises.EURO); });
        botoBitcoinSecond.setOnClickListener(view -> {cambiarcolorsoutput(Divises.BITCOIN); clickDivisaOutput(Divises.BITCOIN); });
        botoCardanoSecond.setOnClickListener(view -> {cambiarcolorsoutput(Divises.CARDANO); clickDivisaOutput(Divises.CARDANO); });
        botoEthereumSecond.setOnClickListener(view -> {cambiarcolorsoutput(Divises.ETHEREUM); clickDivisaOutput(Divises.ETHEREUM); });
        botoLitecoinSecond.setOnClickListener(view -> {cambiarcolorsoutput(Divises.LITECOIN); clickDivisaOutput(Divises.LITECOIN); });
    }

    private void initViews() {
        output = findViewById(R.id.textView_output);
        input = findViewById(R.id.textView_input);

        boto0 = findViewById(R.id.button_0);
        boto1 = findViewById(R.id.button_1);
        boto2 = findViewById(R.id.button_2);
        boto3 = findViewById(R.id.button_3);
        boto4 = findViewById(R.id.button_4);
        boto5 = findViewById(R.id.button_5);
        boto6 = findViewById(R.id.button_6);
        boto7 = findViewById(R.id.button_7);
        boto8 = findViewById(R.id.button_8);
        boto9 = findViewById(R.id.button_9);
        botoComa = findViewById(R.id.button_coma);
        botoCE = findViewById(R.id.button_CE);
        botoFlecha = findViewById(R.id.button_flechita);

        botoEuroFirst = findViewById(R.id.button_euro1);
        botoBitcoinFirst = findViewById(R.id.button_bitcoin1);
        botoCardanoFirst = findViewById(R.id.button_cardano1);
        botoEthereumFirst = findViewById(R.id.button_ethereum1);
        botoLitecoinFirst = findViewById(R.id.button_litecoin1);

        botoEuroSecond = findViewById(R.id.button_euro2);
        botoBitcoinSecond = findViewById(R.id.button_bitcoin2);
        botoCardanoSecond = findViewById(R.id.button_cardano2);
        botoEthereumSecond = findViewById(R.id.button_ethereum2);
        botoLitecoinSecond = findViewById(R.id.button_litecoin2);


    }

    private void clickDivisaInput(Divises divisa){

        String moneda ="";

        switch (divisa){
            case EURO: moneda = "euro"; setvalormonedaInput(moneda, divisa); break;
            case BITCOIN: moneda = "bitcoin"; setvalormonedaInput(moneda, divisa); break;
            case CARDANO: moneda = "cardano"; setvalormonedaInput(moneda, divisa);  break;
            case ETHEREUM: moneda = "ethereum"; setvalormonedaInput(moneda, divisa);  break;
            case LITECOIN: moneda = "litecoin"; setvalormonedaInput(moneda, divisa);  break;
            default: break;
        }
    }
    private void clickDivisaOutput(Divises divisa){

        String moneda ="";

        switch (divisa){
            case EURO: valoroutput =1; break;
            case BITCOIN: moneda = "bitcoin";  setvalormonedaOutput(moneda, divisa);  break;
            case CARDANO: moneda = "cardano";  setvalormonedaOutput(moneda, divisa);  break;
            case ETHEREUM: moneda = "ethereum";  setvalormonedaOutput(moneda, divisa);  break;
            case LITECOIN: moneda = "litecoin"; setvalormonedaOutput(moneda, divisa);  break;
            default: break;
        }
    }


    private void setvalormonedaInput (String moneda, Divises divisa)    {
        double valormoneda;

        switch (divisa){
            case EURO: valormoneda =1; break;
            case BITCOIN: valormoneda =valorBitcoin; break;
            case CARDANO:  valormoneda =valorCardano; break;
            case ETHEREUM:  valormoneda =valorEthereum; break;
            case LITECOIN:  valormoneda =valorLitecoin; break;
            default: valormoneda = 0;break;
        }

        Log.d("valormoneda 254", "Primer pas de setvalormoneda. El valormoneda es " + valormoneda);


        // si el valor de la moneda es 0, executar aixo
        if(valormoneda == 0){

        // missatge de texte per posar el valor
        // "Introdueix el valor de 1 $moneda en euros. Si s'introdueix un valor no valid es posara un default a 1"

            AlertDialog ad;

            ad = new AlertDialog.Builder(this).create();
            ad.setTitle("Introdueix el valor de 1 " + moneda );
            ad.setMessage("Introdueix el valor de 1 " + moneda +" en euros. (IE: 1 bitcoin = 10 €) Si s'introdueix un valor no valid es posara un default a 1");




            // Ahora forzamos que aparezca el editText
            final EditText edtValor = new EditText(this);
            ad.setView(edtValor);

            ad.setButton(AlertDialog.BUTTON_POSITIVE,"Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {


                    //convertir el valor de input a double.
                    try {
                        valor =  Double.parseDouble(edtValor.getText().toString());
                        Toast.makeText(getApplicationContext(), "El valor de 1 " + moneda + " es " + valor + " euros.", Toast.LENGTH_LONG).show();
                        switch (divisa){
                            case EURO: valorEuro =1;  break;
                            case BITCOIN: valorBitcoin = valor;  break;
                            case CARDANO:  valorCardano = valor; break;
                            case ETHEREUM: valorEthereum = valor;  break;
                            case LITECOIN: valorLitecoin = valor;   break;
                            default: break;
                        }}

                    catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "Valor incorrecte trobat. El valor de " + moneda + " s'ha posat a 1.", Toast.LENGTH_LONG).show();
                   valor = 1;
                        switch (divisa){
                            case EURO: valoroutput =1;  break;
                            case BITCOIN: valorBitcoin = valor;  break;
                            case CARDANO:  valorCardano = valor; break;
                            case ETHEREUM: valorEthereum = valor;  break;
                            case LITECOIN: valorLitecoin = valor;   break;
                            default: break;
                        }

                    }


                }
            });

            ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // no fem res.
                }
            });
            ad.show();




    }

       else {
        //toast dient que el valor de $moneda es $valor (El valor de 1 $moneda es $valor euros)
        switch (divisa){
            case EURO:  break;
            case BITCOIN:  Toast.makeText(getApplicationContext(), "El valor de 1 bitcoin es " + valorBitcoin + " euros(s).", Toast.LENGTH_LONG).show();  break;
            case CARDANO:  Toast.makeText(getApplicationContext(), "El valor de 1 cardano es " + valorCardano + " euros(s).", Toast.LENGTH_LONG).show();  break;
            case ETHEREUM: Toast.makeText(getApplicationContext(), "El valor de 1 ethereum es " + valorEthereum + " euros(s).", Toast.LENGTH_LONG).show();  break;
            case LITECOIN:  Toast.makeText(getApplicationContext(), "El valor de 1 litecoin es " + valorLitecoin + " euros(s).", Toast.LENGTH_LONG).show();  break;
            default: break;
        }}

    }


    private void setvalormonedaOutput (String moneda, Divises divisa)    {
        double valormoneda;

        switch (divisa){
            case EURO: valormoneda =1; break;
            case BITCOIN: valormoneda =valorBitcoin; break;
            case CARDANO:  valormoneda =valorCardano; break;
            case ETHEREUM:  valormoneda =valorEthereum; break;
            case LITECOIN:  valormoneda =valorLitecoin; break;
            default: valormoneda = 0;break;
        }
        Log.d("valormoneda 254", "Primer pas de setvalormoneda. El valormoneda es " + valormoneda);


        // si el valor de la moneda es 0, executar aixo
        if(valormoneda == 0){

            // missatge de texte per posar el valor
            // "Introdueix el valor de 1 $moneda en euros. Si s'introdueix un valor no valid es posara un default a 1"

            AlertDialog ad;

            ad = new AlertDialog.Builder(this).create();
            ad.setTitle("Introdueix el valor de 1 " + moneda );
            ad.setMessage("Introdueix el valor de 1 " + moneda +" en euros. (IE: 1 bitcoin = 10 €) Si s'introdueix un valor no valid es posara un default a 1");




            // Ahora forzamos que aparezca el editText
            final EditText edtValor = new EditText(this);
            ad.setView(edtValor);

            ad.setButton(AlertDialog.BUTTON_POSITIVE,"Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {


                    //convertir el valor de input a double.
                    try {
                        valor =  Double.parseDouble(edtValor.getText().toString());
                        Toast.makeText(getApplicationContext(), "El valor de 1 " + moneda + " es " + valor + " euros.", Toast.LENGTH_LONG).show();
                        switch (divisa){
                            case EURO: valorEuro =1; calcularValor(Divises.EURO); break;
                            case BITCOIN: valorBitcoin = valor;calcularValor(Divises.BITCOIN);  break;
                            case CARDANO:  valorCardano = valor; calcularValor(Divises.CARDANO);break;
                            case ETHEREUM: valorEthereum = valor; calcularValor(Divises.ETHEREUM); break;
                            case LITECOIN: valorLitecoin = valor;  calcularValor(Divises.LITECOIN); break;
                            default: break;
                        }}

                    catch(Exception e) {
                        Toast.makeText(getApplicationContext(), "Valor incorrecte trobat. El valor de " + moneda + " s'ha posat a 1.", Toast.LENGTH_LONG).show();
                        valor = 1;
                        switch (divisa){
                            case EURO: valoroutput =1; calcularValor(Divises.EURO); break;
                            case BITCOIN: valorBitcoin = valor;calcularValor(Divises.BITCOIN);  break;
                            case CARDANO:  valorCardano = valor; calcularValor(Divises.CARDANO);break;
                            case ETHEREUM: valorEthereum = valor; calcularValor(Divises.ETHEREUM); break;
                            case LITECOIN: valorLitecoin = valor;  calcularValor(Divises.LITECOIN); break;
                            default: break;
                        }

                    }


                }
            });

            ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // no fem res.
                }
            });
            ad.show();




        }

        else {
            //toast dient que el valor de $moneda es $valor (El valor de 1 $moneda es $valor euros)
            switch (divisa){
                case EURO: calcularValor(Divises.EURO); break;
                case BITCOIN: calcularValor(Divises.BITCOIN); Toast.makeText(getApplicationContext(), "El valor de 1 bitcoin es " + valorBitcoin + " euros(s).", Toast.LENGTH_LONG).show();  break;
                case CARDANO: calcularValor(Divises.CARDANO); Toast.makeText(getApplicationContext(), "El valor de 1 cardano es " + valorCardano + " euros(s).", Toast.LENGTH_LONG).show();  break;
                case ETHEREUM: calcularValor(Divises.ETHEREUM); Toast.makeText(getApplicationContext(), "El valor de 1 ethereum es " + valorEthereum + " euros(s).", Toast.LENGTH_LONG).show();  break;
                case LITECOIN: calcularValor(Divises.LITECOIN); Toast.makeText(getApplicationContext(), "El valor de 1 litecoin es " + valorLitecoin + " euros(s).", Toast.LENGTH_LONG).show();  break;
                default: break;
            }}

    }



    private void calcularValor(Divises divisa){

        String conversio = input.getText().toString();
        double valoractual = conversioDouble(conversio);
        //convertir inicial a euro

        double valorEnEuros=0;
        double valorfinal=0;

        switch (monedaFirst) {
            case "EURO": valorEnEuros = valoractual; break;
            case "BITCOIN": valorEnEuros = valoractual/valorBitcoin; break;
            case "CARDANO": valorEnEuros = valoractual/valorCardano; break;
            case "ETHEREUM": valorEnEuros = valoractual/valorEthereum; break;
            case "LITECOIN": valorEnEuros = valoractual/valorLitecoin; break;
            default: break;
        }

        //convertir el euro a la final


        switch (divisa){
            case EURO: valorfinal= valorEnEuros*valorEuro; break;
            case BITCOIN:   valorfinal= valorEnEuros*valorBitcoin; break;
            case CARDANO:  valorfinal= valorEnEuros*valorCardano; break;
            case ETHEREUM:   valorfinal= valorEnEuros*valorEthereum; break;
            case LITECOIN:  valorfinal= valorEnEuros*valorLitecoin; break;
            default: break;
        }

        output.setText(String.valueOf(valorfinal));

        




    }


    private void clickBorrar() {
        // si es zero o
        if(input.getText().toString().length()==1){clickClear();}
        else {

            input.setText(input.getText().toString().substring(0, input.getText().toString().length()-1));

        }


    }
    private void clickClear() {input.setText("0");}
    private void clickComa() {
        if (!hasComa(input.getText().toString())){
            String valorAntic = input.getText().toString();
            input.setText(valorAntic.concat(","));}


    }
    private void clickNumero(String numero){

        if (input.getText().toString().length()<12){
            //si posa zero en zero, no fa res
            if (input.getText().toString().equals("0")&&numero.equals("0")){return;}

            //afegir numeros normals

            if(hasComa(input.getText().toString()))
            {
                //si te dos decimals despres de la coma
                int numcaracters = input.getText().toString().length();
                int posiciocoma = input.getText().toString().lastIndexOf(",");
                if (input.getText().toString().substring(posiciocoma,numcaracters).length()<9){String valorAntic = input.getText().toString(); input.setText(valorAntic.concat(numero)); Log.d("settext linea 137", "9");}
            }

            else{
                if(input.getText().toString().equals("0")){input.setText(numero); Log.d("settext linea 130","1");}

                else {String valorAntic = input.getText().toString(); input.setText(valorAntic.concat(numero)); Log.d("settext linea 140", "3");}

            }
        }




    }

    private boolean hasComa(String input){return input.contains(",");}
    private double conversioDouble (String conversio){
        conversio = conversio.replace (',','.');
        return Double.parseDouble(conversio);
    };


    private void cambiarcolorsinput(Divises divisa)  {
        switch (divisa){
            case EURO:
                botoEuroFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoBitcoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case BITCOIN:
                botoEuroFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoCardanoFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case CARDANO:
                botoEuroFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoEthereumFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case ETHEREUM:
                botoEuroFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoLitecoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case LITECOIN:
                botoEuroFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinFirst.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                break;
            default: break;
        }

    }
    private void cambiarcolorsoutput(Divises divisa)   {
        switch (divisa){
            case EURO:
                botoEuroSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoBitcoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case BITCOIN:
                botoEuroSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoCardanoSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case CARDANO:
                botoEuroSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoEthereumSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case ETHEREUM:
                botoEuroSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                botoLitecoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                break;

            case LITECOIN:
                botoEuroSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoBitcoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoCardanoSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoEthereumSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                botoLitecoinSecond.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
                break;
            default: break;
        }

    }

}






