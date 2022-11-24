package com.example.scpp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ProjetoDAO {
    private Context context;
    private SQLiteDatabase database;

    public ProjetoDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    //Consultar
    public ArrayList<Projeto> projectList() {
        ArrayList<Projeto> result = new ArrayList<Projeto>();
        String sql = "SELECT * FROM projetos ORDER BY Nome_Projeto";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String projeto = cursor.getString(0);
            String professor = cursor.getString(1);
            Double capitalPrevisto = cursor.getDouble(2);
            Double capitalGasto = cursor.getDouble(3);
            Double materialPrevisto = cursor.getDouble(4);
            Double materialGasto = cursor.getDouble(5);
            Double pfPrevisto = cursor.getDouble(6);
            Double pfGasto = cursor.getDouble(7);
            Double pjPrevisto = cursor.getDouble(8);
            Double pjGasto = cursor.getDouble(9);
            Double diariasPrevisto = cursor.getDouble(10);
            Double diariasGasto = cursor.getDouble(11);
            Double passagensPrevisto = cursor.getDouble(12);
            Double passagensGasto = cursor.getDouble(13);
            String listaDespesas = consultList(projeto);

            result.add(new Projeto(projeto,professor,capitalPrevisto,capitalGasto,materialPrevisto,materialGasto,
                    pfPrevisto,pfGasto, pjPrevisto, pjGasto,diariasPrevisto,diariasGasto,
                    passagensPrevisto,passagensGasto,listaDespesas));
        }
        return result;
    }

    public Projeto consultProject(String project){
        String sql = "SELECT * FROM projetos WHERE Nome_Projeto='"+project+"'";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String projeto = cursor.getString(0);
            String professor = cursor.getString(1);
            Double capitalPrevisto = cursor.getDouble(2);
            Double capitalGasto = cursor.getDouble(3);
            Double materialPrevisto = cursor.getDouble(4);
            Double materialGasto = cursor.getDouble(5);
            Double pfPrevisto = cursor.getDouble(6);
            Double pfGasto = cursor.getDouble(7);
            Double pjPrevisto = cursor.getDouble(8);
            Double pjGasto = cursor.getDouble(9);
            Double diariasPrevisto = cursor.getDouble(10);
            Double diariasGasto = cursor.getDouble(11);
            Double passagensPrevisto = cursor.getDouble(12);
            Double passagensGasto = cursor.getDouble(13);
            String listaDespesas = consultList(projeto);

            return new Projeto(projeto,professor,capitalPrevisto,capitalGasto,materialPrevisto,materialGasto,
                    pfPrevisto,pfGasto, pjPrevisto, pjGasto,diariasPrevisto,diariasGasto,
                    passagensPrevisto,passagensGasto,listaDespesas);
        }
        return null;
    }

    public String consultList(String projeto){
        String result = "";
        String sql = "SELECT * FROM lista_despesas where Nome_Projeto='"+projeto+"' ORDER BY Nome_Projeto";
        Cursor cursor = database.rawQuery(sql, null);
        int cont=0;

        while (cursor.moveToNext()) {
            if(cont != 0) {
                result += ", ";
            }
            result += cursor.getString(1);
            cont++;
        }
        if (result.equals("")){
            result = "Lista Vazia";
        }
        return result;
    }

    //Criar
    public boolean addProject(Projeto projeto){
        String sqlProjeto = "INSERT INTO projetos VALUES (" +
                "'"+ projeto.getProjeto() +"'," +
                "'"+ projeto.getProfessor() +"',"+
                "'"+ projeto.getCapitalPrevisto() +"'," +
                "'"+ projeto.getCapitalGasto() +"'," +
                "'"+ projeto.getMaterialPrevisto() +"'," +
                "'"+ projeto.getMaterialGasto() +"'," +
                "'"+ projeto.getServicoPfPrevisto() +"'," +
                "'"+ projeto.getServicoPfGasto() +"'," +
                "'"+ projeto.getServicoPjPrevisto() +"'," +
                "'"+ projeto.getServicoPjGasto() +"'," +
                "'"+ projeto.getDiariasPrevisto() +"'," +
                "'"+ projeto.getDiariasGasto() +"'," +
                "'"+ projeto.getPassagensPrevisto() +"'," +
                "'"+ projeto.getPassagensGasto() +"')" ;
        try{
            database.execSQL(sqlProjeto);

            if(!createList(projeto.getProjeto(),projeto.getListaDespesas())){
                return false;
            }

            return true;
        }
        catch (SQLException e){
            Toast.makeText(context, "Erro ao Cadastrar! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean createList(String projeto, String listaDespesas){
        try{
            String[] despesa = listaDespesas.split(",");

            for (String s : despesa) {
                if (s != "") {
                    String query = "INSERT INTO lista_despesas VALUES ( '" + projeto + "',";

                    query += "'" + s + "'";

                    query += ")";

                    database.execSQL(query);
                }
            }

            return true;
        }
        catch (SQLException e){
            Toast.makeText(context, "Erro ao Cadastrar! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //Update
    public boolean updateProject(Projeto projeto){
        String sql = "UPDATE projetos SET " +
                "Nome_Professor='"+ projeto.getProfessor() +"'," +
                "Capital_Previsto='"+ projeto.getCapitalPrevisto() +"'," +
                "Capital_Gasto='"+ projeto.getCapitalGasto() +"'," +
                "Material_Consumo_Previsto='"+ projeto.getMaterialPrevisto() +"'," +
                "Material_Consumo_Gasto='"+ projeto.getMaterialGasto() +"'," +
                "Servicos_PF_Previsto='"+ projeto.getServicoPfPrevisto() +"'," +
                "Servicos_PF_Gasto='"+ projeto.getServicoPfGasto() +"'," +
                "Servicos_PJ_Previsto='"+ projeto.getServicoPjPrevisto() +"'," +
                "Servicos_PJ_Gasto='"+ projeto.getServicoPjGasto() +"'," +
                "Diarias_Previsto='"+ projeto.getDiariasPrevisto() +"'," +
                "Diarias_Gasto='"+ projeto.getDiariasGasto() +"'," +
                "Passagens_Previsto='"+ projeto.getPassagensPrevisto() +"'," +
                "Passagens_Gasto='"+ projeto.getPassagensGasto() +"' " +
                "WHERE Nome_Projeto='" + projeto.getProjeto() +"'";

        try {
            database.execSQL(sql);
            if(updateList(projeto.getProjeto(),projeto.getListaDespesas())){
                return false;
            }

            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean updateList(String projeto, String listaDespesas){

        try{
            removeList(projeto);
            createList(projeto, listaDespesas);

            return true;
        }
        catch (SQLException e){
            Toast.makeText(context, "Erro ao Cadastrar! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //Deletar
    public boolean removeProject(String projeto){
        try{
            removeList(projeto);
            String sql = "DELETE FROM projetos WHERE nome_projeto='"+ projeto +"'";
            database.execSQL(sql);

            return true;
        }
        catch (SQLException e){
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean removeList(String projeto){
        try {
            String sql = "DELETE FROM lista_despesas WHERE nome_projeto='"+ projeto +"'";
            database.execSQL(sql);
            return true;
        }
        catch (SQLException e){
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
