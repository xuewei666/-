package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.FenLeiContract;
import bwei.com.myjd.mvp.model.FenLeiModel;


@Module
public class FenLeiModule {
    private FenLeiContract.View view;

    /**
     * 构建FenLeiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FenLeiModule(FenLeiContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    FenLeiContract.View provideFenLeiView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    FenLeiContract.Model provideFenLeiModel(FenLeiModel model) {
        return model;
    }
}