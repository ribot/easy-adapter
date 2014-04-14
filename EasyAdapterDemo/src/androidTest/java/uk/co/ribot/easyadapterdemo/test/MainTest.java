package uk.co.ribot.easyadapterdemo.test;

import java.util.List;

import uk.co.ribot.easyadapterdemo.DataProvider;
import uk.co.ribot.easyadapterdemo.MainActivity;
import uk.co.ribot.easyadapterdemo.Person;
import uk.co.ribot.easyadapterdemo.R;

/**
 * Created by ivan on 11/04/2014.
 */
public class MainTest extends BaseTestCase<MainActivity> {

    public MainTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    //Tests that every single name on the list is being displayed.
    public void testListPeople() {
        List<Person> listPeople = DataProvider.getListPeople();
        for (Person person : listPeople) {
            assertTrue(solo.searchText(person.getName(), 1, true, true));
        }
    }

    //Tests that the click listener works on the first item of the list.
    public void testClickOnImage() {
        List<Person> listPeople = DataProvider.getListPeople();
        solo.clickOnImage(0);
        assertTrue(solo.waitForText(getActivity().getString(R.string.my_name_string, listPeople.get(0).getName())));
    }
}
