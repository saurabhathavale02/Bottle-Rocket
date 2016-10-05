package com.example.saurabh.storedatadisplay.ui.Presenter;

import android.util.Log;

import com.example.saurabh.storedatadisplay.model.StoreDataApiImpl;
import com.example.saurabh.storedatadisplay.model.pojo.ErrorEvent;
import com.example.saurabh.storedatadisplay.model.pojo.Store;
import com.example.saurabh.storedatadisplay.model.pojo.StoreData;
import com.example.saurabh.storedatadisplay.ui.interfaces.MainPresenterCalls;
import com.example.saurabh.storedatadisplay.ui.interfaces.MainView;

import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by saurabh on 10/02/16.
 */

public class MainPresenterImpl implements MainPresenterCalls
{

    public MainView mView;
    public StoreDataApiImpl storeapiimpl;

    public MainPresenterImpl(MainView view, StoreDataApiImpl storeapi)
    {
        this.mView = view;
        this.storeapiimpl = storeapi;
    }
    @Override
    public void loadStoreDataFromAPI()
    {

        storeapiimpl.getPostsObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread())
                .subscribe(new Subscriber<StoreData>()
                {

                    @Override
                    public void onNext(StoreData storedata)
                    {
                        EventBus.getDefault().post(storedata);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.e("GithubDemo", e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent());
                    }

                });

    }


    @Override
    public void clickStoreItem(Store item)
    {
        mView.showStoreClickedData(item);
    }

}
