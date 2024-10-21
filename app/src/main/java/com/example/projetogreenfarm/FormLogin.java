package com.example.projetogreenfarm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {


    private TextView text_cadastrar;

    private TextView text_esqueci_senha;

    private EditText edit_cpf,edit_senha;

    private Button bt_entrar;

    private ProgressBar progressBar;

    String[] mensagens = {"Preencha todos os campos"};


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

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cpf  = edit_cpf.getText().toString();
                String senha = edit_senha.getText().toString();

                if (cpf.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else {
                    AutenticarUsuario(view);

                }

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

    private void AutenticarUsuario(View view){

        String cpf  = edit_cpf.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(cpf,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        TelaPrincipal();
                        }
                    },3000);



                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch (Exception e){
                        erro = "Erro ao fazer login";

                    }
                    Snackbar snackbar = Snackbar.make(view,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual != null){
            TelaPrincipal();
        }

    }

    private void  TelaPrincipal(){

        Intent intent = new Intent(FormLogin.this,TelaPrincipal.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        text_cadastrar = findViewById(R.id.text_cadastrar);
        edit_cpf = findViewById(R.id.edit_cpf);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressbar);

    }

    private void IniciarComponentes1(){
        text_esqueci_senha = findViewById(R.id.text_esqueci_senha);
    }
}

































































