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
package uk.co.ribot.easyadapter;

/**
 * This RuntimeException is thrown when an extension of {@link uk.co.ribot.easyadapter.ItemViewHolder} has not been annotated with {@link uk.co.ribot.easyadapter.annotations.LayoutId}
 */
public class LayoutIdMissingException extends RuntimeException {

    public LayoutIdMissingException() {
        super("ItemViewHolder children classes must be annotated with a layout id, please add @LayoutId(someLayoutId) ");
    }

}
