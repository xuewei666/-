package bwei.com.myjd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.FenLeiContract;
import bwei.com.myjd.mvp.data.FenLeiBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@FragmentScope
public class FenLeiModel extends BaseModel implements FenLeiContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public FenLeiModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<ShouYeFLBean> zuoData() {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<ShouYeFLBean> observable = infoService.getSYFLData();
        return observable;
    }

    @Override
    public Observable<FenLeiBean> youData(int id) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<FenLeiBean> fenLeiData = infoService.getFenLeiData(id);

        return fenLeiData;
    }
}