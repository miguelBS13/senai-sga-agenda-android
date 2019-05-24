package br.senai.rn.agenda.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.senai.rn.agenda.adapters.ListaAlunoAdapter;
import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Lista de Alunos";
    private ListView listaAlunos;
    private FloatingActionButton botaoAdicionar;
    private ListaAlunoAdapter adapter;
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
        adapter.update(dao.obterTodos());
    }

    private void inicializarComponentes() {
        dao = new AlunoDAO();

        listaAlunos = findViewById(R.id.principal_activity_main_lista_de_alunos);
        botaoAdicionar = findViewById(R.id.principal_activity_main_fab_novo_aluno);

        configurarAdapter(listaAlunos);

        registerForContextMenu(listaAlunos);


    }

    private void configurarAdapter(ListView listView) {
        adapter = new ListaAlunoAdapter(this, dao.obterTodos());

        listView.setAdapter(adapter);
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
    public boolean onContextItemSelected(final MenuItem item) {
        int menuSelecionado = item.getItemId();

        if (menuSelecionado == R.id.activity_lista_aluno_menu_remover) {

            AdapterView.AdapterContextMenuInfo menuInfo
                    = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final Aluno alunoSelecionado = adapter.getItem(menuInfo.position);

            new AlertDialog.Builder(this)
                    .setTitle("Remover Aluno?")
                    .setMessage("Deseja remover esse aluno "+ alunoSelecionado.getNome()+"?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.remover(alunoSelecionado);
                            adapter.remove(alunoSelecionado);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }

        return super.onContextItemSelected(item);

    }
}