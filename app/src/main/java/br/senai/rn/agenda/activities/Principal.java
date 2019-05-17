package br.senai.rn.agenda.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_acitvity);
        setContentView(R.layout.principal_acitvity);
        setTitle("Lista de Alunos");

        List<String> alunos = new ArrayList<String>();
        alunos.add("Miguel");
        alunos.add("Ohana");
        alunos.add("Juliana");

        ListView listaAlunos = (ListView) findViewById(R.id.principal_activity_main_lista_de_alunos);

        listaAlunos.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                alunos
        ));
    }

}
