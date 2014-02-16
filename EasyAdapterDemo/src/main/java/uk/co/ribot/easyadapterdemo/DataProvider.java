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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ivan on 14/02/2014.
 */
public class DataProvider {

    //Returns a mock List of Person objects
    public static List<Person> getListPeople() {
        List<Person> listPeople = new ArrayList<Person>();
        listPeople.add(new Person("Ivan Carballo", "07123456789", R.drawable.homer1));
        listPeople.add(new Person("Antony Ribot", "07123456789", R.drawable.homer2));
        listPeople.add(new Person("Jerome Ribot", "07123456789", R.drawable.homer3));
        listPeople.add(new Person("Ben Breckler", "07123456789", R.drawable.homer1));
        listPeople.add(new Person("Lindsay Scott", "07123456789", R.drawable.homer2));
        listPeople.add(new Person("Matt Oakes", "07123456789", R.drawable.homer3));
        listPeople.add(new Person("Olly Thomas", "07123456789", R.drawable.homer1));
        listPeople.add(new Person("Rob Douglas", "07123456789", R.drawable.homer2));
        listPeople.add(new Person("Tara Etherington", "07123456789", R.drawable.homer3));
        listPeople.add(new Person("Trevor May", "07123456789", R.drawable.homer1));
        listPeople.add(new Person("Jason Fry", "07123456789", R.drawable.homer2));
        listPeople.add(new Person("Manuel Marcos", "07123456789", R.drawable.homer3));
        listPeople.add(new Person("Stefan Person", "07123456789", R.drawable.homer1));
        listPeople.add(new Person("Joe Birch", "07123456789", R.drawable.homer2));
        return listPeople;
    }
}
