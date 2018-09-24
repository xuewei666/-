package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.AddModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.AddActivity;

@ActivityScope
@Component(modules = AddModule.class, dependencies = AppComponent.class)
public interface AddComponent {
    void inject(AddActivity activity);
}