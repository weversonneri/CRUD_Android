package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_nome;
    EditText editText_telefone;
    EditText editText_email;

    Contato contato;
    ContatoDAO contatoDAO;

    String acao;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_nome = findViewById(R.id.editText_nome);
        editText_telefone = findViewById(R.id.editText_telefone);
        editText_email = findViewById(R.id.editText_email);

        contatoDAO = new ContatoDAO(RegisterActivity.this);

        Intent intent = getIntent();
        contato = (Contato) intent.getSerializableExtra("contato");

        if (contato != null) {
            editText_nome.setText(contato.getNome());
            editText_telefone.setText(contato.getTelefone());
            editText_email.setText(contato.getEmail());
            id = contato.getId();
            acao = "update";
        } else {
            acao = "insert";

        }
    }

    public void salvar (View view) {
        contato = new Contato();
        contato.setNome(editText_nome.getText().toString());
        contato.setTelefone(editText_telefone.getText().toString());
        contato.setEmail(editText_email.getText().toString());

        if (acao.equals("update")) {
            contato.setId(id);
            contatoDAO.alterar(contato);
        } else {
            contatoDAO.adicionar(contato);
        }

        Toast.makeText(RegisterActivity.this, "Contato " + contato.getNome() + "salvo!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void apagar(View view) {
        contatoDAO.apagar(contato);

        Toast.makeText(RegisterActivity.this, contato.getNome() + " Apagado!", Toast.LENGTH_LONG).show();
        finish();

    }
}
