package com.delarue.crudsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.delarue.crudsqlite.modelo.Pessoa;


public class PessoaDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "DBPessoa.db";
    private static final int VERSION = 1;
    private static final String TABELA = "pessoa";
    //Dados a armazenar
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String IDADE = "idade";
    private static final String ENDERECO = "endereco";
    private static final String TELEFONE = "telefone";


    public PessoaDao(Context context) {

        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + " ( "
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NOME + " TEXT, " + IDADE + " TEXT, " + ENDERECO + " TEXT,"
                        + TELEFONE + " TEXT ) ;";

        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS "+TABELA;

        db.execSQL(sql);

        onCreate(db);

    }

    public long salvarPessoa(Pessoa p){

        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, p.getNome());
        values.put(IDADE, p.getIdade());
        values.put(ENDERECO, p.getEndereco());
        values.put(TELEFONE, p.getTelefone());

        retornoDB = getWritableDatabase().insert(TABELA,null,values);

        return retornoDB;


    }

}
