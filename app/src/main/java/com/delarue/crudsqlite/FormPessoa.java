package com.delarue.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.delarue.crudsqlite.dao.PessoaDao;
import com.delarue.crudsqlite.modelo.Pessoa;

public class FormPessoa extends AppCompatActivity {

    EditText editNome, editIdade, editEndereco, editTelefone;
    Button btnVariavel;
    Pessoa pessoa, altpessoa;
    PessoaDao pessoaDao;
    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pessoa);

        Intent i = getIntent();
        altpessoa = (Pessoa) i.getSerializableExtra("pessoa-enviada");
        pessoa = new Pessoa();
        pessoaDao = new PessoaDao(FormPessoa.this);

        editNome = findViewById(R.id.editNome);
        editIdade = findViewById(R.id.editIdade);
        editEndereco = findViewById(R.id.editEndereco);
        editTelefone = findViewById(R.id.editTelefone);
        btnVariavel = findViewById(R.id.btnVariavel);


        if (altpessoa != null){

            btnVariavel.setText("Alterar");

        }else {

            btnVariavel.setText("Salvar");

        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pessoa.setNome(editNome.getText().toString());
                pessoa.setIdade(Integer.parseInt(editIdade.getText().toString()));
                pessoa.setEndereco(editEndereco.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());

                if (btnVariavel.getText().toString().equals("Salvar")){

                    retornoDB = pessoaDao.salvarPessoa(pessoa);

                    if (retornoDB == -1){

                        alert ("Erro Ao Cadastrar");
                    }else {

                        alert ("Cadastro Realizado!");
                    }

                }else {


                }

                finish();

            }

            private void alert(String s) {

               //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

              Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
