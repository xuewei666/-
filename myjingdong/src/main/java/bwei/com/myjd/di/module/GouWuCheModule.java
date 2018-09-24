package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.GouWuCheContract;
import bwei.com.myjd.mvp.model.GouWuCheModel;


@Module
public class GouWuCheModule {
    private GouWuCheContract.View view;

    /**
     * 构建GouWuCheModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GouWuCheModule(GouWuCheContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    GouWuCheContract.View provideGouWuCheView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    GouWuCheContract.Model provideGouWuCheModel(GouWuCheModel model) {
        return model;
    }
}