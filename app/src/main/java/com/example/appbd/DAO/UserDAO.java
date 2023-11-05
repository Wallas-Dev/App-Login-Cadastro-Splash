package com.example.appbd.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appbd.helper.DBhelper;
import com.example.appbd.model.User;

public class UserDAO {

    private User user;
    private DBhelper db;

    public UserDAO(Context ctx, User us) {
        db = new DBhelper(ctx);
        user = us;
    }

    public boolean VerficarEmaileSenha() {
        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "SELECT * FROM user WHERE email = ? AND senha = ?";
        Cursor cursor = dbLite.rawQuery(sql, new String[]{this.user.getEmail(), this.user.getPassword()});

        if(cursor.getCount() > 0){
            return true;
        };

        return false;
    }

    public boolean inserir (){
        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getNome());
        cv.put("senha", this.user.getPassword());
        cv.put("email", this.user.getEmail());

        long ret = dbLite.insert("user",
                null,
                cv);

        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean update(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getNome());
        cv.put("senha", this.user.getPassword());
        cv.put("email", this.user.getEmail());

        long ret = dbLite.update("user", cv,"email = ?", new String[]{this.user.getEmail()} );
        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean delete(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        long ret = dbLite.delete("user","email = ?", new String[]{this.user.getEmail()} );

        if (ret > 0){
            return true;
        }
        return false;
    }


    public User obterUserByEmail(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "Select * From user where email = ?; ";
        Cursor c = dbLite.rawQuery(sql,new String[]{this.user.getEmail()});
        if(c != null){
            c.moveToFirst();
        }

        this.user.setEmail(c.getString(0));
        this.user.setPassword(c.getString(1));
        this.user.setNome(c.getString(2));

        return this.user;

    }
}
