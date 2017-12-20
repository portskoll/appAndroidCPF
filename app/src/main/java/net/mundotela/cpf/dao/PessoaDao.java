package net.mundotela.cpf.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import net.mundotela.cpf.model.Pessoa;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Henrique on 19/06/2017.
 */

public class PessoaDao extends SQLiteOpenHelper {
    private static final String TAG = "sql_Pessoas";
    private static final String NOME_BANCO = "pessoas.sqlite";
    private static final int VERSAO_BANCO = 1;




    public PessoaDao(Context context) {
        super(context, NOME_BANCO, null,VERSAO_BANCO);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "criando a tabela pessoa");
        db.execSQL("create table if not exists pessoa (_id integer primary key autoincrement," +
                "nome text, cpf text,idade text,telefone text, email text);");
        Log.d(TAG, "A tabela pessoa foi criada");


    }

    //metodo insert e update
    public long save(Pessoa p){

        long id = p.getId();
        SQLiteDatabase db = getWritableDatabase();//usa o banco no modo de escrita

        try {
            ContentValues values = new ContentValues();//adciona valores na tabela passados pelo model pessoa
            values.put("nome",p.getNome());
            values.put("cpf",p.getCpf());
            values.put("idade",p.getIdade());
            values.put("telefone",p.getTelefone());
            values.put("email",p.getEmail());

            if (id != 0){//faz o update quando o id não é zero

                String _id = String.valueOf(p.getId());// pega o id e trasnforma em string
                String[] whereArgs = new String[] {_id};
                //update pessoa set values = ... where _id=?
                int count = db.update("pessoa", values, "_id=?", whereArgs);
                return count;

            }else{// salva o registro
                //insert into pessoa values (...)
                id = db.insert("pessoa","",values);
                return id;//retorna o id do registro

            }

        }finally {
            db.close();//fecha a conexão com o banco
        }



    }


    //Consulta a lista de pessoas por nome
    public List<Pessoa> findAllByNome(String nome) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            //select * from pessoa where tipo=?
            Cursor c = db.query("pessoa",null,"nome like" + "'%"+nome+"%'",null,null,null,null);
            return toList(c);

        }finally {
            db.close();
        }

    }


    // Lê o cursor e cria a lista de pessoas
    private List<Pessoa> toList(Cursor c) {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        if(c.moveToFirst()){
            do {
                Pessoa pessoa = new Pessoa();
                pessoas.add(pessoa);
                //recupera os atributos de pessoa
                pessoa.setId(c.getLong(c.getColumnIndex("_id")));
                pessoa.setNome(c.getString(c.getColumnIndex("nome")));
                pessoa.setCpf(c.getString(c.getColumnIndex("cpf")));
                pessoa.setIdade( c.getString(c.getColumnIndex("idade")));
                pessoa.setTelefone(c.getString(c.getColumnIndex("telefone")));
                pessoa.setEmail(c.getString(c.getColumnIndex("email")));


            }while (c.moveToNext());
        }
        return pessoas;
    }

    //Deleta Pessoa
    public int deletebyNome(String nome) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            //delete from pessoa where nome=?
            int count = db.delete("pessoa","nome=?",new String[]{nome});
            Log.i(TAG, "Deletou [" + count + "] registro");
            return count;
        }finally {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //usado  quando fazer alteracoes no banco
    }
}
