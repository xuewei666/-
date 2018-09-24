package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Map;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.SouSuo2Contract;
import bwei.com.myjd.mvp.data.SouSuoBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@ActivityScope
public class SouSuo2Model extends BaseModel implements SouSuo2Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SouSuo2Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<SouSuoBean> reponseData(Map<String, String> map) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<SouSuoBean> souSuoData = infoService.getSouSuoData(map);
        return souSuoData;
    }
}