package com.example.appbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbd.DAO.UserDAO;
import com.example.appbd.model.User;

public class editUser extends AppCompatActivity {

    EditText editName;
    EditText editEmail;
    EditText editPassword;
    Button btnEditUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmailAddress);
        editPassword = findViewById(R.id.editPassword);
        btnEditUser = findViewById(R.id.btnEditUser);

        SharedPreferences sp = getSharedPreferences("appLogin",
                Context.MODE_PRIVATE);


        String email = sp.getString("email", "abc");

        User user = new User();
        user.setEmail(email);
        UserDAO userDao = new UserDAO(getApplicationContext(), user);

        user = userDao.obterUserByEmail();

        editName.setText(user.getNome());
        editEmail.setText(user.getEmail());
        editPassword.setText(user.getPassword());

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editar = sp.edit();
                editar.putString("email", editEmail.getText().toString());
                editar.commit();

                UserDAO userDao = new UserDAO(getApplicationContext(),
                        new User(editEmail.getText().toString(), editName.getText().toString(),
                                editPassword.getText().toString()));
                if(editEmail.getText().toString().isEmpty()){
                    editEmail.setError("Preencha seu Email");
                } else if (editName.getText().toString().isEmpty()) {
                    editName.setError("Preencha seu Nome");
                }else if(editPassword.getText().toString().isEmpty()){
                    editPassword.setError("Preencha sua Senha");
                }else{
                    if(userDao.update()){
                        Intent it = new Intent(editUser.this, Principal.class);
                        startActivity(it);
                    }else{
                        Toast.makeText(editUser.this, "Não foi possivel editar", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}