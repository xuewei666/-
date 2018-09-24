package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.SouSuo2Contract;
import bwei.com.myjd.mvp.model.SouSuo2Model;


@Module
public class SouSuo2Module {
    private SouSuo2Contract.View view;

    /**
     * 构建SouSuo2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SouSuo2Module(SouSuo2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SouSuo2Contract.View provideSouSuo2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SouSuo2Contract.Model provideSouSuo2Model(SouSuo2Model model) {
        return model;
    }
}