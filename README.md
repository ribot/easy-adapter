Deprecated
===========

Due to the growing use of the [RecyclerView](http://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) and the [RecyclerView.Adapter](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html) provided adapter class, Easy-Adapter is now deprecated. Whilst the current version still remains usable, there will no longer be any new development taking place.

EasyAdapter For Android
===========

Using `ListView` and `RecyclerView` has never been so easy. Inspired by the [view holder](http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder) design pattern, this library provides an easier way of linking `ListView` and `RecyclerView` with the underlying data for that view without having to implement your own Adapter. EasyAdapter will do the tedious work for you.

###### Why to use EasyAdapter?
* __Simpler than implementing your own Adapter__. You just have to extend `ItemViewHolder` and use annotations to link your code to views and layouts. See examples below or demo app.
* __Ensure performance__. It reuses the view holders so helps your ListViews scroll smoothly.
* __Easy to switch between `ListView` and `RecyclerView`__. Once you implement your `ItemViewHolder` this can be used with both widgets. By just changing a couple of lines you can easily switch between a `ListView` and a `RecyclerView`.
* __Cleaner code__. By keeping the view fields inside the view holders your code becomes cleaner an more understandable.

EasyAdapter supports Android 2.1 and above.

Setup
--------------
#### 1. Gradle
```groovy
dependencies {
    compile 'uk.co.ribot:easyadapter:1.5.0@aar'
}
```
#### 2. Maven
```xml
<dependency>
    <groupId>uk.co.ribot</groupId>
    <artifactId>easyadapter</artifactId>
    <version>1.5.0</version>
    <type>aar</type>
</dependency>
```
#### 3. Manual

Download the __[latest Jar](https://raw.github.com/ribot/EasyAdapter/master/downloads/easyadapter-1.5.0.jar)__

Sample
--------------

This example shows how to implement a `ListView` and a `RecyclerView` that displays a list of people. Every item on the list is a person with an image, name and phone number. The item's layout is `person_item_layout.xml` and it contains an `ImageView` and two `TextViews`. The `Person` class contains data about a person.

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
If you define the `ViewHolder` as an inner class, it must be `static` so that the `EasyAdapter` can instantiate it.

#### 2. Link the view holder to your ListView or RecyclerView

##### ListView

```java
/*
Create an EasyAdapter by passing a Context, your ItemViewHolder class and the list of items.
Alternatively, you can create an EasyAdapter only with a Context and an ItemViewHolder class and set
the list of items later on.
*/
mListView.setAdapter(new EasyAdapter<Person>(
        this,
        PersonViewHolder.class,
        DataProvider.getListPeople()));
```

##### RecyclerView

```java
//Same as above but use a EasyRecyclerAdapter instead of EasyAdapter
mRecyclerView.setAdapter(new EasyRecyclerAdapter<Person>(
        this,
        PersonViewHolder.class,
        DataProvider.getListPeople()));
```

See demo app for a full working example.

### Pass a callback or listener to the view holder. 

Sometimes you need to notify your `Activity` or `Fragment` about an action that happened in your view holder, for example, you need to set the title of the `Activity` when a button that is in the view holder is clicked. From version 1.4.+ this is quite easy to implement by passing a listener or callback to the view holder through the adapter. See the example below:

##### In your Activity create an easy adapter with a listener.  
```java
//Implement the listener. 
private PersonViewHolder.PersonListener mListener = new PersonViewHolder.PersonListener() {
    public onButtonClicked(Person person) {
        setTitle(person.getName());
    }
}

//Set the listener when creating the EasyRecyclerAdapter (same for EasyAdapter)
mRecyclerView.setAdapter(new EasyRecyclerAdapter<Person>(
        this,
        PersonViewHolder.class,
        DataProvider.getListPeople(),
        mListener));
```

##### Get your listener from the ItemViewHolder
```java
@LayoutId(R.layout.person_item_layout)
public class PersonViewHolder extends ItemViewHolder<Person> {

    @ViewId(R.id.button)
    Button button;
    
    //Implement onSetListeners and set a click listener in the button.  
    @Override
    public void onSetListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get your custom listener and call the method. 
                PersonListener listener = getListener(PersonListener.class);
                if (listener != null) {
                    listener.onButtonClicked(getItem());
                }
            }
        }
    }
    
    //Define your custom interface 
    public interface PersonListener {
        public void onButtonClicked(Person person);
    }
}
```
Proguard 
--------------
If you are using Proguard you need to add the following rules to `proguard-rules.pro`:
```
-keepattributes *Annotation*
-keepclassmembers class * extends uk.co.ribot.easyadapter.ItemViewHolder {
    public <init>(...);
 }
```

Build
--------------
##### The demo app
```
./gradlew :demo:installDebug
```
##### The library
```
./gradlew :library:build
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

