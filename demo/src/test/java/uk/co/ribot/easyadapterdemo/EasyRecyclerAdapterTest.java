/*
 * Copyright (C) 2015 Ribot Ltd.
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

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import uk.co.ribot.easyadapter.BuildConfig;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;
import uk.co.ribot.easyadapterdemo.util.DefaultConfig;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static uk.co.ribot.easyadapterdemo.util.DataUtil.createPerson;
import static uk.co.ribot.easyadapterdemo.util.DataUtil.getSomePeople;

/**
 * This test is in the demo app so it can use the layout resources defined for the PersonViewHolder
 * Ideally it should be in the library module because is testing the EasyRecylerAdapter class.
 * However in order to create an EasyAdapter we need a view holder annotated with a
 * valid layout ID. At the moment it's not possible to define a resource layout in the test variant
 * and I didn't want to include resources in the library that are only used for testing.
 */

@RunWith(RobolectricGradleTestRunner.class)
// Needs to include package name in config here because of this issue
// https://github.com/robolectric/robolectric/issues/1623
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK,
        packageName = DefaultConfig.PACKAGE_NAME)
public class EasyRecyclerAdapterTest {

    public EasyRecyclerAdapter<Person> mEasyRecyclerAdapter;
    public RecyclerView.AdapterDataObserver mMockAdapterDataObserver;

    @Before public void setUp() {
        mEasyRecyclerAdapter = new EasyRecyclerAdapter<>(
                RuntimeEnvironment.application,
                PersonViewHolder.class);
        mMockAdapterDataObserver = mock(RecyclerView.AdapterDataObserver.class);
        mEasyRecyclerAdapter.registerAdapterDataObserver(mMockAdapterDataObserver);
    }

    @Test public void testGetItemCount() throws Exception {
        mEasyRecyclerAdapter.getItems().addAll(Arrays.asList(
                createPerson("Person1"),
                createPerson("Person2")));
        assertEquals(2, mEasyRecyclerAdapter.getItemCount());
    }

    @Test public void testGetItem() throws Exception {
        Person person1 = createPerson("Person1");
        Person person2 = createPerson("Person2");
        mEasyRecyclerAdapter.getItems().addAll(Arrays.asList(person1, person2));
        assertEquals(person1, mEasyRecyclerAdapter.getItem(0));
        assertEquals(person2, mEasyRecyclerAdapter.getItem(1));
    }

    @Test public void testGetItems() throws Exception {
        List<Person> list = getSomePeople();
        EasyRecyclerAdapter<Person> easyRecyclerAdapter = new EasyRecyclerAdapter<>(
                RuntimeEnvironment.application,
                PersonViewHolder.class,
                list);
        assertEquals(list, easyRecyclerAdapter.getItems());
    }

    @Test public void testSetItems() throws Exception {
        List<Person> list = getSomePeople();
        assertEquals(0, mEasyRecyclerAdapter.getItems().size());
        mEasyRecyclerAdapter.setItems(list);
        assertEquals(list, mEasyRecyclerAdapter.getItems());
        verify(mMockAdapterDataObserver).onChanged();
    }

    @Test public void testSetItemsWithoutNotifying() throws Exception {
        List<Person> list = getSomePeople();
        assertEquals(0, mEasyRecyclerAdapter.getItems().size());
        mEasyRecyclerAdapter.setItemsWithoutNotifying(list);
        assertEquals(list, mEasyRecyclerAdapter.getItems());
        verify(mMockAdapterDataObserver, never()).onChanged();
    }

    @Test public void testAddItem() throws Exception {
        mEasyRecyclerAdapter.getItems().addAll(getSomePeople());
        Person newPerson = createPerson("New Person");
        mEasyRecyclerAdapter.addItem(newPerson);

        int indexOfItem = mEasyRecyclerAdapter.getItems().indexOf(newPerson);
        assertTrue(indexOfItem > 0);
        verify(mMockAdapterDataObserver).onItemRangeInserted(indexOfItem, 1);
    }

    @Test public void testRemoveItem() throws Exception {
        List<Person> items = getSomePeople();
        Person personToRemove = items.get(0);
        mEasyRecyclerAdapter.getItems().addAll(items);
        boolean result = mEasyRecyclerAdapter.removeItem(personToRemove);

        assertTrue(result);
        assertFalse(mEasyRecyclerAdapter.getItems().contains(personToRemove));
        verify(mMockAdapterDataObserver).onItemRangeRemoved(0, 1);
    }

    @Test public void testRemoveNonExistingItem() throws Exception {
        Person person = createPerson("Person1");
        boolean result = mEasyRecyclerAdapter.removeItem(person);

        assertFalse(result);
        verify(mMockAdapterDataObserver, never()).onChanged();
        verify(mMockAdapterDataObserver, never()).onItemRangeRemoved(anyInt(), anyInt());
    }

    @Test public void testAddItems() throws Exception {
        Person person1 = createPerson("Person1");
        Person person2 = createPerson("Person2");
        Person person3 = createPerson("Person3");
        mEasyRecyclerAdapter.getItems().add(person1);
        List<Person> listToAdd = Arrays.asList(person2, person3);
        boolean result = mEasyRecyclerAdapter.addItems(listToAdd);

        assertTrue(result);
        assertEquals(3, mEasyRecyclerAdapter.getItemCount());
        assertTrue(mEasyRecyclerAdapter.getItems().contains(person2));
        assertTrue(mEasyRecyclerAdapter.getItems().contains(person3));
        verify(mMockAdapterDataObserver).onItemRangeInserted(1, listToAdd.size());
    }

    @Test public void testRemoveItems() throws Exception {
        Person person1 = createPerson("Person1");
        Person person2 = createPerson("Person2");
        Person person3 = createPerson("Person3");
        mEasyRecyclerAdapter.getItems().addAll(Arrays.asList(person1, person2, person3));
        boolean result = mEasyRecyclerAdapter.removeItems(Arrays.asList(person1, person3));

        assertTrue(result);
        assertEquals(1, mEasyRecyclerAdapter.getItemCount());
        assertFalse(mEasyRecyclerAdapter.getItems().contains(person1));
        assertTrue(mEasyRecyclerAdapter.getItems().contains(person2));
        assertFalse(mEasyRecyclerAdapter.getItems().contains(person3));
        verify(mMockAdapterDataObserver).onChanged();
    }

    @Test public void testRemoveNonExistingItems() throws Exception {
        Person person1 = createPerson("Person1");
        Person person2 = createPerson("Person2");
        boolean result = mEasyRecyclerAdapter.removeItems(Arrays.asList(person1, person2));

        assertFalse(result);
        verify(mMockAdapterDataObserver, never()).onChanged();
        verify(mMockAdapterDataObserver, never()).onItemRangeRemoved(anyInt(), anyInt());
    }

}
