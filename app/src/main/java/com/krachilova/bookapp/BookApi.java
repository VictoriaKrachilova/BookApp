package com.krachilova.bookapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {
    @GET("/api/request.php?cmd=getArticles")
    Call<Book> getBook(@Query("type") Integer i, @Query("category") String s);
}
