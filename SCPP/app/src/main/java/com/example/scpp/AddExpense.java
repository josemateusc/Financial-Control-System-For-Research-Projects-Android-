package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
    }

    public void buttonAddClicked(View view){
        EditText projectName = findViewById(R.id.projectName);
        EditText expenseList = findViewById(R.id.editExpenseList);
        boolean filled = true;

        TextInputLayout projectLayout = findViewById(R.id.projectLayout);
        TextInputLayout expenseLayout = findViewById(R.id.expenseLayout);

        if(projectName.getText().toString().equals("")){
            projectLayout.setError("Campo Obrigatório!");
            filled = false;
        } else{
            projectLayout.setError(null);
        }

        if(expenseList.getText().toString().equals("")){
            expenseLayout.setError("Campo Obrigatório!");
            filled = false;
        } else{
            expenseLayout.setError(null);
        }

        ProjetoDAO projetoDAO = new ProjetoDAO(this);

        if(filled){
            try {
                if (!projetoDAO.consultProject(projectName.getText().toString()).equals("")) {

                    //Se o elemento já está na lista
                    boolean ok = true;
                    String lista = projetoDAO.consultList(projectName.getText().toString());
                    if(lista.equals("Lista Vazia")){
                        lista = expenseList.getText().toString();
                    }
                    else{
                        String tmp[] = lista.split(",");
                        for(String str : tmp) {
                            if(str.replace(" ","").equals(expenseList.getText().toString().replace(" ",""))) {
                                ok = false;
                            }
                        }
                        if(ok) {
                            lista = expenseList.getText().toString();
                        }
                    }
                    if(ok) {
                        projetoDAO.createList(projectName.getText().toString(),lista);
                        Toast.makeText(this, "Item Adicionado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Item já existe!", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                projectLayout.setError("Campo Obrigatório!");
                filled = false;
                Toast.makeText(this, "Projeto Inexistente!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}