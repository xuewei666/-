package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.GouWuCheModule;

import com.jess.arms.di.scope.FragmentScope;

import bwei.com.myjd.mvp.ui.fragment.GouWuCheFragment;

@FragmentScope
@Component(modules = GouWuCheModule.class, dependencies = AppComponent.class)
public interface GouWuCheComponent {
    void inject(GouWuCheFragment fragment);
}