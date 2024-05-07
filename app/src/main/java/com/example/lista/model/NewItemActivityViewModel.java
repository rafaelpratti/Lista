package com.example.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

// classe que armazena os dados de newItemActivity (o endereço da imagem)
public class NewItemActivityViewModel extends ViewModel {
 Uri selectPhotoLocation = null;

        public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
        }

        public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
        }
}

