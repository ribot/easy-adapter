package uk.co.ribot.easyadapterdemo;

import android.database.DataSetObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import uk.co.ribot.easyadapter.BuildConfig;
import uk.co.ribot.easyadapter.EasyAdapter;
import uk.co.ribot.easyadapterdemo.util.DefaultConfig;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class EasyAdapterTest {

    public EasyAdapter<Person> mEasyAdapter;
    public DataSetObserver mMockDataSetObserver;

    @Before public void setUp() {
        mEasyAdapter = new EasyAdapter<>(RuntimeEnvironment.application, PersonViewHolder.class);
        mMockDataSetObserver = mock(DataSetObserver.class);
        mEasyAdapter.registerDataSetObserver(mMockDataSetObserver);
    }

    @Test public void testSetItems() throws Exception {
        List<Person> list = getSomeData();
        assertEquals(0, mEasyAdapter.getItems().size());
        mEasyAdapter.setItems(list);
        assertEquals(list, mEasyAdapter.getItems());
        verify(mMockDataSetObserver).onChanged();
    }

    @Test public void testSetItemsWithoutNotifying() throws Exception {
        List<Person> list = getSomeData();
        assertEquals(0, mEasyAdapter.getItems().size());
        mEasyAdapter.setItemsWithoutNotifying(list);
        assertEquals(list, mEasyAdapter.getItems());
    }

    @Test public void testGetItems() throws Exception {
        List<Person> list = getSomeData();
        EasyAdapter<Person> easyAdapter = new EasyAdapter<>(RuntimeEnvironment.application,
                PersonViewHolder.class, list);
        assertEquals(list, easyAdapter.getItems());
    }

    @Test public void testAddItem() throws Exception {
        mEasyAdapter.getItems().addAll(getSomeData());
        Person newPerson = createPerson("New Person");
        mEasyAdapter.addItem(newPerson);

        assertTrue(mEasyAdapter.getItems().contains(newPerson));
        verify(mMockDataSetObserver).onChanged();
    }

    private List<Person> getSomeData() {
        Person person1 = createPerson("John Snow");
        Person person2 = createPerson("Sam Tarly");
        return Arrays.asList(person1, person2);
    }

    private Person createPerson(String name) {
        return new Person(name, "07888999777", R.drawable.ic_launcher);
    }

}
