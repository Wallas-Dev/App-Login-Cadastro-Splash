package com.example.appbd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.appbd.DAO.UserDAO;
import com.example.appbd.model.User;

public class userList extends AppCompatActivity {
    ListView listView;
    UserDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listUser);
        dao = new UserDAO(getApplicationContext(), new User());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.user_layout,
                dao.listarUsers(),
                new String[]{"_id", "nome"},
                new int[]{R.id.tvName, R.id.tvEmail}, 0);
        listView.setAdapter(adapter);
    }
}