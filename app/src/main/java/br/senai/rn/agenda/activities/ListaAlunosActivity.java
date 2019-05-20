package br.senai.rn.agenda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Lista de Alunos";
    private ListView listaAlunos;
    private FloatingActionButton botaoAdicionar;
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_acitvity);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        definirEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.obterTodos()));
    }

    private void inicializarComponentes() {
        listaAlunos = findViewById(R.id.principal_activity_main_lista_de_alunos);
        botaoAdicionar = findViewById(R.id.principal_activity_main_fab_novo_aluno);
    }

    private void definirEventos() {
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                startActivity(intent);
            }
        });

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado  = dao.obterTodos().get(position);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                intent.putExtra("aluno", alunoSelecionado);
                startActivity(intent);
            }
        });
    }

}