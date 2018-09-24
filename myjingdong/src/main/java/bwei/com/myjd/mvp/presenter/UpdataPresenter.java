package bwei.com.myjd.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.Map;

import bwei.com.myjd.mvp.data.UpdataBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.UpdataContract;


@ActivityScope
public class UpdataPresenter extends BasePresenter<UpdataContract.Model, UpdataContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public UpdataPresenter(UpdataContract.Model model, UpdataContract.View rootView) {
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
    public  void getData(Map<String, Object> map){
            Observable<UpdataBean> observable = mModel.requestData(map);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<UpdataBean>() {
                        @Override
                        public void accept(UpdataBean updataBean) throws Exception {
                            mRootView.responseMag(updataBean);
                        }
                    });
        }
}
