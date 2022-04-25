package com.example.retrofit.contract;

import com.example.retrofit.base.BasePresenter;
import com.example.retrofit.base.BaseView;

public class AppContract {
    public static class AppPersonter extends BasePresenter<IAppView>{

    }

    public interface IAppView extends BaseView{

    }
}
