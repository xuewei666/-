package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.LoginActivitysModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.LoginActivitysActivity;

@ActivityScope
@Component(modules = LoginActivitysModule.class, dependencies = AppComponent.class)
public interface LoginActivitysComponent {
    void inject(LoginActivitysActivity activity);
}