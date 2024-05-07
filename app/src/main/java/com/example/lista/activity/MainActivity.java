package com.example.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.lista.adapter.MyAdapter;
import com.example.lista.model.MainAcitivityViewModel;
import com.example.lista.model.MyItem;
import com.example.lista.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dettmann.pratti.rafael.lista.R;

public class MainActivity extends AppCompatActivity {

    // identificador para cada chamada para a criação dos itens
    static int NEW_ITEM_REQUEST = 1;

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

        //obtendo rv
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // view model da mainactivity é obtido
        MainAcitivityViewModel vm = new ViewModelProvider(this).get(MainAcitivityViewModel.class);
        // lista de itens é obtida do vm e enviada  para o adapter
        List<MyItem> itens = vm.getItens();


        // criado o adapter e setado no rv
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);
        //indica ao rv que não há variação de tamanho entre os itens da lista (todos têm tamanho igual)
        rvItens.setHasFixedSize(true);

        // criando um gerenciador de layout linear no rv
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        //cria uma linha que separa os itens no rv
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);

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
                Uri selectedPhotoURI = data.getData();


                try {
                    // carregando a imagem dentro de um bitmap (cópia da imagem original)
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI,100, 100);
                    // guardando a imagem na variável photo do objeto myItem
                    myItem.photo = photo;
                }
                catch (FileNotFoundException e ){
                    // exceção disparada se o arquivo da imagem não for encontrado
                    e.printStackTrace();
                }

                // obtendo o vm e guardando o novo item dentro da lista
                MainAcitivityViewModel vm = new ViewModelProvider(this).get(MainAcitivityViewModel.class);
                List<MyItem> itens = vm.getItens();
                itens.add(myItem);

                //notifica a activity que novos itens foram criados e os exibe
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}