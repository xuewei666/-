package bwei.com.myjd.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import bwei.com.myjd.di.module.AddressModule;

import com.jess.arms.di.scope.ActivityScope;

import bwei.com.myjd.mvp.ui.activity.AddressActivity;

@ActivityScope
@Component(modules = AddressModule.class, dependencies = AppComponent.class)
public interface AddressComponent {
    void inject(AddressActivity activity);
}