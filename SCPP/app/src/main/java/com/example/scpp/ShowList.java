package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ShowList extends AppCompatActivity {
    private Projeto projeto;
    private ProjetoDAO projetoDAO;
    private EditText editProjectName,editProfessorName,editExpenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        Intent intent = getIntent();
        String projectName = intent.getStringExtra("projectName");

        projetoDAO = new ProjetoDAO(this);
        projeto = projetoDAO.consultProject(projectName);

        editProjectName = findViewById(R.id.projectName);
        editProjectName.setText(projeto.getProjeto());

        editProfessorName = findViewById(R.id.professorName);
        editProfessorName.setText(projeto.getProfessor());

        editExpenseList = findViewById((R.id.expenseList));
        editExpenseList.setText(projeto.getListaDespesas());
    }

    public void editExpenseClicked (View view){
        Intent intent = new Intent(this,EditExpense.class);
        intent.putExtra("projectName", this.projeto.getProjeto());
        startActivity(intent);
        finish();
    }
}