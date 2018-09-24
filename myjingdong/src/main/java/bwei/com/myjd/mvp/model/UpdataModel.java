package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Map;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.UpdataContract;
import bwei.com.myjd.mvp.data.UpdataBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@ActivityScope
public class UpdataModel extends BaseModel implements UpdataContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public UpdataModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<UpdataBean> requestData(Map<String, Object> map) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<UpdataBean> updataData = infoService.getUpdataData(map);
        return updataData;
    }
}