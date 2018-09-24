package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.UpdataModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.UpdataActivity;

@ActivityScope
@Component(modules = UpdataModule.class, dependencies = AppComponent.class)
public interface UpdataComponent {
    void inject(UpdataActivity activity);
}