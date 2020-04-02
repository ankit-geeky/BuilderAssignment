package com.builder.assignment.data.rest;

import java.util.List;

import io.reactivex.Single;

import com.builder.assignment.data.model.ListResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoService {

    @GET("search_by_date")
    Single<Response<ListResponse>> getList(@Query("tags") String tags, @Query("page") int page);

}
