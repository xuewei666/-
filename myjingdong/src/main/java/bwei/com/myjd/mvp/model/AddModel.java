package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Map;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.AddContract;
import bwei.com.myjd.mvp.data.TJDZBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@ActivityScope
public class AddModel extends BaseModel implements AddContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AddModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TJDZBean> requestData(Map<String, String> map) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<TJDZBean> tjdzData = infoService.getTJDZData(map);
        return tjdzData;
    }
}