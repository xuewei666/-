package bwei.com.myjd.mvp.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jack Lee on 2018/8/24.
 * name:Juck Lee
 */

public class DingDanBean implements Serializable {
    @Override
    public String toString() {
        return "DingDanBean{" +
                "price=" + price +
                ", iconurl='" + iconurl + '\'' +
                ", title='" + title + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

            private double price=55.5;

            private String iconurl="123";

            private String title ="123 ";

            private String zhuangtai="代付款";

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZhuangtai() {
            return zhuangtai;
        }

        public void setZhuangtai(String zhuangtai) {
            this.zhuangtai = zhuangtai;
        }

        public DingDanBean(double price, String iconurl, String title, String zhuangtai) {
            this.price = price;
            this.iconurl = iconurl;
            this.title = title;
            this.zhuangtai = zhuangtai;
        }

        public DingDanBean() {

        }

        public DingDanBean(double price, String iconurl, String title) {
            this.price = price;
            this.iconurl = iconurl;
            this.title = title;
        }

    }

