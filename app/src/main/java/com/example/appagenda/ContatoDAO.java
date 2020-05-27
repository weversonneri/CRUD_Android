package com.example.appagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ContatoDAO extends SQLiteOpenHelper {
    public ContatoDAO(Context context) {
        super(context, "agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contatos (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL," +
                "telefone TEXT, " +
                "email TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adicionar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDados(contato);

        db.insert("Contatos", null, dados);

    }

    @NonNull
    private ContentValues getDados(Contato contato) {
        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("telefone", contato.getTelefone());
        dados.put("email", contato.getEmail());
        return dados;
    }

    public ArrayList<Contato> buscaContato () {
        String sql = "SELECT * FROM Contatos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<Contato> contatos = new ArrayList<Contato>();
        while (cursor.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            contato.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            contatos.add(contato);
        }
        cursor.close();
        return contatos;
    }

    public void alterar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDados(contato);

        String[] params = {contato.getId().toString()};
        db.update("Contatos", dados, "id = ?", params);

    }

    public void apagar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();


        String[] params = {contato.getId().toString()};

        db.delete("Contatos",  "id = ?", params);
    }
}
