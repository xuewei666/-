package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.HomeContract;
import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@FragmentScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    @Override
    public Observable<LunBoUserBean> responseData() {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<LunBoUserBean> observable = infoService.getLunBoData();
        return observable;
    }

    @Override
    public Observable<ShouYeFLBean> responseFLData() {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<ShouYeFLBean> observable = infoService.getSYFLData();
        return observable;
    }
}