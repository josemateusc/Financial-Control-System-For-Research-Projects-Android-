package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i("ActivityStartMain","Activity de seleção de path criada!");
    }

    public void buttonProjetosClicked(View view) {
        Intent intent = new Intent(this, AcoesProjetos.class);
        startActivity(intent);
    }

    public void buttonListasClicked(View view) {

        Intent intent = new Intent(this, AcoesDespesas.class);
        startActivity(intent);
    }
}