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

/**
 * Created by ivan on 14/02/2014.
 * <p/>
 * The data item class
 */
public class Person {

    private String name;
    private String phoneNumber;
    private int resDrawableId;

    public Person() {

    }

    public Person(String name, String phoneNumber, int resDrawableId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.resDrawableId = resDrawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getResDrawableId() {
        return resDrawableId;
    }

    public void setResDrawableId(int resDrawableId) {
        this.resDrawableId = resDrawableId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (resDrawableId != person.resDrawableId) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return !(phoneNumber != null ? !phoneNumber.equals(person.phoneNumber) : person.phoneNumber != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + resDrawableId;
        return result;
    }
}
