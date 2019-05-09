package com.binding.supperdemo.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.binding.supperdemo.R;
import com.binding.supperdemo.databinding.ActivityDetailBinding;
import com.binding.supperdemo.service.model.Project;
import com.binding.supperdemo.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail);

        DetailViewModel.Factory factory = new DetailViewModel.Factory(
                getApplication(), getIntent().getStringExtra("id"));

        final DetailViewModel projectViewModel = ViewModelProviders.of(this,factory).get(DetailViewModel.class);
        binding.setProjecetmodel(projectViewModel);
        projectViewModel.getObservableDetails().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(Project project) {
                projectViewModel.setProject(project);
            }
        });
    }
}
