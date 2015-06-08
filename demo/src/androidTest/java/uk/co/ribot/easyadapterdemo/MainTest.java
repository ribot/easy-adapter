/*
 * Copyright (C) 2014 Ribot Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.ribot.easyadapterdemo;

import android.support.test.espresso.contrib.RecyclerViewActions;

import java.util.List;

import uk.co.ribot.easyadapterdemo.util.BaseTestCase;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static uk.co.ribot.easyadapterdemo.util.CustomViewActions.clickOnChild;

public class MainTest extends BaseTestCase<MainActivity> {

    public MainTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    //Tests that every single name on the ListView is being displayed.
    public void testListViewPeople() {
        List<Person> listPeople = DataProvider.getMockPeopleSet1();
        for (int position = 0; position < listPeople.size(); position++) {
            onData(is(instanceOf(Person.class)))
                    .inAdapterView(withId(R.id.list_view))
                    .atPosition(position)
                    .onChildView(withText(listPeople.get(position).getName()))
                    .check(matches(isDisplayed()));
        }
    }

    //Tests that the click listener works on every item of the ListView
    public void testListViewClickOnImages() {
        List<Person> listPeople = DataProvider.getMockPeopleSet1();
        for (int position = 0; position < listPeople.size(); position++) {
            onData(is(instanceOf(Person.class)))
                    .inAdapterView(withId(R.id.list_view))
                    .atPosition(position)
                    .onChildView(withId(R.id.image_view_person))
                    .perform(click());
            //Check Dialog shows and close it
            onView(withText(getActivity().getString(R.string.my_name_string, listPeople.get(position).getName())))
                    .check(matches(isDisplayed()));
            onView(withText(R.string.ok))
                    .perform(click());
        }
    }

    //Tests that every single name on the RecyclerView is being displayed.
    public void testRecyclerViewPeople() {
        List<Person> listPeople = DataProvider.getMockPeopleSet2();
        onView(withText(getActivity().getString(R.string.tab_2_name)))
                .perform(click());
        for (int position = 0; position < listPeople.size(); position++) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            onView(withText(listPeople.get(position).getName()))
                    .check(matches(isDisplayed()));
        }
    }

    //Tests that the click listener works on every item of the RecyclerView.
    public void testRecyclerViewClickOnImages() {
        List<Person> listPeople = DataProvider.getMockPeopleSet2();
        onView(withText(getActivity().getString(R.string.tab_2_name)))
                .perform(click());
        for (int position = 0; position < listPeople.size(); position++) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(position, clickOnChild(R.id.image_view_person)));
            //Check Dialog shows and close it
            onView(withText(getActivity().getString(R.string.my_name_string, listPeople.get(position).getName())))
                    .check(matches(isDisplayed()));
            onView(withText(R.string.ok))
                    .perform(click());
        }
    }

}
