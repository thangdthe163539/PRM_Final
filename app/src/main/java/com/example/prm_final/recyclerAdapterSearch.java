package com.example.prm_final;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapterSearch extends RecyclerView.Adapter<recyclerAdapterSearch.ViewHolder> {
    private ArrayList<Film> mFilmList;
    private Context mContext;

    public recyclerAdapterSearch(ArrayList<Film> filmList, Context context) {
        mFilmList = filmList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Film film = mFilmList.get(position);
        holder.filmName.setText(film.getName());
        holder.filmImage.setImageResource(Integer.parseInt(film.getImage()));
        holder.itemView.setOnClickListener(v -> onClickFilmDetail(film));
    }

    private void onClickFilmDetail(Film film) {
        DatabaseFilm db = new DatabaseFilm(mContext, null, null, 1);
        db.updateView(Integer.toString(film.getId()), film.getViews()+1);
        Intent intent = new Intent(mContext, FilmDetail.class);
        intent.putExtra("film", film);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mFilmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filmName;
        ImageView filmImage;

        public ViewHolder(final View view) {
            super(view);
            filmName = view.findViewById(R.id.filmNameInList);
            filmImage = view.findViewById(R.id.filmImageTrending);
        }
    }
}
