package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.WoDeContract;
import bwei.com.myjd.mvp.model.WoDeModel;


@Module
public class WoDeModule {
    private WoDeContract.View view;

    /**
     * 构建WoDeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WoDeModule(WoDeContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    WoDeContract.View provideWoDeView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    WoDeContract.Model provideWoDeModel(WoDeModel model) {
        return model;
    }
}