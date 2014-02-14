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
 * This runtime exception is thrown when the {@link uk.co.ribot.easyadapter.ItemViewHolder} extension passed into the {@link uk.co.ribot.easyadapter.EasyAdapter} is not a valid implementation
 * of {@link uk.co.ribot.easyadapter.ItemViewHolder}
 */
public class InvalidViewHolderException extends RuntimeException {

    public InvalidViewHolderException() {
        super("The ItemViewHolder class passed into the EasyAdapter is not valid, make sure it extends ItemViewHolder.");
    }

}
