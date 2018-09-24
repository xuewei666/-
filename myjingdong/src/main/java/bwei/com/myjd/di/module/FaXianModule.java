package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.FaXianContract;
import bwei.com.myjd.mvp.model.FaXianModel;


@Module
public class FaXianModule {
    private FaXianContract.View view;

    /**
     * 构建FaXianModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FaXianModule(FaXianContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    FaXianContract.View provideFaXianView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    FaXianContract.Model provideFaXianModel(FaXianModel model) {
        return model;
    }
}