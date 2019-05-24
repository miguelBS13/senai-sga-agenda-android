package br.senai.rn.agenda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Lista de Alunos";
    private ListView listaAlunos;
    private FloatingActionButton botaoAdicionar;
    private ArrayAdapter<Aluno> adapter;
    private AlunoDAO dao;

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
        adapter.clear();
        adapter.addAll(dao.obterTodos());
    }

    private void inicializarComponentes() {
        dao = new AlunoDAO();

        listaAlunos = findViewById(R.id.principal_activity_main_lista_de_alunos);
        botaoAdicionar = findViewById(R.id.principal_activity_main_fab_novo_aluno);

        configurarAdapter(listaAlunos);

        registerForContextMenu(listaAlunos);

        dao.salvar(new Aluno("Miguel", "998998876", "adsfkafdja@gmail.com"));
        dao.salvar(new Aluno("Marcos", "3453535", "sla@gmail.com"));
        dao.salvar(new Aluno("Wesley", "35353535", "isso@gmail.com"));
    }

    private void configurarAdapter(ListView listView) {
        adapter = new ArrayAdapter<>(this, R.layout.item_aluno);

        listView.setAdapter (new BaseAdapter() {

            final List<Aluno> alunos = new ArrayList<>();

            @Override
            public int getCount() {
                return alunos.size();
            }

            @Override
            public Aluno getItem(int position) {
                return alunos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return alunos.get(position).getId();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                Aluno aluno = getItem(position);

                TextView nome = findViewById(R.id.item_aluno_nome);
                TextView telefone = findViewById(R.id.item_aluno_telefone);

                nome.setText(aluno.getNome());
                telefone.setText(aluno.getTelefone());

                View viewCriada = LayoutInflater
                        .from(ListaAlunosActivity.this)
                        .inflate(R.layout.item_aluno, parent);

                return viewCriada;

            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
//        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuSelecionado = item.getItemId();

        if (menuSelecionado == R.id.activity_lista_aluno_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo
                    = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
            dao.remover(alunoSelecionado);
            adapter.remove(alunoSelecionado);
        }


        return super.onContextItemSelected(item);

    }
}