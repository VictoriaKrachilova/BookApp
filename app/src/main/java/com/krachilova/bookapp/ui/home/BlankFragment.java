package com.krachilova.bookapp.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krachilova.bookapp.Book;
import com.krachilova.bookapp.BookApi;
import com.krachilova.bookapp.BookDB;
import com.krachilova.bookapp.BookEntity;
import com.krachilova.bookapp.MyRecyclerViewAdapter;
import com.krachilova.bookapp.R;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";
    private static final String ARG_PARAM2 = "param2";
    private int index;
    private String categories;

    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance(int param, String categories) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, param);
        args.putString(ARG_PARAM2, categories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM);
            categories = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.ebooksbilliger.de")//базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create())//конвертер
                .build();
        RoomDatabase database= Room.databaseBuilder(getContext(), BookDB.class, "books").// название файла
                fallbackToDestructiveMigration()
                .build();
        BookApi bookApi;
        bookApi=retrofit.create(BookApi.class);
        bookApi.getBook(index, categories).
                enqueue(new Callback<Book>() {//aссинхронный вызов (для синхронного был бы метод execute() )
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.isSuccessful()) {
                            adapter = new MyRecyclerViewAdapter(getContext(), response.body().getData());
                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @Override
                                public void run() {//кладём данные в БД
                                    for(int i=0;i<10;i++) {
                                        BookEntity bookEntity = new BookEntity();
                                        bookEntity.id = i;
                                        bookEntity.title = response.body().getData().get(i).getOldPrice();
                                        bookEntity.oldPrice = response.body().getData().get(i).getOldPrice();
                                        bookEntity.newPrice = response.body().getData().get(i).getNewPrice();
                                        bookEntity.author = response.body().getData().get(i).getAuthor();
                                        bookEntity.dateTime = "now";
                                        ((BookDB) database).bookDao().insertBook(bookEntity);
                                    }

                                }
                            });
                            recyclerView.setAdapter(adapter);
                            Log.i("Jane", "OK");
                        } else Log.i("Jane", "no reponse");
                    }
                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Log.i("Jane","Failure "+t);
                    }
                });


    }
}