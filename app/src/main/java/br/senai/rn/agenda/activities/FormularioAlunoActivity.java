package br.senai.rn.agenda.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDAO dao;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        inicializarAluno();
        definirEventos();
    }

    private void inicializarAluno() {
        dao = new AlunoDAO();
        if (getIntent().hasExtra("aluno")){
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            setTitle("Editar Aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void inicializarComponentes() {
        campoNome = findViewById(R.id.activity_formulario_aluno_et_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_et_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_et_email);
    }

    private void definirEventos() {}

    private void criarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void salvarAluno() {
        dao.salvar(aluno);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuSelecionado = item.getItemId();

        if (menuSelecionado == R.id.activity_formulario_aluno_menu_salvar){
            criarAluno();
            salvarAluno();
        }

        return super.onOptionsItemSelected(item);
    }
}
