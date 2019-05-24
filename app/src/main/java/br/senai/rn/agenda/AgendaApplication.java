package br.senai.rn.agenda;

import android.app.Application;

import br.senai.rn.agenda.dao.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CriarAlunosDeTeste();
    }

    private void CriarAlunosDeTeste() {
        AlunoDAO dao =new AlunoDAO();

        dao.salvar(new Aluno("Miguel", "998998876", "adsfkafdja@gmail.com"));
        dao.salvar(new Aluno("Marcos", "3453535", "sla@gmail.com"));
        dao.salvar(new Aluno("Wesley", "35353535", "isso@gmail.com"));
    }


}
