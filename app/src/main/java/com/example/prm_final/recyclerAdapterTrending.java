package com.example.prm_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapterTrending extends RecyclerView.Adapter<recyclerAdapterTrending.MyViewHolder> {
    private ArrayList<Film> filmList;
    Context context;

    public recyclerAdapterTrending(ArrayList<Film> filmList, Context context) {
        this.filmList = filmList;
        this.context = context;
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder {
        Button filmName, score;
        ImageView filmImage;
        public  MyViewHolder(final View view) {
            super(view);
            filmName = view.findViewById(R.id.filmNameTreding);
            filmImage = view.findViewById(R.id.filmImageTrending);
            score = view.findViewById(R.id.scoreTrending);
        }
    }
    @NonNull
    @Override
    public recyclerAdapterTrending.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trending, parent, false);
        return  new MyViewHolder(itemVIew);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterTrending.MyViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.filmName.setText(film.getName());
        holder.filmImage.setImageResource(Integer.parseInt(film.getImage()));
        holder.score.setText(film.getScore());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGetFilmDetail(film);
            }
        });
    }
    private void onClickGetFilmDetail(Film film) {
        DatabaseFilm handler = new DatabaseFilm(context, null, null, 1);
        handler.updateView(Integer.toString(film.getId()), film.getViews()+1);
        Intent intent = new Intent(context, FilmDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("film", film);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
