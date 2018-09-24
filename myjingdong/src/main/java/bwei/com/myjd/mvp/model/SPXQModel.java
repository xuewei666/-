package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Map;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.SPXQContract;
import bwei.com.myjd.mvp.data.SPXQBean;
import bwei.com.myjd.mvp.data.TJGWCBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@ActivityScope
public class SPXQModel extends BaseModel implements SPXQContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SPXQModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<SPXQBean> requestData(String pid) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<SPXQBean> spxqData = infoService.getSPXQData(pid);
        return spxqData;
    }

    @Override
    public Observable<TJGWCBean> requestTJGWCData(Map<String, String> pid) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<TJGWCBean> observable = infoService.getTJGWCData(pid);
        return observable;
    }
}