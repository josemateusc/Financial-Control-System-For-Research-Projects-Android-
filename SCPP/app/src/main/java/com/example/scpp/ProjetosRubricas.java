package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ProjetosRubricas extends AppCompatActivity {
    private String projectName;
    private String professorName;
    private String expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos_rubricas);
        Log.i("ActivityStartMain","Activity de rubricas de Projetos criada!");

//        TextView textBemVindo = findViewById(R.id.textView15);






//        textBemVindo.setText("Projeto: "+projectName+"\nProfessor: "+professorName+"\nLista: "+str);


//        TextInputLayout teste = findViewById(R.id.LayoutCapitalPrevisto);
//        teste.setBoxStrokeColor(Color.RED);
    }

    public void buttonContinueClicked(View view) {
        EditText capitalPrevisto = findViewById(R.id.TextCapitalPrevisto);
        EditText capitalGasto = findViewById(R.id.TextCapitalGasto);
        EditText materialPrevisto = findViewById(R.id.TextMaterialPrevisto);
        EditText materialGasto = findViewById(R.id.TextMaterialGasto);
        boolean filled = true;


        TextInputLayout cp = findViewById(R.id.LayoutCapitalPrevisto);
        TextInputLayout mp = findViewById(R.id.LayoutMaterialPrevisto);

        Double capitalP=0.0;
        Double capitalG=0.0;
        Double materialP=0.0;
        Double materialG=0.0;


        if(capitalPrevisto.getText().toString().equals("")){
            //cp.setBoxStrokeColor(Color.RED);
            cp.setError("Campo Obrigatório!");
            filled = false;
        } else{
            cp.setError(null);
            capitalP = Double.parseDouble(capitalPrevisto.getText().toString());
        }

        if(!capitalGasto.getText().toString().equals("")){
            capitalG = Double.parseDouble(capitalGasto.getText().toString());
        }

        if(materialPrevisto.getText().toString().equals("")){
            mp.setError("Campo Obrigatório!");
            filled = false;
        } else{
            mp.setError(null);
            materialP = Double.parseDouble(materialPrevisto.getText().toString());
        }

        if(!materialGasto.getText().toString().equals("")){
            materialG = Double.parseDouble(materialGasto.getText().toString());
        }

        if(filled){
            if(capitalG > capitalP){
                cp.setError("Deve ser maior que o Valor Gasto!");
                Toast.makeText(this,"Valor Gasto não pode ser maior que o Previsto!",Toast.LENGTH_SHORT).show();
                filled = false;
            }
            if(materialG>materialP){
                mp.setError("Deve ser maior que o Valor Gasto!");
                Toast.makeText(this,"Valor Gasto não pode ser maior que o Previsto!",Toast.LENGTH_SHORT).show();
                filled = false;
            }
        }

        if(filled == true){

            Intent intent = getIntent();
            projectName = intent.getStringExtra("projectName");
            professorName = intent.getStringExtra("professorName");
            expenseList = intent.getStringExtra("expenseList");

            Toast.makeText(this, "Dados Completos!", Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(this, ProjetosRubricas2.class);

            intent1.putExtra("projectName", projectName);
            intent1.putExtra("professorName", professorName);
            intent1.putExtra("expenseList", expenseList);

            intent1.putExtra("capitalP", capitalP);
            intent1.putExtra("capitalG", capitalG);
            intent1.putExtra("materialP", materialP);
            intent1.putExtra("materialG", materialG);


            startActivity(intent1);
            finish();

        } else{
            Log.i("ButtonError","Verifique os dados!");
            Toast.makeText(this, "Verifique os Dados!", Toast.LENGTH_SHORT).show();
        }
    }
}