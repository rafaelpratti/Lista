package com.example.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dettmann.pratti.rafael.lista.R;

public class MainActivity extends AppCompatActivity {

    // identificador para cada chamada para a criação dos itens
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
    // adapter para a lista
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtendo o botão de adicionar itens
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {

            // Executado quando clicado
            @Override
            public void onClick(View v) {
                // Navegar para a outra activity a partir de um intent explícito
                // A MainActivity, ao navegar para a NewItemActivity, espera dados do item cadastrado
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // verificação do retorno
        if(requestCode == NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){

                // criando objeto da classe myitem
                MyItem myItem =  new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();
                // adicionando na lista
                itens.add(myItem);
            }
        }
    }
}