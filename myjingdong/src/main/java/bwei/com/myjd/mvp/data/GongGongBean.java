package bwei.com.myjd.mvp.data;

public class GongGongBean<T> {

    private  T  value;

    public GongGongBean() {
    }

    public GongGongBean(T value) {
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
