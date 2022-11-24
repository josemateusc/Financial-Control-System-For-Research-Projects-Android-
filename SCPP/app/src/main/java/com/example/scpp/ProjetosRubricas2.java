package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ProjetosRubricas2 extends AppCompatActivity {
    private String projectName;
    private String professorName;
    private String expenseList;
    private Double capitalP;
    private Double capitalG;
    private Double materialP;
    private Double materialG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos_rubricas2);
    }

    public void buttonContinueClicked(View view) {
        EditText pfPrevisto = findViewById(R.id.TextPFPrevisto);
        EditText pfGasto = findViewById(R.id.TextPFGasto);
        EditText pjPrevisto = findViewById(R.id.TextPJPrevisto);
        EditText pjGasto = findViewById(R.id.TextPJGasto);
        boolean filled = true;

        TextInputLayout pfpLayout = findViewById(R.id.LayoutPFPrevisto);
        TextInputLayout pjpLayout = findViewById(R.id.LayoutPJPrevisto);

        Double pfp=0.0; //Valor em Double do PF Previsto
        Double pfg=0.0; //Valor em Double do PF Gasto
        Double pjp=0.0; //Valor em Double do PJ Previsto
        Double pjg=0.0; //Valor em Double do PJ Gasto

        if(pfPrevisto.getText().toString().equals("")){
            //cp.setBoxStrokeColor(Color.RED);
            pfpLayout.setError("Campo Obrigatório!");
            filled = false;
        } else{
            pfpLayout.setError(null);
            pfp = Double.parseDouble(pfPrevisto.getText().toString());
        }

        if(!pfGasto.getText().toString().equals("")){
            pfg = Double.parseDouble(pfGasto.getText().toString());
        }

        if(pjPrevisto.getText().toString().equals("")){
            pjpLayout.setError("Campo Obrigatório!");
            filled = false;
        } else{
            pjpLayout.setError(null);
            pjp = Double.parseDouble(pjPrevisto.getText().toString());
        }

        if(!pjGasto.getText().toString().equals("")){
            pjg = Double.parseDouble(pjGasto.getText().toString());
        }

        if(filled){
            if(pfg > pfp){
                pfpLayout.setError("Deve ser maior que o Valor Gasto!");
                Toast.makeText(this,"Valor Gasto não pode ser maior que o Previsto!",Toast.LENGTH_SHORT).show();
                filled = false;
            }
            if(pjg>pjp){
                pjpLayout.setError("Deve ser maior que o Valor Gasto!");
                Toast.makeText(this,"Valor Gasto não pode ser maior que o Previsto!",Toast.LENGTH_SHORT).show();
                filled = false;
            }
        }

        if(filled == true){
            Intent intent = getIntent();
            projectName = intent.getStringExtra("projectName");
            professorName = intent.getStringExtra("professorName");
            expenseList = intent.getStringExtra("expenseList");
            capitalP = intent.getDoubleExtra("capitalP",0.0);
            capitalG = intent.getDoubleExtra("capitalG",0.0);
            materialP = intent.getDoubleExtra("materialP",0.0);
            materialG = intent.getDoubleExtra("materialG",0.0);

            Intent intent1 = new Intent(this, ProjetosRubricas3.class);

            intent1.putExtra("projectName", projectName);
            intent1.putExtra("professorName", professorName);
            intent1.putExtra("expenseList", expenseList);

            intent1.putExtra("capitalP", capitalP);
            intent1.putExtra("capitalG", capitalG);
            intent1.putExtra("materialP", materialP);
            intent1.putExtra("materialG", materialG);

            intent1.putExtra("PFPrevisto",pfp);
            intent1.putExtra("PFGasto",pfg);
            intent1.putExtra("PJPrevisto",pjp);
            intent1.putExtra("PJGasto",pjg);


            startActivity(intent1);
            finish();

        } else{
            Log.i("ButtonError","Verifique os Dados!");
            Toast.makeText(this, "Verifique os Dados!!", Toast.LENGTH_SHORT).show();
        }
    }
}