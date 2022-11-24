package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ProjetosMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos_menu);
        Log.i("ActivityStartMain","Activity de Menu de Projetos criada!");
    }

    public void buttonContinueClicked(View view) {
        //Toast.makeText(this, "Ótima escolha!", Toast.LENGTH_SHORT).show();
        Log.i("ButtonContinueClicked","Botao de continuar para rubricas foi clicado!");

        EditText projectName = findViewById(R.id.editTextProjectName);
        EditText professorName = findViewById(R.id.editTextProfessorName);
        EditText expenseList = findViewById(R.id.editTextList);
        boolean filled = true;

        TextInputLayout layoutProject = findViewById(R.id.layoutProject);
        TextInputLayout layoutProfessor = findViewById(R.id.layoutProfessor);

        if(projectName.getText().toString().equals("")){
            layoutProject.setError("Campo Obrigatório!");
            filled = false;
        } else{
            layoutProject.setError(null);
        }

        if(professorName.getText().toString().equals("")){
            layoutProfessor.setError("Campo Obrigatório!");
            filled = false;
        } else{
            layoutProfessor.setError(null);
        }

        if(filled){
            Intent intent = new Intent(this, ProjetosRubricas.class);

            intent.putExtra("projectName", projectName.getText().toString());

            intent.putExtra("professorName", professorName.getText().toString());

            intent.putExtra("expenseList", expenseList.getText().toString());

            startActivity(intent);
            finish();
        }
    }
}