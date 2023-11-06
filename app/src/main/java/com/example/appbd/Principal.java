package com.example.appbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbd.DAO.UserDAO;
import com.example.appbd.model.User;
import com.example.appbd.R;

public class Principal extends AppCompatActivity {
    TextView txtEmail;
    TextView txtName;
    Button btnEdit;
    Button btnExcluir;
    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        btnEdit = findViewById(R.id.btnEdit);
        btnExcluir = findViewById(R.id.btnExcluir);
        logOut = findViewById(R.id.logout);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email", "abc");
        User user = new User();
        user.setEmail(email);
        UserDAO dao = new UserDAO(getApplicationContext(), user);
        user = dao.obterUserByEmail();
        txtEmail.setText(user.getEmail());
        txtName.setText(user.getNome());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Principal.this, editUser.class);
                startActivity(it);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.delete()){

                    Toast.makeText(Principal.this, "Dados excluídos", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(Principal.this, Login.class);
                    startActivity(it);
                }else{
                    Toast.makeText(Principal.this, "Não foi possivel excluir", Toast.LENGTH_LONG).show();

                }

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editar = sp.edit();
                editar.putString("email", null);
                editar.commit();
                Intent it = new Intent(Principal.this, Login.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public String convertID(int id){
        return getResources().getResourceEntryName(id);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if("config".equals(convertID(R.id.config))){
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, userList.class);
            startActivity(it);
        }
        return true;
    }
}