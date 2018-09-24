package bwei.com.myjd.mvp.model.api.service;

import java.util.Map;

import bwei.com.myjd.mvp.data.DiZhiBean;
import bwei.com.myjd.mvp.data.FenLeiBean;
import bwei.com.myjd.mvp.data.LoginBean;
import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.RegisterBean;
import bwei.com.myjd.mvp.data.SPXQBean;
import bwei.com.myjd.mvp.data.ShippingBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.data.SouSuoBean;
import bwei.com.myjd.mvp.data.TJDZBean;
import bwei.com.myjd.mvp.data.TJGWCBean;
import bwei.com.myjd.mvp.data.UpdataBean;
import io.reactivex.Observable;
import kotlin.jvm.internal.Ref;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface InfoService {
    @GET("ad/getAd")
    Observable<LunBoUserBean> getLunBoData();

    @GET("product/getCatagory")
    Observable<ShouYeFLBean> getSYFLData();

    @GET("product/getProductCatagory")
    Observable<FenLeiBean> getFenLeiData(@Query("cid") int id);

    @GET("user/reg?")
    Observable<RegisterBean> getRegisterData(@QueryMap Map<String,String> querymap);

    @GET("user/login?")
    Observable<LoginBean> getLoginData(@QueryMap Map<String,String> querymap);


    @GET("product/getCarts?")
    Observable<ShippingBean> getGWCData(@Query("uid") String uid);


    @GET("product/searchProducts?")
    Observable<SouSuoBean> getSouSuoData(@QueryMap Map<String,String> map);
    //https://www.zhaoapi.cn/pid=57

    @GET("product/getProductDetail?")
    Observable<SPXQBean> getSPXQData(@Query("pid") String uid);
    //https://www.zhaoapi.cn/product/addCart
    @GET("product/addCart?")
    Observable<TJGWCBean> getTJGWCData(@QueryMap Map<String,String> querymap);
//    https://www.zhaoapi.cn/user/getAddrs?uid=71
    @GET("user/getAddrs?")
    Observable<DiZhiBean> getDiZhiData(@Query("uid") String uid);
//https://www.zhaoapi.cn/user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
    @GET("user/addAddr?")
    Observable<TJDZBean> getTJDZData(@QueryMap Map<String,String> querymap);
    //https://www.zhaoapi.cn/user/updateAddr?uid=71&addrid=2
    @GET("user/updateAddr?")
    Observable<UpdataBean> getUpdataData(@QueryMap Map<String,Object> querymap);
}
