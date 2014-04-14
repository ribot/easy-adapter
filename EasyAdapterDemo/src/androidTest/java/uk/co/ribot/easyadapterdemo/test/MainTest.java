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
