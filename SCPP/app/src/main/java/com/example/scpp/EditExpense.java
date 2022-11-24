package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditExpense extends AppCompatActivity {
    private ProjetoDAO projetoDAO;
    private String projectName, expenseList;
    private EditText editExpenseList,editProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        Intent intent = getIntent();
        projectName = intent.getStringExtra("projectName");
        projetoDAO = new ProjetoDAO(this);

        editProjectName = findViewById(R.id.projectName);
        editProjectName.setText(projectName);

        editExpenseList = findViewById(R.id.expenseList);
        expenseList = projetoDAO.consultList(projectName);
        if(expenseList.equals("Lista Vazia")){
            editExpenseList.setText("");
        }else{
            editExpenseList.setText(expenseList);
        }
    }

    public void editList(View view){
        if(!editExpenseList.getText().toString().equals("")){
            if(projetoDAO.updateList(projectName,editExpenseList.getText().toString())){
                Toast.makeText(this, "Lista atualizada!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void deleteList(View view){
        if(projetoDAO.removeList(projectName)){
            Toast.makeText(this, "Lista Deletada!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}