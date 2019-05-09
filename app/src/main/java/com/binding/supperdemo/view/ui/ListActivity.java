package com.binding.supperdemo.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.binding.supperdemo.R;
import com.binding.supperdemo.databinding.ActivityMainListBinding;
import com.binding.supperdemo.service.model.Project;
import com.binding.supperdemo.view.adapter.ProjectAdapter;
import com.binding.supperdemo.view.callback.ProjectClickCallback;
import com.binding.supperdemo.viewmodel.ListViewModel;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    ActivityMainListBinding binding;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_list);
        projectAdapter = new ProjectAdapter(projectClickCallback);
        binding.projectList.setAdapter(projectAdapter);
        binding.projectList.setLayoutManager(new LinearLayoutManager(this));

        ListViewModel projectViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        projectViewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if (projects != null) {
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }

    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = new Intent(ListActivity.this,DetailActivity.class);
                intent.putExtra("id",project.id);
                startActivity(intent);
            }

        }
    };
}
