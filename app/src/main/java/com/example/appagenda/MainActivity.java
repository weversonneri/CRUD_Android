package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Contato> list;
    ArrayAdapter<Contato> adapter;

    ContatoDAO contatoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        contatoDAO = new ContatoDAO(MainActivity.this);
        carregaList();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Contato contato = (Contato) listView.getItemAtPosition(position);
                Intent intentEdit = new Intent(MainActivity.this, RegisterActivity.class);

                intentEdit.putExtra("contato", contato);
                startActivity(intentEdit);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaList();
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void carregaList() {
        list = contatoDAO.buscaContato();
        adapter = new ArrayAdapter<Contato>(MainActivity.this, R.layout.text_size, list);
        listView.setAdapter(adapter);
    }


}
