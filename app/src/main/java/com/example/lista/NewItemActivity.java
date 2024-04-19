package com.example.lista;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dettmann.pratti.rafael.lista.R;

public class NewItemActivity extends AppCompatActivity {


    static int PHOTO_PICKER_REQUEST = 1;

    //variável que guarda o endereço da imagem, e não o arquivo em si
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item);

        //Obtendo o botão que adiciona imagens
        ImageButton imgCl = findViewById(R.id.imbCl);
        imgCl.setOnClickListener(new View.OnClickListener() {

            //executado quando o botão é clicado
            @Override
            public void onClick(View v) {
                // Abrir a página de seleção de imagens (galeria) a partir de um intent implícito
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                // Selecionar o tipo de arquivo desejado a partir do mimetype "image/*"
                photoPickerIntent.setType("image/*");
                // Inicia a activity da galeria, tendo como retorno a imagem selecionada
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);
            }
        });
    }

    @Override

    // O método entrega 3 parâmetros: requestCode, que é o int da chamada; resultCode, int que indica se o resultado
    // deu certo e data, que contém os dados (a imagem) retornados
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PHOTO_PICKER_REQUEST){
                if (resultCode == Activity.RESULT_OK){
                    photoSelected = data.getData();
                    ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                    imvfotoPreview.setImageURI(photoSelected);
                }
            }

        }
}