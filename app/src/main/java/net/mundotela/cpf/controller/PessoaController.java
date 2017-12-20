package net.mundotela.cpf.controller;

import android.content.Context;
import net.mundotela.cpf.dao.PessoaDao;
import net.mundotela.cpf.model.Pessoa;
import java.util.List;


/**
 * Created by Henrique on 19/06/2017.
 */

public class PessoaController {//controladores para manipular o banco e retornar dados através da classe DAO


    public PessoaController() {// cronstrutor vazio
    }

    public long salvar(Context context, Pessoa p) {//metodo usado  para salvar
        return new PessoaDao(context).save(p);
    }

    public List<Pessoa> findbyNome(Context context, String nome) {//lista registro pesquisando por nome
        return new PessoaDao(context).findAllByNome(nome);
    }

    public int deleteNome(Context context,String nome){// apaga o registro que retornarem com a busca por nome
        return new PessoaDao(context).deletebyNome(nome);
    }


}
