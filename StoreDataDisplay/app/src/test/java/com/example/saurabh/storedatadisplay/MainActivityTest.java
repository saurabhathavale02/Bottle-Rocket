package com.example.saurabh.storedatadisplay;

/**
 * Created by saurabh on 10/02/16.
 */

import com.example.saurabh.storedatadisplay.model.StoreDataApiImpl;
import com.example.saurabh.storedatadisplay.model.pojo.StoreData;
import com.example.saurabh.storedatadisplay.ui.Presenter.MainPresenterImpl;
import com.example.saurabh.storedatadisplay.ui.interfaces.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class})
public class MainActivityTest
{
    private MainPresenterImpl mainPresenterimpl;

    @Before
    public void setUp() throws Exception {
        mainPresenterimpl = spy(new MainPresenterImpl(mock(MainView.class),mock(StoreDataApiImpl.class)));

    }

    @Test
    public void testShouldSchedulePostsLoadFromAPIOnBackgroundThread() {

        //create mocks
        Observable<StoreData> mainObservable = (Observable<StoreData>) mock(Observable.class);

        //define return values
        when(mainPresenterimpl.storeapiimpl.getPostsObservable()).thenReturn(mainObservable);
        when(mainObservable.subscribeOn(Schedulers.io())).thenReturn(mainObservable);
        when(mainObservable.observeOn(AndroidSchedulers.mainThread())).thenReturn(mainObservable);

        //call test method
        mainPresenterimpl.loadStoreDataFromAPI();

        //verify if all methods in the chain are called with correct arguments

        verify(mainPresenterimpl.storeapiimpl).getPostsObservable();
        verify(mainObservable).subscribeOn(Schedulers.io());
        verify(mainObservable).observeOn(AndroidSchedulers.mainThread());
        verify(mainObservable).subscribe(Matchers.<Subscriber<StoreData>>any());

    }

}
