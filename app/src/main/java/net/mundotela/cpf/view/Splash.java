//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Henrique Kolle Portella
//******************************************************

package net.mundotela.cpf.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;


import net.mundotela.cpf.R;

public class Splash extends AppCompatActivity implements  Runnable{// tela de abertura da aplicação

    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();// esconde a actionBar
        h = new Handler();
        h.postDelayed(this,2000);// demora dois segundos para executar o metodo run()

    }

    @Override
    public void run() {

        Intent it = new Intent(this,PessoaView.class);
        startActivity(it);// abre a tela de cadastro
        finish();// chama o metodo ondestroy desta activity

    }
}
