package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.MainModule;

import com.jess.arms.di.scope.FragmentScope;

import bwei.com.myjd.mvp.ui.fragment.MainFragment;

@FragmentScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainFragment fragment);
}