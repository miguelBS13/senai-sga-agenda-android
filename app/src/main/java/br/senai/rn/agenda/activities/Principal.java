package br.senai.rn.agenda.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.senai.rn.agenda.dao.AlunoDAO;

public class Principal extends AppCompatActivity {

    private android.support.design.widget.FloatingActionButton botao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_acitvity);
        setContentView(R.layout.principal_acitvity);
        setTitle("Lista de Alunos");

        final AlunoDAO dao = new AlunoDAO();
        botao = findViewById(R.id.principal_activity_main_fab_novo_aluno);

        ListView listaAlunos = (ListView) findViewById(R.id.principal_activity_main_lista_de_alunos);

        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.obterTodos()
        ));

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Principal.this, FormularioAlunoActivity.class));
            }
        });
    }

}
