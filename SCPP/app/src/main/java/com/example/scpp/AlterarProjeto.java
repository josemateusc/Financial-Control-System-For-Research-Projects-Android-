package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AlterarProjeto extends AppCompatActivity {
    private ProjetoDAO projetoDAO;
    private String projectName;
    private EditText editProfessor,editCapital,editMaterial,editPF,editPJ,editDiaria,editPassagem;
    private TextInputLayout layoutCapital, layoutMaterial, layoutPF, layoutPJ,layoutDiaria,layoutPassagem;
    private Projeto projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_projeto);

        Intent intent = getIntent();
        projectName = intent.getStringExtra("projectName");

        projetoDAO = new ProjetoDAO(this);
        projeto = projetoDAO.consultProject(projectName);

        editProfessor = findViewById(R.id.textProfessor);
        editProfessor.setText(projeto.getProfessor());

        editCapital = findViewById(R.id.textCapital);
        editCapital.setText(String.valueOf(projeto.getCapitalGasto()));

        editMaterial = findViewById(R.id.textMaterial);
        editMaterial.setText(String.valueOf(projeto.getMaterialGasto()));

        editPF = findViewById(R.id.textPF);
        editPF.setText(String.valueOf(projeto.getServicoPfGasto()));

        editPJ = findViewById(R.id.textPJ);
        editPJ.setText(String.valueOf(projeto.getServicoPjGasto()));

        editDiaria = findViewById(R.id.textDiaria);
        editDiaria.setText(String.valueOf(projeto.getDiariasGasto()));

        editPassagem = findViewById(R.id.textPassagem);
        editPassagem.setText(String.valueOf(projeto.getPassagensGasto()));

        layoutCapital = findViewById(R.id.layoutCapital);
        layoutMaterial = findViewById(R.id.layoutMaterial);
        layoutPF = findViewById((R.id.layoutPF));
        layoutPJ = findViewById((R.id.layoutPJ));
        layoutDiaria = findViewById((R.id.layoutDiaria));
        layoutPassagem = findViewById((R.id.layoutPassagem));
    }

    public void editButton(View view){
        boolean ok = true;

        if(!editProfessor.getText().toString().equals("")){
            projeto.setProfessor(editProfessor.getText().toString());
        }
        if(!editCapital.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editCapital.getText().toString());
            if(tmp > projeto.getCapitalPrevisto()){
                ok = false;
                layoutCapital.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getCapitalPrevisto());
            }
            else{
                layoutCapital.setError(null);
                projeto.setCapitalGasto(tmp);
            }
        }
        if(!editMaterial.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editMaterial.getText().toString());
            if(tmp > projeto.getMaterialPrevisto()){
                ok = false;
                layoutMaterial.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getMaterialPrevisto());
            }
            else{
                layoutMaterial.setError(null);
                projeto.setMaterialGasto(tmp);
            }

        }
        if(!editPF.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editPF.getText().toString());
            if(tmp > projeto.getServicoPfPrevisto()){
                ok = false;
                layoutPF.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getServicoPfPrevisto());
            }
            else{
                layoutPF.setError(null);
                projeto.setServicoPfGasto(tmp);
            }
        }
        if(!editPJ.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editPJ.getText().toString());
            if(tmp > projeto.getServicoPjPrevisto()){
                ok = false;
                layoutPJ.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getServicoPjPrevisto());
            }
            else{
                layoutPJ.setError(null);
                projeto.setServicoPjGasto(tmp);
            }
        }
        if(!editDiaria.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editDiaria.getText().toString());
            if(tmp > projeto.getDiariasPrevisto()){
                ok = false;
                layoutDiaria.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getDiariasPrevisto());
            }
            else{
                layoutDiaria.setError(null);
                projeto.setDiariasGasto(tmp);
            }
        }
        if(!editPassagem.getText().toString().equals("")){
            Double tmp = Double.parseDouble(editPassagem.getText().toString());
            if(tmp > projeto.getPassagensPrevisto()){
                ok = false;
                layoutPassagem.setError("Valor Gasto não pode ser maior que R$ "+ projeto.getPassagensPrevisto());
            }else{
                layoutPassagem.setError(null);
                projeto.setPassagensGasto(tmp);
            }
        }
        if(ok){
            projetoDAO.updateProject(projeto);
            Toast.makeText(this, "Projeto atualizado!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void deleteButton(View view){
        if(projetoDAO.removeProject(projeto.getProjeto())){
            finish();
        }
    }
}