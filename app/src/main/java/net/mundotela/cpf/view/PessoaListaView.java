package net.mundotela.cpf.view;


import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.mundotela.cpf.R;
import net.mundotela.cpf.controller.PessoaController;
import net.mundotela.cpf.model.Pessoa;
import net.mundotela.cpf.util.PessoaUtil;
import java.util.ArrayList;
import java.util.List;


public class PessoaListaView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listaPessoas;
    private List<String> pessoas = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_lista_view);
        getSupportActionBar().setTitle("Lista");//escreve titulo na actionbar
        getSupportActionBar().setSubtitle("Burcar por nome");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//cria o icone voltar
        PessoaController pc = new PessoaController();
        listaPessoas = (ListView) findViewById(R.id.ListViewPessoas);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pessoas);//adaptador para carregar a lista
        listaPessoas.setAdapter(adapter);//coloca o conteudo na listView
        listaPessoas.setOnItemClickListener(this);//listView vai usar o metodo de click no item da lista
        PessoaUtil.mudaLista(getApplicationContext(),pessoas,adapter,"",pc);// atualiza a lista
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//cria o menu na actiobar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_pessoa, menu);
        //cria o icone de busca e campo de texto para digitar busca  e atualiza a lista
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {// busca pelo nome não esta sendo usado
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {//busca pelo nome conforme ele muda
                PessoaController pc = new PessoaController();
                pessoas.clear();//apaga toda a lista
                for (Pessoa p : pc.findbyNome(getApplicationContext(),newText)){//percorre os registros devolvendo objetos pessoa
                    pessoas.add(p.getNome());//adiciona o nome na lista

                }
                adapter.notifyDataSetChanged();//atualiza a listview
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//aciona os itens de menu

        int id = item.getItemId();//pega o item de menu


        if (id == android.R.id.home) {
            startActivity(new Intent(this,PessoaView.class));// volta para tela de cadastro
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            s = (String) adapterView.getAdapter().getItem(i);// pega o nome do item da lista
            PessoaUtil.escolha(PessoaListaView.this,s,pessoas,adapter);//retorna uma mensagem de escolha para apagar um registro
        //e atualiza a lista de nomes


    }
}
