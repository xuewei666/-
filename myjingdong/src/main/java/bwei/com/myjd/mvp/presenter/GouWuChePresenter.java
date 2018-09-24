package bwei.com.myjd.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import bwei.com.myjd.mvp.data.ShippingBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.GouWuCheContract;


@FragmentScope
public class GouWuChePresenter extends BasePresenter<GouWuCheContract.Model, GouWuCheContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public GouWuChePresenter(GouWuCheContract.Model model, GouWuCheContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    @SuppressLint("CheckResult")
    public  void getGUCData(String uid){
        Observable<ShippingBean> observable = mModel.requestGUCData(uid);
        if (observable!=null){
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ShippingBean>() {
                        @Override
                        public void accept(ShippingBean shippingBean) throws Exception {
                            mRootView.responseGUCData(shippingBean);
                        }
                    });
        }



    }
}
