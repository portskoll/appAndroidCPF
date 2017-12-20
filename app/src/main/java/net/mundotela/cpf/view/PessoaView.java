//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Henrique Kolle Portella
//******************************************************

package net.mundotela.cpf.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import net.mundotela.cpf.R;
import net.mundotela.cpf.util.PessoaUtil;

public class PessoaView extends AppCompatActivity {

    private EditText nome, cpf, idade, tel, email;
    private int _id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_view);
        nome = (EditText) findViewById(R.id.edtNome);
        cpf = (EditText) findViewById(R.id.edt_cpf);
        idade = (EditText) findViewById(R.id.edtIdade);
        tel = (EditText) findViewById(R.id.edtTelefone);
        email = (EditText) findViewById(R.id.edtEmail);
        getSupportActionBar().setTitle("Cadastro");//escreve titulo na actionbar
        getSupportActionBar().setSubtitle("Pessoas Físicas");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// cria o menu na actionBar
        getMenuInflater().inflate(R.menu.menu_pessoa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//aciona os itens de menu

        int id = item.getItemId();//pega o item de menu

        if (id == R.id.salvar) {

            PessoaUtil.verificaValorCampo(this, _id, nome, cpf, idade, tel, email);// salva ou atuliza registros e exibe um alerta

        }
        if (id == R.id.listar) {
            startActivity(new Intent(this,PessoaListaView.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
