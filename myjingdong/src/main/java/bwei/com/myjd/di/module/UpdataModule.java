package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.UpdataContract;
import bwei.com.myjd.mvp.model.UpdataModel;


@Module
public class UpdataModule {
    private UpdataContract.View view;

    /**
     * 构建UpdataModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UpdataModule(UpdataContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UpdataContract.View provideUpdataView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UpdataContract.Model provideUpdataModel(UpdataModel model) {
        return model;
    }
}