package com.binding.supperdemo.viewmodel;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.binding.supperdemo.service.model.Project;
import com.binding.supperdemo.service.repository.ProjectRepository;

public class DetailViewModel extends AndroidViewModel {
    private final LiveData<Project> projectObservable;
    private final String projectID;

    public ObservableField<Project> project = new ObservableField<>();

    public DetailViewModel(@NonNull Application application,
                           final String projectID) {
        super(application);
        this.projectID = projectID;

        projectObservable = ProjectRepository.getInstance().getDetails(projectID);
    }

    public LiveData<Project> getObservableDetails() {
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String projectID;

        public Factory(@NonNull Application application, String projectID) {
            this.application = application;
            this.projectID = projectID;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new DetailViewModel(application, projectID);
        }
    }
}
