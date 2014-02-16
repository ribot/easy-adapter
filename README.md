EasyAdapter For Android
===========

Using `AdapterViews` has never been so easy. Inpired by the [view holder](http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder) design pattern, this library provides an easier way of linking `AdapterViews` and the underlying data for that view without having to implement your own Adapter. The `EasyAdapter` will do the tedious work for you. 

###### Why to use EasyAdapter?
* __Simpler than implementing your own Adapter__. You just have to extend `ItemViewHolder` and use annotations to link your code to views and layouts. See examples below or demo app. 
* __Ensure performace__. It reuses the view holders so it helps your ListViews scroll smoothly. 
* __Cleaner code__. By keeping the view fields inside the view holders your code becomes cleaner an more understable. 

EasyAdapter supports Android 2.1 and above.

Downloads
--------------

* __[easyAdapter_v1.0.jar](http://ribot.co.uk)__

Examples
--------------

### ListView with EasyAdapter

This example shows how to implent a `ListView` that displays people's data. Every item on the list is a person with an image, name and phone number. The item's layout is `person_item_layout.xml` and it contains an `ImageView` and two `TextViews`. The `Person` class contains data about a person. 

#### 1. Extend ItemViewHolder 

```java
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
}
```
#### 2. Create the EasyAdapter and add it to your ListView

```java
/*
Create an EasyAdapter by passing a Context, your ItemViewHolder class and the list of items.
Alternatively, you can create an EasyAdapter only with a Context and an ItemViewHolder class and set
the list of items later on.
*/
mListView.setAdapter(new EasyAdapter<Person>(this, PersonViewHolder.class, DataProvider.getListPeople()));
```
See demo app for a full working example. 

### Other uses of View Holders. 

View holders are very convenient with adapters, but they can also be useful to hold the views of Fragments and Activities. For this reason, this library provides the abstract classes `ViewHolder` and `ActivityViewHolder` that can be used in the same way as the `ItemViewHolder`. 

#### ActivityViewHolder example

```java
public class MainActivity extends Activity {

    MainActivityViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the view holder. 
        mViewHolder = new MainActivityViewHolder(this);
        // Access the views
        mViewHolder.textViewTitle.setText("Some title");

    }
    
    //Extend ActivityViewHolder and annotate the view fields
    static class MainActivityViewHolder extends ActivityViewHolder {
    
        @ViewId(R.id.text_view_title)
        TextView textViewTitle
        
        @ViewId(R.id.list_view_people)
        ListView listViewPeople;
    
        public MainActivityViewHolder(Activity activity) {
            super(activity);
        }
    
    }
}
```
This will keep your code tidy and you won't have to use `findViewById(id)`. Use `ViewHolder` for Fragments. 

Build with Gradle
--------------
##### The demo app 
```
./gradlew :easyadapterdemo:assembleDebug
```
##### The library 
```
./gradlew :easyadapter:assembleDebug
```


License
--------------

    Copyright 2014 Ribot Ltd.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
