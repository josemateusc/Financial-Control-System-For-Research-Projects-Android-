package com.example.scpp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShowProjeto extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView popupValorPrevisto, popupValorGasto,popupValorDisponivel;
    private Button okButton;
    private Projeto projeto;
    private ProjetoDAO projetoDAO;
    private EditText editProject,ediProfessor;
    private ProgressBar capitalBar, materialBar,pfBar,pjBar,diariaBar,passagemBar;
    private TextView capitalPercent,materialPercent,pfPercent,pjPercent,diariaPercent,passagemPercent;
    Double valorPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_projeto);

        Intent intent = getIntent();
        String projectName = intent.getStringExtra("projectName");

        projetoDAO = new ProjetoDAO(this);
        projeto = projetoDAO.consultProject(projectName);

        editProject = findViewById(R.id.showProject);
        editProject.setText(projeto.getProjeto());
        ediProfessor = findViewById(R.id.showProfessor);
        ediProfessor.setText(projeto.getProfessor());

        valorPercent = (projeto.getCapitalGasto()/projeto.getCapitalPrevisto())*100;
        capitalBar = findViewById(R.id.capitalBar);
        capitalBar.setProgress((int)Math.round(valorPercent));
        capitalPercent = findViewById(R.id.capitalPercent);
        capitalPercent.setText(String.format("%.2f",valorPercent)+"%");

        valorPercent = (projeto.getMaterialGasto()/projeto.getMaterialPrevisto())*100;
        materialBar = findViewById(R.id.materialBar);
        materialBar.setProgress((int)Math.round(valorPercent));
        materialPercent = findViewById(R.id.materialPercent);
        materialPercent.setText(String.format("%.2f",valorPercent)+"%");

        valorPercent = (projeto.getServicoPfGasto()/projeto.getServicoPfPrevisto())*100;
        pfBar = findViewById(R.id.pfBar);
        pfBar.setProgress((int)Math.round(valorPercent));
        pfPercent = findViewById(R.id.pfPercent);
        pfPercent.setText(String.format("%.2f",valorPercent)+"%");

        valorPercent = (projeto.getServicoPjGasto()/projeto.getServicoPjPrevisto())*100;
        pjBar = findViewById(R.id.pjBar);
        pjBar.setProgress((int)Math.round(valorPercent));
        pjPercent = findViewById(R.id.pjPercent);
        pjPercent.setText(String.format("%.2f",valorPercent)+"%");

        valorPercent = (projeto.getDiariasGasto()/projeto.getDiariasPrevisto())*100;
        diariaBar = findViewById(R.id.diariaBar);
        diariaBar.setProgress((int)Math.round(valorPercent));
        diariaPercent = findViewById(R.id.diariaPercent);
        diariaPercent.setText(String.format("%.2f",valorPercent)+"%");

        valorPercent = (projeto.getPassagensGasto()/projeto.getPassagensPrevisto())*100;
        passagemBar = findViewById(R.id.passagemBar);
        passagemBar.setProgress((int)Math.round(valorPercent));
        passagemPercent = findViewById(R.id.passagemPercent);
        passagemPercent.setText(String.format("%.2f",valorPercent)+"%");
    }

    public void infoButtonCapital (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getCapitalPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getCapitalGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                                        (projeto.getCapitalPrevisto() - projeto.getCapitalGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void infoButtonMaterial (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getMaterialPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getMaterialGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                (projeto.getMaterialPrevisto() - projeto.getMaterialGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void infoButtonPf (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getServicoPfPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getServicoPfGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                (projeto.getServicoPfPrevisto() - projeto.getServicoPfGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void infoButtonPj (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getServicoPjPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getServicoPjGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                (projeto.getServicoPjPrevisto() - projeto.getServicoPjGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void infoButtonDiaria (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getDiariasPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getDiariasGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                (projeto.getDiariasPrevisto() - projeto.getDiariasGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void infoButtonPassagem (View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popupValorPrevisto = (TextView) popupView.findViewById(R.id.popupValorPrevisto);
        popupValorGasto = (TextView) popupView.findViewById(R.id.popupValorGasto);
        popupValorDisponivel = (TextView) popupView.findViewById(R.id.popupValorDisponivel);
        okButton = (Button) popupView.findViewById(R.id.okButton);

        popupValorPrevisto.setText(popupValorPrevisto.getText().toString()+ projeto.getPassagensPrevisto());
        popupValorGasto.setText(popupValorGasto.getText().toString()+ projeto.getPassagensGasto());
        popupValorDisponivel.setText(popupValorDisponivel.getText().toString()+
                (projeto.getPassagensPrevisto() - projeto.getPassagensGasto()));

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void alterarButton (View view){
        Intent intent = new Intent(this,AlterarProjeto.class);
        intent.putExtra("projectName", this.projeto.getProjeto());
        startActivity(intent);
        finish();
    }
}