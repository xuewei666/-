package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.FaXianModule;

import com.jess.arms.di.scope.FragmentScope;

import bwei.com.myjd.mvp.ui.fragment.FaXianFragment;

@FragmentScope
@Component(modules = FaXianModule.class, dependencies = AppComponent.class)
public interface FaXianComponent {
    void inject(FaXianFragment fragment);
}