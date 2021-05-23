package com.krachilova.bookapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Datum> mData;//список данных, которые будем помещать в RecyclerView
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // передаём данные в конструктор
    public MyRecyclerViewAdapter(Context context, List<Datum> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // “создает(надувает)” строку(пункт) RecyclerView из xml файла
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout, parent, false);
        return new ViewHolder(view);
    }

    // заполняет каждую строк RecyclerView данными
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Datum datum =  mData.get(position);
        holder.title.setText(datum.getTitle());
        holder.author.setText(datum.getAuthor());
        holder.oldPrice.setText(datum.getOldPrice() + "$");
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.newPrice.setText(datum.getNewPrice() + "$");
        new ImageDownloader(holder.image).execute("https://www.ebooksbilliger.de/" + datum.getImageUrlHd());

    }

    // общее количество строк
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // сохраняет и реиспользует view компоненты, когда строка прокручивается(уходит с экрана)
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        TextView author;
        TextView oldPrice;
        TextView newPrice;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            newPrice = itemView.findViewById(R.id.newPrice);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    String getItem(int id) {
        return mData.get(id).getAuthor();
    }

    // добавление возможности перехата нажатия на кнопку
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //  Activity будет реализовывать этот метод, щелчек по элементу
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}