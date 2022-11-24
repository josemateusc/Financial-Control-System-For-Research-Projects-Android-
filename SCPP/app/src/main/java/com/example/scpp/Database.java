package com.example.scpp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tp2.db";
    public static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cria a primeira tabela com projeto e rubricas
        db.execSQL("CREATE TABLE projetos(" +
                "  Nome_Projeto varchar(100) primary key NOT NULL," +
                "  Nome_Professor varchar(100)," +
                "Capital_Previsto float NOT NULL," +
                "Capital_Gasto float DEFAULT 0.0," +
                "Material_Consumo_Previsto float NOT NULL," +
                "Material_Consumo_Gasto float DEFAULT 0.0," +
                "Servicos_PF_Previsto float NOT NULL," +
                " Servicos_PF_Gasto float DEFAULT 0.0," +
                "Servicos_PJ_Previsto float NOT NULL," +
                "Servicos_PJ_Gasto float DEFAULT 0.0," +
                "Diarias_Previsto float NOT NULL," +
                "Diarias_Gasto float DEFAULT 0.0," +
                "Passagens_Previsto float NOT NULL," +
                "Passagens_Gasto float DEFAULT 0.0" +
                ")");

        //Cria a segunda tabela (de rubircas)
//        db.execSQL("create table rubricas(" +
//                "Nome_Projeto varchar(100) NOT NULL," +
//                "Capital_Previsto float NOT NULL," +
//                "Capital_Gasto float DEFAULT 0.0," +
//                "Material_Consumo_Previsto float NOT NULL," +
//                "Material_Consumo_Gasto float DEFAULT 0.0," +
//                "Servicos_PF_Previsto float NOT NULL," +
//                " Servicos_PF_Gasto float DEFAULT 0.0," +
//                "Servicos_PJ_Previsto float NOT NULL," +
//                "Servicos_PJ_Gasto float DEFAULT 0.0," +
//                "Diarias_Previsto float NOT NULL," +
//                "Diarias_Gasto float DEFAULT 0.0," +
//                "Passagens_Previsto float NOT NULL," +
//                "Passagens_Gasto float DEFAULT 0.0," +
//                "FOREIGN KEY (Nome_Projeto) REFERENCES projetos(Nome_Projeto)" +
//                ")");

        //cria a terceira tabela (Lista de Despesas)
        db.execSQL("create table lista_despesas(" +
                "Nome_Projeto varchar(100) NOT NULL," +
                "Despesas char(100) NOT NULL," +
                "FOREIGN KEY (Nome_Projeto) REFERENCES projetos(Nome_Projeto)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS projetos");
//        db.execSQL("DROP TABLE IF EXISTS rubricas");
        db.execSQL("DROP TABLE IF EXISTS lista_despesas");
        onCreate(db);
    }
}
