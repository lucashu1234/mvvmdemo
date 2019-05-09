package com.binding.supperdemo.service.repository;


import com.binding.supperdemo.service.model.ListResult;
import com.binding.supperdemo.service.model.Project;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ApisService {
    String HTTPS_API_MAIN_URL = "https://news-at.zhihu.com/api/4/news/";

    @GET("latest")
    Call<ListResult> getProjectList();

    @GET("{id}")
    Call<Project> getProjectDetails(@Path("id") String id);
}
