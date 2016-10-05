package com.example.saurabh.storedatadisplay.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.saurabh.storedatadisplay.R;
import com.example.saurabh.storedatadisplay.adapter.StoreListAdapter;
import com.example.saurabh.storedatadisplay.model.StoreDataApiImpl;
import com.example.saurabh.storedatadisplay.model.pojo.ErrorEvent;
import com.example.saurabh.storedatadisplay.model.pojo.Store;
import com.example.saurabh.storedatadisplay.model.pojo.StoreData;
import com.example.saurabh.storedatadisplay.ui.Presenter.MainPresenterImpl;
import com.example.saurabh.storedatadisplay.ui.interfaces.MainView;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements MainView,StoreListAdapter.StoreItemListener
{

    private MainPresenterImpl mPresenter;
    private RecyclerView rvStoreList;
    private StoreListAdapter storeListAdapter;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb=(ProgressBar) findViewById(R.id.progressbar);


        showProgress();

        System.out.println("oncreate");

        initRecyclerView();

        StoreDataApiImpl storedataapiimpl=new StoreDataApiImpl();
        mPresenter=new MainPresenterImpl(this,storedataapiimpl);

        mPresenter.loadStoreDataFromAPI();

    }

    private void initRecyclerView()
    {
        System.out.println("init recycleview");
        rvStoreList= (RecyclerView) findViewById(R.id.rv_main);
        rvStoreList.setHasFixedSize(true);
        rvStoreList.setLayoutManager(new LinearLayoutManager(this));
        storeListAdapter = new StoreListAdapter(this,this);
        rvStoreList.setAdapter(storeListAdapter);
    }

    @Override
    public void showProgress()
    {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress()
    {
        pb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showStoreClickedData(Store selectedstore)
    {
       // Toast.makeText(this, "dsd", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayContent.class);
        intent.putExtra("selectedstore", selectedstore);
        startActivity(intent);

    }




    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(StoreData newStoreData)
    {
        System.out.println("eventmainthread="+newStoreData.getStores().get(0).getCity());
        hideError();
        hideProgress();
        storeListAdapter.addStores(newStoreData.getStores());
    }

    public void onEventMainThread(ErrorEvent errorEvent)
    {
        hideProgress();
        showError();
    }

    private void hideError()
    {
        System.out.println("hide error");
        rvStoreList.setVisibility(View.VISIBLE);
        //errorView.setVisibility(View.GONE);
    }

    private void showError()
    {
        System.out.println(" error");
        Toast.makeText(MainActivity.this,"Error while displaying data",Toast.LENGTH_SHORT).show();
        ;
    }

    @Override
    public void onStoreClick(Store item)
    {
        mPresenter.clickStoreItem(item);
    }
}
