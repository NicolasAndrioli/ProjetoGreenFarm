package com.example.projetogreenfarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;


import androidx.appcompat.app.AppCompatActivity;

public class FormLogin extends AppCompatActivity {


    private TextView text_cadastrar;

    private TextView text_esqueci_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Verifica se a ActionBar existe antes de tentar escondê-la
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        IniciarComponentes();
        IniciarComponentes1();

        text_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FormLogin.this,FormCadastro.class);
                startActivity(intent);


            }
        });

        text_esqueci_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(FormLogin.this, FormRedefinirSenha.class);
                startActivity(intent1);
            }
        });
    }

    private void IniciarComponentes(){
        text_cadastrar = findViewById(R.id.text_cadastrar);
    }

    private void IniciarComponentes1(){
        text_esqueci_senha = findViewById(R.id.text_esqueci_senha);
    }
}

































































