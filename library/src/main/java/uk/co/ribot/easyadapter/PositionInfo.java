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
 * This class holds information about the position of an item in the {@link uk.co.ribot.easyadapter.EasyAdapter}
 */
public class PositionInfo {

    private int mPosition;
    private boolean mFirst;
    private boolean mLast;

    /**
     * Constructs an empty PositionInfo object
     */
    public PositionInfo() {

    }

    /**
     * Constructs a PositionInfo with: position, first and last
     *
     * @param position the position of the item
     * @param first    true if the item is the first
     * @param last     true if the item is the last
     */

    public PositionInfo(int position, boolean first, boolean last) {
        setPosition(position);
        setFirst(first);
        setLast(last);
    }

    /**
     * @return position of the item in the {@link uk.co.ribot.easyadapter.EasyAdapter}
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * @return true if the item is the first one in the {@link uk.co.ribot.easyadapter.EasyAdapter}
     */
    public boolean isFirst() {
        return mFirst;
    }

    /**
     * @return true if the item is the last one in the {@link uk.co.ribot.easyadapter.EasyAdapter}
     */
    public boolean isLast() {
        return mLast;
    }

    /**
     * Sets the position of the item
     *
     * @param position position of the item
     */
    public void setPosition(int position) {
        mPosition = position;
    }

    /**
     * Sets whether the item is the first one or not
     *
     * @param first true if the item is the first
     */
    public void setFirst(boolean first) {
        mFirst = first;
    }

    /**
     * Sets whether the item is the last one or not
     *
     * @param last true if the item is the last
     */
    public void setLast(boolean last) {
        mLast = last;
    }
}
