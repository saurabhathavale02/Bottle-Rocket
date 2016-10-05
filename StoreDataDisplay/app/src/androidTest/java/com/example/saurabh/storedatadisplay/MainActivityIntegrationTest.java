package com.example.saurabh.storedatadisplay;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.saurabh.storedatadisplay.model.pojo.ErrorEvent;
import com.example.saurabh.storedatadisplay.model.pojo.Store;
import com.example.saurabh.storedatadisplay.model.pojo.StoreData;
import com.example.saurabh.storedatadisplay.ui.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by saurabh on 10/02/16.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntegrationTest
{

    @Rule
    public final ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    MainActivity mainActivity;

    @Test
    public void testShouldShowRecyclerViewOnNewPosts()
    {

        List<Store> storelist = new ArrayList<>();
        storelist.add(new Store("27001 US Highway 19 N","Clearwater","Dillards's","27.9898988","33761","http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/Dillards.jpeg","727-296-2242","-82.7294986","1235","FL"));

        StoreData storeData =new StoreData();

        storeData.setStores(storelist);
        EventBus.getDefault().post(storeData);

        onView(withId(R.id.rv_main)).check(matches(isDisplayed()));
        // onView(withId(R.id.error_view)).check(matches(not(isDisplayed())));

    }

    @Test
    public void testShouldShowErrorViewOnNetworkError() {

        EventBus.getDefault().post(new ErrorEvent());

       // onView(withId(R.id.error_view)).check(matches(isDisplayed()));
        onView(withText("Error while displaying data"))
                .inRoot(withDecorView(not(rule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testClickOnRecylerViewItem_showsDisplaycontent() {
        // click on first item
        onView(withId(R.id.rv_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        // should show toast message
        onView(withId(R.id.address)).check(matches(isDisplayed()));
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.cityname)).check(matches(isDisplayed()));

    }

    }


