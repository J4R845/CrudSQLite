package com.delarue.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
    boolean dadosValidados;


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


        if (altpessoa != null) {

            btnVariavel.setText("Alterar");

            editNome.setText(altpessoa.getNome());
            editIdade.setText(altpessoa.getIdade() + "");
            editEndereco.setText(altpessoa.getEndereco());
            editTelefone.setText(altpessoa.getTelefone());

            pessoa.setId(altpessoa.getId());


        } else {

            btnVariavel.setText("Salvar");

        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    pessoa.setNome(editNome.getText().toString());
                    pessoa.setIdade(Integer.parseInt(editIdade.getText().toString()));
                    pessoa.setEndereco(editEndereco.getText().toString());
                    pessoa.setTelefone(editTelefone.getText().toString());

                    dadosValidados = validarCampos();

                    if (dadosValidados) {

                        Log.i("CrudSQLite", "Testando App");


                        if (btnVariavel.getText().toString().equals("Salvar")) {

                            retornoDB = pessoaDao.salvarPessoa(pessoa);
                            pessoaDao.close();

                            if (retornoDB == -1) {

                                alert("Erro Ao Cadastrar");
                            } else {

                                alert("Cadastro Realizado!");
                            }

                        } else {

                            retornoDB = pessoaDao.alterarPessoa(pessoa);
                            pessoaDao.close();

                            if (retornoDB == -1) {
                                alert("Erro Ao Atualizar Os Dados");

                            } else {

                                alert("Os Dados Foram Atualizados");
                            }

                        }

                        finish();

                    }


                } catch (Exception e) {


                }

            }

            private void alert(String s) {

                //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

            }
        });

    }

    //Inicio Valida Campos

    private boolean validarCampos() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editNome.getText().toString())
                || editNome.getText().toString().trim().isEmpty()) {
            retorno = false;
            editNome.setError("Digite O Nome!");
            editNome.requestFocus();
        } else if (TextUtils.isEmpty(editEndereco.getText().toString())
                || editEndereco.getText().toString().trim().isEmpty()) {
            retorno = false;
            editIdade.setError("Digite A Idade!");
            editIdade.requestFocus();
        } else if (TextUtils.isEmpty(editEndereco.getText().toString())
                || editEndereco.getText().toString().trim().isEmpty()) {
            retorno = false;
            editEndereco.setError("Digite O Endereço!");
            editEndereco.requestFocus();

        } else if (TextUtils.isEmpty(editTelefone.getText().toString())
                || editTelefone.getText().toString().trim().isEmpty()) {
            retorno = false;
            editTelefone.setError("Digite O Telefone!");
            editTelefone.requestFocus();
        }

        return retorno;
    }

    //Fim Valida Campos

}