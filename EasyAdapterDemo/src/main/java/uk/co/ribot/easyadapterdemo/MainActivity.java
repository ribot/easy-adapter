package uk.co.ribot.easyadapterdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import uk.co.ribot.easyadapter.EasyAdapter;

public class MainActivity extends Activity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);

        mListView.setAdapter(new EasyAdapter<Person>(this, PersonViewHolder.class, DataProvider.getListPeople()));


    }

}
