package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.SPXQContract;
import bwei.com.myjd.mvp.model.SPXQModel;


@Module
public class SPXQModule {
    private SPXQContract.View view;

    /**
     * 构建SPXQModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SPXQModule(SPXQContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SPXQContract.View provideSPXQView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SPXQContract.Model provideSPXQModel(SPXQModel model) {
        return model;
    }
}