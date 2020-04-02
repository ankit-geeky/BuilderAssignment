package com.builder.assignment.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.builder.assignment.ui.main.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();
}
