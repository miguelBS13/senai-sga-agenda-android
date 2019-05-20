package br.senai.rn.agenda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.senai.rn.agenda.models.Aluno;

public class AlunoDAO {

    private final static List<Aluno> ALUNOS = new ArrayList<>(Arrays.asList(new Aluno("Miguel","99657-4618","miguelbarbosa1309@gmail.com")));

    public void salva(Aluno aluno){
        ALUNOS.add(aluno);
    }

    public List<Aluno> obterTodos() {
        return new ArrayList<>(ALUNOS);
    }
}
