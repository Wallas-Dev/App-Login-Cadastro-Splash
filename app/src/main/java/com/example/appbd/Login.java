package com.example.appbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbd.DAO.UserDAO;
import com.example.appbd.model.User;

public class Login extends AppCompatActivity {
    TextView edtEmail;
    TextView edtSenha;
    Button btnEntrar;
    TextView directCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.txtEmail);
        edtSenha = findViewById(R.id.txtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        directCad = findViewById(R.id.directCad);

        directCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Cadastro.class);
                startActivity(it);
            }
        });
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", edtEmail.getText().toString());
                editor.commit();

                UserDAO dao = new UserDAO(getApplicationContext(), new User(edtEmail.getText().toString(), "", edtSenha.getText().toString()));
                if (dao.VerficarEmaileSenha()){
                    Intent it = new Intent(Login.this, Principal.class);
                    startActivity(it);
                }else {
                    Toast.makeText(Login.this, "Dados Incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}