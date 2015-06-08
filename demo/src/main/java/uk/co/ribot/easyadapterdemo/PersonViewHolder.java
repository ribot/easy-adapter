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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * The extension of ItemViewHolder for the ListView of Person.
 */

//Annotate the class with the layout ID of the item.
@LayoutId(R.layout.person_item_layout)
public class PersonViewHolder extends ItemViewHolder<Person> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.
    @ViewId(R.id.image_view_person)
    ImageView imageViewPerson;

    @ViewId(R.id.text_view_name)
    TextView textViewName;

    @ViewId(R.id.text_view_phone)
    TextView textViewPhone;

    //Extend ItemViewHolder and call super(view)
    public PersonViewHolder(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(Person person, PositionInfo positionInfo) {
        imageViewPerson.setImageResource(person.getResDrawableId());
        textViewName.setText(person.getName());
        textViewPhone.setText(person.getPhoneNumber());
    }

    //Optionally override onSetListeners to add listeners to the views.
    @Override
    public void onSetListeners() {
        imageViewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonHolderListener listener = getListener(PersonHolderListener.class);
                if (listener != null) {
                    listener.onPersonImageClicked(getItem());
                }
            }
        });
    }

    public interface PersonHolderListener {
        void onPersonImageClicked(Person person);
    }
}
