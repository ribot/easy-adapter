package uk.co.ribot.easyadapterdemo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ivan on 14/02/2014.
 */
public class DataProvider {

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
