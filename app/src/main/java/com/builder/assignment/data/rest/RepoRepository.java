package com.builder.assignment.data.rest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

import com.builder.assignment.data.model.ListResponse;

public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<Response<ListResponse>> getList(String tags, int page) {
        return repoService.getList(tags, page);
    }

}
