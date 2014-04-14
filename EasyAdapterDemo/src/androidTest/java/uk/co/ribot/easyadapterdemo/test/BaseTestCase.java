package uk.co.ribot.easyadapterdemo.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public abstract class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {
    public Solo solo;

    public BaseTestCase(Class cls) {
        super(cls);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        try {
            solo.finishOpenedActivities();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        super.tearDown();
    }

}

