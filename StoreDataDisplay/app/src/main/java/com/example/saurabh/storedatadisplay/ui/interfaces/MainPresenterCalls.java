package com.example.saurabh.storedatadisplay.ui.interfaces;

import com.example.saurabh.storedatadisplay.model.pojo.Store;

/**
 * Created by saurabh on 10/02/16.
 */

public interface MainPresenterCalls
{
    void loadStoreDataFromAPI();
    void clickStoreItem(Store item);
}
