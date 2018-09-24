package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.FenLeiModule;

import com.jess.arms.di.scope.FragmentScope;

import bwei.com.myjd.mvp.ui.fragment.FenLeiFragment;

@FragmentScope
@Component(modules = FenLeiModule.class, dependencies = AppComponent.class)
public interface FenLeiComponent {
    void inject(FenLeiFragment fragment);
}