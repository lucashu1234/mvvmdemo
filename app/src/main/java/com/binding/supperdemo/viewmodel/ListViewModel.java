package com.binding.supperdemo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.binding.supperdemo.service.model.Project;
import com.binding.supperdemo.service.repository.ProjectRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;

    public ListViewModel(Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = ProjectRepository.getInstance().getProjectList();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
}
