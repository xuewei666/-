package bwei.com.myjd.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.Map;

import bwei.com.myjd.mvp.data.SouSuoBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.SouSuo2Contract;


@ActivityScope
public class SouSuo2Presenter extends BasePresenter<SouSuo2Contract.Model, SouSuo2Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SouSuo2Presenter(SouSuo2Contract.Model model, SouSuo2Contract.View rootView) {
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
    public  void  getData(Map<String, String> map){
        Observable<SouSuoBean> observable = mModel.reponseData(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SouSuoBean>() {
                    @Override
                    public void accept(SouSuoBean souSuoBean) throws Exception {
                        mRootView.responseMsg(souSuoBean);
                    }
                });


    }
}
