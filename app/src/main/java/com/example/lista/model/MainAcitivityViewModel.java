package com.example.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


// classe para armazenar os dados da main activity (os itens já cadastrados)
public class MainAcitivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens(){
        return itens;
    }
}


