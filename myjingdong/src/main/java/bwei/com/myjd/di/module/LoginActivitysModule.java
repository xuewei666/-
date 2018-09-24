package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.LoginActivitysContract;
import bwei.com.myjd.mvp.model.LoginActivitysModel;


@Module
public class LoginActivitysModule {
    private LoginActivitysContract.View view;

    /**
     * 构建LoginActivitysModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginActivitysModule(LoginActivitysContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginActivitysContract.View provideLoginActivitysView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginActivitysContract.Model provideLoginActivitysModel(LoginActivitysModel model) {
        return model;
    }
}