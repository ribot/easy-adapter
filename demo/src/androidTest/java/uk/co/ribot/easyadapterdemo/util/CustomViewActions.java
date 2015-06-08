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

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;

public final class CustomViewActions {

    public static ViewAction clickOnChild(int childViewId) {
        return actionWithAssertions((new ChildClickAction(
                new GeneralClickAction(Tap.SINGLE, GeneralLocation.VISIBLE_CENTER, Press.FINGER),
                childViewId)));
    }

    private static class ChildClickAction implements ViewAction {

        private int mChildViewId;
        private GeneralClickAction mGeneralClickAction;

        public ChildClickAction(GeneralClickAction generalClickAction, int childViewId) {
            mGeneralClickAction = generalClickAction;
            mChildViewId = childViewId;
        }

        @Override
        public Matcher<View> getConstraints() {
            return mGeneralClickAction.getConstraints();
        }

        @Override
        public String getDescription() {
            return mGeneralClickAction.getDescription();
        }

        @Override
        public void perform(UiController uiController, View view) {
            View viewToClick = view.findViewById(mChildViewId);
            mGeneralClickAction.perform(uiController, viewToClick != null ? viewToClick : view);
        }
    }
}
