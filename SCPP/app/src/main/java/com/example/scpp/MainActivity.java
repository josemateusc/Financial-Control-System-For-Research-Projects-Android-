package com.example.scpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ActivityStartMain","Activity inicial criada!");
    }

    public void buttonAcessClicked(View view) {
        Toast.makeText(this, "Bem Vindo!", Toast.LENGTH_SHORT).show();
        Log.i("ButtonClicked","Botao inicial foi clicado!");

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

}