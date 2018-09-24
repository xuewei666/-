package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.SouSuo2Module;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.SouSuo2Activity;

@ActivityScope
@Component(modules = SouSuo2Module.class, dependencies = AppComponent.class)
public interface SouSuo2Component {
    void inject(SouSuo2Activity activity);
}