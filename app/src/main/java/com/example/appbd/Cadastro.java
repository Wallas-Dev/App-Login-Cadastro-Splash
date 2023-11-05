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

public class Cadastro extends AppCompatActivity {
    TextView email;
    TextView name;
    TextView senha;
    Button btnCad;
    UserDAO uDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.editEmailAddress);
        name = findViewById(R.id.editName);
        senha = findViewById(R.id.editPassword);
        btnCad = findViewById(R.id.btnEditUser);

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", email.getText().toString());
                editor.commit();


                uDao = new UserDAO(getApplicationContext(),
                        new User(email.getText().toString(),
                                name.getText().toString(),
                                senha.getText().toString()
                        ));

                if(uDao.inserir()){
                    Intent it = new Intent(Cadastro.this, Principal.class);
                    startActivity(it);
                }else{
                    Toast.makeText(Cadastro.this, "Errou demais garotcho", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}