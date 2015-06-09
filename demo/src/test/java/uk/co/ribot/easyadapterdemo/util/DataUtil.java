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

package uk.co.ribot.easyadapterdemo.util;

import java.util.Arrays;
import java.util.List;

import uk.co.ribot.easyadapterdemo.Person;
import uk.co.ribot.easyadapterdemo.R;

public final class DataUtil {

    public static List<Person> getSomePeople() {
        Person person1 = createPerson("John Snow");
        Person person2 = createPerson("Sam Tarly");
        return Arrays.asList(person1, person2);
    }

    public static Person createPerson(String name) {
        return new Person(name, "07888999777", R.drawable.ic_launcher);
    }
}
