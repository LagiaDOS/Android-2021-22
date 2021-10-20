package com.example.calculadoradivises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

enum Divises {DOLAR, LLIURA, YEN, YUAN;}

public class MainActivity extends AppCompatActivity {

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
    private Button botoIgual;
    private Button botoComa;
    private Button botoCE;
    private Button botoFlecha;

    private Button euroFirst;
    private Button bitcoinFirst;
    private Button ethereumFirst;
    private Button cardanoFirst;
    private Button litecoinFirst;

    private Button euroSecond;
    private Button bitcoinSecond;
    private Button ethereumSecond;
    private Button cardanoSecond;
    private Button litecoinSecond;




    private double conversioActual = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        //botons de moneda inicial

        euroFirst.setOnClickListener(view -> {});
        bitcoinFirst.setOnClickListener(view -> {});
        ethereumFirst.setOnClickListener(view -> {});
        cardanoFirst.setOnClickListener(view -> {});
        litecoinFirst.setOnClickListener(view -> {});

        //botons de a que convertir


        euroSecond.setOnClickListener(view -> {});
        bitcoinSecond.setOnClickListener(view -> {});
        ethereumSecond.setOnClickListener(view -> {});
        cardanoSecond.setOnClickListener(view -> {});
        litecoinSecond.setOnClickListener(view -> {});


      //  botoLliura.setOnClickListener(view -> {clickDivisa(Divises.LLIURA);});
      //  botoDolar.setOnClickListener(view -> {clickDivisa(Divises.DOLAR);});
      //  botoYen.setOnClickListener(view -> {clickDivisa(Divises.YEN);});
      //  botoYuan.setOnClickListener(view -> {clickDivisa(Divises.YUAN);});



    }

    private void clickBorrar() {
        // si es zero o
        if(input.getText().toString().length()==1){clickClear();}
        else {

            input.setText(input.getText().toString().substring(0, input.getText().toString().length()-1));

        }


    }

    private void clickClear() {input.setText("0"); output.setText("0");}

    private void clickComa() {
        if (!hasComa(input.getText().toString())){
            String valorAntic = input.getText().toString();
            input.setText(valorAntic.concat(","));}
    }

    private boolean hasComa(String input){return input.contains(",");}

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

        euroFirst = findViewById(R.id.button_euros1);
        bitcoinFirst = findViewById(R.id.button_bitcoin1);
        ethereumFirst = findViewById(R.id.button_ethereum1);
        cardanoFirst = findViewById(R.id.button_cardano1);
        litecoinFirst = findViewById(R.id.button_litecoin1);

        euroSecond = findViewById(R.id.button_euros2);
        bitcoinSecond = findViewById(R.id.button_bitcoin2);
        ethereumSecond = findViewById(R.id.button_ethereum2);
        cardanoSecond = findViewById(R.id.button_cardano2);;
        litecoinSecond = findViewById(R.id.button_litecoin2);





       // botoLliura = findViewById(R.id.button_euros2);
       // botoDolar = findViewById(R.id.button_bitcoin2);
       // botoYen = findViewById(R.id.button_ethereum2);
       // botoYuan = findViewById(R.id.button_cardano2);
    }

    private void clickNumero(String numero){

    if (input.getText().toString().length()<12){
        //si posa zero en zero, no fa res
        if (input.getText().toString().equals("0")&&numero.equals("0")){return;}

        //afegir numeros normals

        if(hasComa(input.getText().toString()))
        {
         //si te 8 decimals despres de la coma
            int numcaracters = input.getText().toString().length();
            int posiciocoma = input.getText().toString().lastIndexOf(",");
            if (input.getText().toString().substring(posiciocoma,numcaracters).length()<9){String valorAntic = input.getText().toString(); input.setText(valorAntic.concat(numero)); Log.d("settext linea 136", "9");}
        }

        else{
            if(input.getText().toString().equals("0")){input.setText(numero); Log.d("settext linea 130","1");}
            else {String valorAntic = input.getText().toString(); input.setText(valorAntic.concat(numero)); Log.d("settext linea 141", "9");}

        }
    }




    }

    private void clickDivisa(Divises divisa){

        switch (divisa){
            case DOLAR: conversioActual = 1.184100; break;
            case LLIURA: conversioActual = 0.859100; break;
            case YEN: conversioActual = 130.619995; break;
            case YUAN: conversioActual = 7.657500; break;
            default: break;
        }

    }

    private void calcular_valor(){

        String conversio = input.getText().toString();

        double valoractual = conversioDouble(conversio);
        double valorfinal = valoractual*conversioActual;

        output.setText(String.valueOf(valorfinal));

    }

    private double conversioDouble (String conversio){
        conversio = conversio.replace (',','.');
        return Double.parseDouble(conversio);
    };

}






