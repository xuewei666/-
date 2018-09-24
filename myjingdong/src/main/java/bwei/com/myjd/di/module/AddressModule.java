package bwei.com.myjd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import bwei.com.myjd.mvp.contract.AddressContract;
import bwei.com.myjd.mvp.model.AddressModel;


@Module
public class AddressModule {
    private AddressContract.View view;

    /**
     * 构建AddressModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddressModule(AddressContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddressContract.View provideAddressView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddressContract.Model provideAddressModel(AddressModel model) {
        return model;
    }
}