package net.mundotela.cpf.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import net.mundotela.cpf.R;
import net.mundotela.cpf.controller.PessoaController;
import net.mundotela.cpf.model.Pessoa;

import java.util.List;

/**
 * Created by Henrique on 19/06/2017.
 */

public abstract class PessoaUtil {

    public static void verificaValorCampo(Context ctx,int id,EditText nome, EditText cpf,EditText idade, EditText tel, EditText email ){
        //verifica se todos os campos foram preenchidos
        if (nome.getText().length() > 0 && cpf.getText().length() > 0 && idade.getText().length() > 0 && tel.getText().length() > 0 && email.getText().length() > 0){

            Pessoa p = new Pessoa();//cria um novo objeto pessoa
            //pega o valor dos campos do formulario e instancia o objeto pessoa
            p.setId(id);
            p.setNome(nome.getText().toString());
            p.setCpf(cpf.getText().toString());
            p.setIdade(idade.getText().toString());
            p.setTelefone(tel.getText().toString());
            p.setEmail(email.getText().toString());

            PessoaController pc = new PessoaController();//cria um novo controller
            if(pc.salvar(ctx,p) > 0){//Salva e verifica se o registro foi salvo
                mensagem(ctx,"Cadastro","Registro salvo com sucesso!\n" +
                        "\nNome : " + p.getNome()+
                        "\nCPF : " + p.getCpf()+
                        "\nIdade : " + p.getIdade()+ " anos" +
                        "\nTel : " + p.getTelefone()+
                        "\nE-mail : " + p.getEmail()

                );
                limpaCampos(nome,cpf,idade,tel,email);//limpa todos os campos
                nome.requestFocus();//da o foco no campo nome
            }else {
                mensagem(ctx,"Algo deu errado...","Erro ao salvar no banco!");
            }
        }else {

            mensagem(ctx,"Alerta","Todos os campos devem ser preenchidos!");
        }

    }

    private static void mensagem(Context ctx,String titulo, String mensagem) {
        //exibe uma mensagem
        AlertDialog.Builder alerta = new AlertDialog.Builder(ctx);
        alerta.setIcon(R.drawable.cpf);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.show();

    }

    public static void escolha(final Context ctx, final String texto, final List<String> pessoas,final  ArrayAdapter<String> adapter){
        String registro = "";//metodo cria um AlertDialog, carrega o registro pergunta sim ou não para apagar , e atializa a lista
        PessoaController pc = new PessoaController();
        for (Pessoa p : pc.findbyNome(ctx,texto)){//percorre os registros devolvendo objetos pessoa
            registro =
                        p.getNome()//adiona os valores do registro no texto do AD
                        + "\n" + p.getIdade() + " anos"
                        + "\n" + p.getCpf()
                        + "\n" + p.getTelefone()
                        + "\n" + p.getEmail();

        }
        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);// cria o AD
        adb.setTitle("Deseja apagar este registro ?");
        adb.setMessage(registro);
        adb.setNegativeButton("Não",null);
        adb.setPositiveButton("Sim", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PessoaController pc = new PessoaController();
                pc.deleteNome(ctx,texto);//deleta o registro pelo nome
                //metodo para atualizar a lista
                pessoas.clear();
                for (Pessoa p : pc.findbyNome(ctx,"")){//percorre os registros devolvendo objetos pessoa
                    pessoas.add(p.getNome());//adiciona o nome na lista

                }
                adapter.notifyDataSetChanged();//atualiza a listview
            }
        });
        adb.show();// mostra o AD

    }

    private static void limpaCampos(EditText nome, EditText cpf,EditText idade, EditText tel, EditText email){
        //limpa todos os campos do formulario
        String limpar = "";

        nome.setText(limpar);
        cpf.setText(limpar);
        idade.setText(limpar);
        tel.setText(limpar);
        email.setText(limpar);
    }

    public static void mudaLista(Context context,List<String> pessoas, ArrayAdapter<String> adapter, String e, PessoaController pc){
        //metodo para atualizar a lista
        pessoas.clear();
        for (Pessoa p : pc.findbyNome(context,"")){//percorre os registros devolvendo objetos pessoa
            pessoas.add(p.getNome());//adiciona o nome na lista

        }
        adapter.notifyDataSetChanged();//atualiza a listview
    }


}
