package uk.co.ribot.easyadapterdemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by ivan on 14/02/2014.
 */

@LayoutId(R.layout.person_item_layout)
public class PersonViewHolder extends ItemViewHolder<Person> {

    @ViewId(R.id.image_view_person)
    ImageView imageViewPerson;

    @ViewId(R.id.text_view_name)
    TextView textViewName;

    @ViewId(R.id.text_view_phone)
    TextView textViewPhone;

    public PersonViewHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Person person, PositionInfo positionInfo) {
        imageViewPerson.setImageResource(person.getResDrawableId());
        textViewName.setText(person.getName());
        textViewPhone.setText(person.getPhoneNumber());
    }
}
