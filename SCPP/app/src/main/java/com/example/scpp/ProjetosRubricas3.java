package com.example.scpp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ProjetosRubricas3 extends AppCompatActivity {
    private String projectName;
    private String professorName;
    private String expenseList;
    private Double capitalP;
    private Double capitalG;
    private Double materialP;
    private Double materialG;
    private Double PFPrevisto;
    private Double PFGasto;
    private Double PJPrevisto;
    private Double PJGasto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos_rubricas3);
    }

    public void buttonCreateClicked(View view) {
        EditText textDiariaPrevisto = findViewById(R.id.TextDiariaPrevista);
        EditText textDiariaGasto = findViewById(R.id.TextDiariaGasto);
        EditText textPassagemPrevisto = findViewById(R.id.TextPassagemPrevisto);
        EditText textPassagemGasto = findViewById(R.id.TextPassagemGasto);
        boolean filled = true;

        TextInputLayout layoutDiaria = findViewById(R.id.LayoutDiariaPrevista);
        TextInputLayout layoutPassagem = findViewById(R.id.LayoutPassagemPrevisto);

        Double DiariaPrevisto=0.0; //Valor em Double do Diarias Previsto
        Double DiariaGasto=0.0; //Valor em Double do Diarias Gasto
        Double PassagemPrevisto=0.0; //Valor em Double do Passagem Previsto
        Double PassagemGasto=0.0; //Valor em Double do Passagem Gasto

        if(textDiariaPrevisto.getText().toString().equals("")){
            //cp.setBoxStrokeColor(Color.RED);
            layoutDiaria.setError("Campo Obrigatório!");
            filled = false;
        } else{
            layoutDiaria.setError(null);
            DiariaPrevisto = Double.parseDouble(textDiariaPrevisto.getText().toString());
        }

        if(!textDiariaGasto.getText().toString().equals("")){
            DiariaGasto = Double.parseDouble(textDiariaGasto.getText().toString());
        }

        if(textPassagemPrevisto.getText().toString().equals("")){
            //cp.setBoxStrokeColor(Color.RED);
            layoutPassagem.setError("Campo Obrigatório!");
            filled = false;
        } else{
            layoutPassagem.setError(null);
            PassagemPrevisto = Double.parseDouble(textPassagemPrevisto.getText().toString());
        }

        if(!textPassagemGasto.getText().toString().equals("")){
            PassagemGasto = Double.parseDouble(textPassagemGasto.getText().toString());
        }

        if(filled){
            if(DiariaGasto > DiariaPrevisto){
                layoutDiaria.setError("Deve ser maior que o Valor Gasto!");
                Toast.makeText(this,"Valor Gasto não pode ser maior que o Previsto!",Toast.LENGTH_SHORT).show();
                filled = false;
            }
            if(PassagemGasto > PassagemPrevisto){
                layoutPassagem.setError("Deve ser maior que o Valor Gasto!");
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
            PFPrevisto = intent.getDoubleExtra("PFPrevisto",0.0);
            PFGasto = intent.getDoubleExtra("PFGasto",0.0);
            PJPrevisto = intent.getDoubleExtra("PJPrevisto",0.0);
            PJGasto = intent.getDoubleExtra("PJGasto",0.0);


            Projeto projeto = new Projeto(projectName,professorName,capitalP,capitalG,
                                            materialP,materialG,PFPrevisto,PFGasto,
                                            PJPrevisto,PJGasto,DiariaPrevisto,DiariaGasto,
                                            PassagemPrevisto,PassagemGasto,expenseList);

            ProjetoDAO projetoDAO = new ProjetoDAO(this);

            if(projetoDAO.addProject(projeto)){
                Toast.makeText(this, "Projeto Cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            }

        } else{
            Log.i("ButtonError","Verifique os Dados!");
            Toast.makeText(this, "Verifique os Dados!", Toast.LENGTH_SHORT).show();
        }

    }
}