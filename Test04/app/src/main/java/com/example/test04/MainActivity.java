package com.example.test04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // variables
    private EditText et1, et2;
    private TextView tv3, tvret;
    private RadioButton rbADD, rbSUB, rbMULT, rbDIV;

    private CheckBox cb;
    private Spinner sp;

    private String[] msgs = {"Google", "Youtube", "Gmail", "Google Drive", "GitLab", "Github",
            "Bitbucket"};
    private String[] msgs_num = {"www.google.com", "www.youtube.com", "mail.google.com",
            "drive.google.com", "gitlab.com", "github.com", "bitbucket.org"};
    private ListView lv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data from screen
        et1 = findViewById(R.id.NumberA);
        et2 = findViewById(R.id.NumberB);
        tv3 = findViewById(R.id.NumberC);

        // radio buttons
        rbADD = findViewById(R.id.RadioButtonADD);
        rbSUB = findViewById(R.id.RadioButtonSUB);
        rbMULT = findViewById(R.id.RadioButtonMULT);
        rbDIV = findViewById(R.id.RadioButtonDIV);

        // checkbox
        cb = findViewById(R.id.checkBox);

        // spinner
        sp = findViewById(R.id.spinner);
        String[] opciones = {"operacion", "suma", "resta", "multiplicacion", "division"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        sp.setAdapter(adapter);

        // listview
        lv = findViewById(R.id.ListView);
        ArrayAdapter<String> adapterlv = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, msgs);
        lv.setAdapter(adapterlv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                webpage(i);
//                tv3.setText("Numero de " + lv.getItemAtPosition(i) + " es " + msgs_num[i]);
            }
        });

        // notification
        Toast notificacion = Toast.makeText(this, "Abriste la app", Toast.LENGTH_LONG);
        notificacion.show();
    }



    // launch web page
    public void webpage(int i) {
        // to launch a new activity
        Intent j = new Intent(this, web.class);
        j.putExtra("direccion", msgs_num[i]);
        startActivity(j);
    }




    // operation
    public void calcule(View view) {
        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        float opA, opB, res;

        // notification
        Toast notificacion = Toast.makeText(this, "Apretaste el boton", Toast.LENGTH_LONG);
        notificacion.show();


        // check for operators
        if (st1.isEmpty()) {
            tv3.setText("Ingrese A");
            return;
        } else
            opA = Float.parseFloat(st1);

        if (st2.isEmpty()) {
            tv3.setText("Ingrese B");
            return;
        } else
            opB = Float.parseFloat(st2);


        // get spinner option
        int sel = sp.getSelectedItemPosition();


        // check for operation
        if (rbADD.isChecked() || (sel == 1))
            res = opA + opB;
        else if (rbSUB.isChecked() || (sel == 2))
            res = opA - opB;
        else if (rbMULT.isChecked() || (sel == 3))
            res = opA * opB;
        else if (rbDIV.isChecked() || (sel == 4))
            res = opA / opB;
        else {
            tv3.setText("Ingrese operacion");
            return;
        }


        // check box to clear result
        if (cb.isChecked())
            res = 0;

        tv3.setText(String.valueOf(res));
    }




    // about
    public void about(View view) {
        Intent i = new Intent(this, autor.class );
        startActivity(i);
    }
}

