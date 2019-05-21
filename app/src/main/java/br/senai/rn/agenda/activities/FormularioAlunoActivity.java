package br.senai.rn.agenda.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
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
        botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
    }

    private void definirEventos() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarAluno();
                salvarAluno();
            }
        });
    }

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
}
