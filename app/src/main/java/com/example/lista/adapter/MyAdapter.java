package com.example.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista.activity.MainActivity;
import com.example.lista.activity.MyItem;

import java.util.List;
import dettmann.pratti.rafael.lista.R;

public class MyAdapter extends RecyclerView.Adapter{

    MainActivity mainActivity;
    List<MyItem> itens;

    // construtor do adapter
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }


    @NonNull
    @Override
    // cria os elementos de interface para os itens, que são guardados em um viewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // obtêm um inflador de layouts, lendo o arquivo xml do item e criando os elementos de interface do mesmo
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    // recebe o ViewHolder criado por onCreateViewHolder e preenche os elementos de interface com os dados do item
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView;
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.photo);
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    @Override
    // retorna a quantidade de itens a serem adicionados
    public int getItemCount(){
        return itens.size();
    }
}
