package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.WoDeModule;

import com.jess.arms.di.scope.FragmentScope;

import bwei.com.myjd.mvp.ui.fragment.WoDeFragment;

@FragmentScope
@Component(modules = WoDeModule.class, dependencies = AppComponent.class)
public interface WoDeComponent {
    void inject(WoDeFragment fragment);
}