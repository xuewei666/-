package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.AddressContract;
import bwei.com.myjd.mvp.data.DiZhiBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@ActivityScope
public class AddressModel extends BaseModel implements AddressContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AddressModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<DiZhiBean> requestData(String uid) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<DiZhiBean> diZhiData = infoService.getDiZhiData(uid);
        return diZhiData;
    }
}