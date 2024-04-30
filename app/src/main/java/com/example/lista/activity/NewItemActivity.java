package com.example.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dettmann.pratti.rafael.lista.R;

public class NewItemActivity extends AppCompatActivity {

    // Id para a chamada
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

        // obtêm o botão de adicionar item
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            // executado quando o botão de adicionar item é clicado
            public void onClick(View v) {
                // Mensagem se não for selecionada imagem
                if(photoSelected == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!",Toast.LENGTH_LONG).show();
                    return;

                }

                // obtendo o titulo e convertendo pra str
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                // mensagem se não houver titulo
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                // obtendo a descrição e convertendo para str
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                // mensagem se a descrição estiver vazia
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                // intent para retornar os dados abaixo para a mainactivity
                Intent i  = new Intent();
                // setando o uri da imagem dentro do intent
                i.setData(photoSelected);
                // adicionando o titulo e desc na intent
                i.putExtra("title", title);
                i.putExtra("description", description);
                // indica que há dados de retorno
                setResult(Activity.RESULT_OK, i);
                // activity finalizada
                finish();
            }
        });
    }

    @Override

    // O método entrega 3 parâmetros: requestCode, que é o int da chamada; resultCode, int que indica se o resultado
    // deu certo e data, Intent que contém os dados (a imagem) retornados
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // verifica se o requestcode a partir do id PHOTO_PICKER_RESULT
            if(requestCode == PHOTO_PICKER_REQUEST){
                // verifica se o código de sucesso
                if (resultCode == Activity.RESULT_OK){
                    // obtêm o endereço da imagem e guarda na variável photoSelected
                    photoSelected = data.getData();
                    // obtêm o objeto PhotoPreview
                    ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                    // exibe a imagem no PhotoPreview
                    imvfotoPreview.setImageURI(photoSelected);
                }
            }

        }
}