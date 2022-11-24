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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AcoesDespesas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private SearchView busca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_despesas);

        recyclerView = findViewById(R.id.list_recycler_expense);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ExpenseAdapter(this);
        recyclerView.setAdapter(adapter);

        busca = findViewById(R.id.searchView_expense);
        busca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RecyclerView recyclerView2 = (RecyclerView) recyclerView.findViewById(R.id.list_recycler_expense);
                if(query.isEmpty()){
                    recyclerView2.setAdapter(adapter);
                } else{
                    recyclerView2.setAdapter(new ExpenseAdapterBusca(AcoesDespesas.this,query));
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

    public void reloadExpense(View view){
        RecyclerView recyclerView3 = (RecyclerView) recyclerView.findViewById(R.id.list_recycler_expense);
        recyclerView3.setAdapter(adapter);
    }

    public void buttonAddExpenseClicked(View view) {
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }
}

class ExpenseAdapterBusca extends RecyclerView.Adapter<ExpenseViewHolder> {
    private Context context;
    private String query;
    private Projeto projeto;
    ProjetoDAO projetoDAO;

    public ExpenseAdapterBusca(Context context,String query) {
        this.context = context;
        this.query = query;
        projetoDAO = new ProjetoDAO(context);
        update();
    }

    public void update() {
        projeto = projetoDAO.consultProject(query);
    }

    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ExpenseViewHolder vh = new ExpenseViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        try {
            holder.project.setText(projeto.getProjeto());
            holder.info.setText("Clique para visualizar as Despesas");
        }catch (Exception e){
            holder.project.setText("Inexistente");
            holder.info.setText("");
        }
    }

    public int getItemCount() { return 1; }
}

class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {
    private Context context;
    private ArrayList<Projeto> projeto;
    ProjetoDAO projetoDAO;

    public ExpenseAdapter(Context context) {
        this.context = context;
        projetoDAO = new ProjetoDAO(context);
        update();
    }

    public void update() { projeto = projetoDAO.projectList(); }

    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ExpenseViewHolder vh = new ExpenseViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        holder.project.setText(projeto.get(position).getProjeto());
        holder.info.setText("Clique para visualizar as Despesas");
    }

    public int getItemCount() { return projeto.size(); }
}

class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView info, project;

    public ExpenseViewHolder(ConstraintLayout v, Context context) {
        super(v);
        this.context = context;
        project = v.findViewById(R.id.itemProject);
        info = v.findViewById(R.id.itemInfo);
        v.setOnClickListener(this);
    }

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onClick(View v) {

        if(!this.project.getText().toString().equals("Inexistente")){
            Intent intent = new Intent(context, ShowList.class);
            intent.putExtra("projectName", this.project.getText().toString());
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "Esse projeto não existe!",Toast.LENGTH_SHORT).show();
        }
    }
}