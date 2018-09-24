package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.SouSuoModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.SouSuoActivity;

@ActivityScope
@Component(modules = SouSuoModule.class, dependencies = AppComponent.class)
public interface SouSuoComponent {
    void inject(SouSuoActivity activity);
}