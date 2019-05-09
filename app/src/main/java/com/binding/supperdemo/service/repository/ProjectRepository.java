package com.binding.supperdemo.service.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.binding.supperdemo.service.model.ListResult;
import com.binding.supperdemo.service.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    private ApisService apiService;
    private static ProjectRepository projectRepository;

    private ProjectRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApisService.HTTPS_API_MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApisService.class);
    }

    public synchronized static ProjectRepository getInstance() {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }

    public LiveData<List<Project>> getProjectList() {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();
        apiService.getProjectList().enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                data.setValue(response.body().stories);
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<Project> getDetails(String id) {
        final MutableLiveData<Project> data = new MutableLiveData<>();

        apiService.getProjectDetails(id).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
