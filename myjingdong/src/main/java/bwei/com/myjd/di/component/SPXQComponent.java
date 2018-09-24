package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.SPXQModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.SPXQActivity;

@ActivityScope
@Component(modules = SPXQModule.class, dependencies = AppComponent.class)
public interface SPXQComponent {
    void inject(SPXQActivity activity);
}