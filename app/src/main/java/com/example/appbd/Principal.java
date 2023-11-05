package com.example.appbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbd.DAO.UserDAO;
import com.example.appbd.model.User;

public class Principal extends AppCompatActivity {
    TextView txtEmail;
    TextView txtName;
    Button btnEdit;
    Button btnExcluir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtmail);
        btnEdit = findViewById(R.id.btnEdit);
        btnExcluir = findViewById(R.id.btnExcluir);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email", "abc");
        User user = new User();
        user.setEmail(email);
        UserDAO dao = new UserDAO(getApplicationContext(), user);
        user = dao.obterUserByEmail();
        txtEmail.setText(user.getEmail());
        txtName.setText(user.getNome());
    }
}