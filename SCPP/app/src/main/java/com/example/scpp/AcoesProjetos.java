package com.example.scpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AcoesProjetos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    private SearchView busca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_projetos);

        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProjectAdapter(this);
        recyclerView.setAdapter(adapter);

        busca = findViewById(R.id.searchView);
        busca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RecyclerView recyclerView2 = (RecyclerView) recyclerView.findViewById(R.id.list_recycler);
                if(query.isEmpty()){
                    recyclerView2.setAdapter(adapter);
                } else{
                    recyclerView2.setAdapter(new ProjectAdapterBusca(AcoesProjetos.this,query));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void reload(View view){
        RecyclerView recyclerView3 = (RecyclerView) recyclerView.findViewById(R.id.list_recycler);
        recyclerView3.setAdapter(adapter);
    }

    public void buttonCadastrarClicked(View view) {
        Intent intent = new Intent(this, ProjetosMenu.class);
        startActivity(intent);
    }
}

class ProjectAdapterBusca extends RecyclerView.Adapter<ProjectViewHolder> {
    private Context context;
    private String query;
    private Projeto projeto;
    ProjetoDAO projetoDAO;

    public ProjectAdapterBusca(Context context,String query) {
        this.context = context;
        this.query = query;
        projetoDAO = new ProjetoDAO(context);
        update();
    }

    public void update() {
        projeto = projetoDAO.consultProject(query);
    }

    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ProjectViewHolder vh = new ProjectViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        try {
            holder.project.setText(projeto.getProjeto());
            holder.info.setText(projeto.getProfessor());
        }catch (Exception e){
            holder.project.setText("Inexistente");
            holder.info.setText("");
        }
    }

    public int getItemCount() { return 1; }
}

class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {
    private Context context;
    private ArrayList<Projeto> projeto;
    ProjetoDAO projetoDAO;

    public ProjectAdapter(Context context) {
        this.context = context;
        projetoDAO = new ProjetoDAO(context);
        update();
    }

    public void update() { projeto = projetoDAO.projectList(); }

    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ProjectViewHolder vh = new ProjectViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.project.setText(projeto.get(position).getProjeto());
        holder.info.setText(projeto.get(position).getProfessor());
    }

    public int getItemCount() { return projeto.size(); }
}

class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView info, project;

    public ProjectViewHolder(ConstraintLayout v, Context context) {
        super(v);
        this.context = context;
        project = v.findViewById(R.id.itemProject);
        info = v.findViewById(R.id.itemInfo);
        v.setOnClickListener(this);
    }

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onClick(View v) {

        if(!this.project.getText().toString().equals("Inexistente")){
            Intent intent = new Intent(context, ShowProjeto.class);
            intent.putExtra("projectName", this.project.getText().toString());
//        Log.i("Teste", this.project.getText().toString());
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "Esse projeto não existe!",Toast.LENGTH_SHORT).show();
        }
    }
}
