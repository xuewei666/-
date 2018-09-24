package bwei.com.myjd.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import bwei.com.myjd.BuildConfig;
import bwei.com.myjd.mvp.contract.GouWuCheContract;
import bwei.com.myjd.mvp.data.ShippingBean;
import bwei.com.myjd.mvp.model.api.service.InfoService;
import io.reactivex.Observable;


@FragmentScope
public class GouWuCheModel extends BaseModel implements GouWuCheContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public GouWuCheModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<ShippingBean> requestGUCData(String uid) {
        InfoService infoService = mRepositoryManager.obtainRetrofitService(InfoService.class);
        Observable<ShippingBean> gwcData = infoService.getGWCData(uid);
        Log.e("GouWuCheModel", "gwcData:" + gwcData);

            if(gwcData.equals("null")){

                return null;
            }else {
                return gwcData;
            }

    }
}