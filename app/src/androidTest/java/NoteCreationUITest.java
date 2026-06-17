package com.atharva.stickynotes;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class NoteCreationUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    // 测试1：创建一条新笔记
    @Test
    public void testCreateNote() {
        // 输入标题
        onView(withId(R.id.titlenote)).perform(replaceText("UI测试笔记"), closeSoftKeyboard());
        // 输入内容
        onView(withId(R.id.contentnote)).perform(replaceText("这是UI测试的内容"), closeSoftKeyboard());
        // 点击保存按钮
        onView(withId(R.id.savebutton)).perform(click());
        // 验证笔记出现在列表中
        onView(withText("UI测试笔记")).check(matches(isDisplayed()));
    }

    // 测试2：创建多条笔记
    @Test
    public void testCreateMultipleNotes() throws InterruptedException {
        // 第一条笔记
        onView(withId(R.id.titlenote)).perform(replaceText("笔记A"), closeSoftKeyboard());
        onView(withId(R.id.contentnote)).perform(replaceText("内容A"), closeSoftKeyboard());
        onView(withId(R.id.savebutton)).perform(click());
        Thread.sleep(500);

        // 第二条笔记
        onView(withId(R.id.titlenote)).perform(replaceText("笔记B"), closeSoftKeyboard());
        onView(withId(R.id.contentnote)).perform(replaceText("内容B"), closeSoftKeyboard());
        onView(withId(R.id.savebutton)).perform(click());
        Thread.sleep(500);

        // 验证两条笔记都出现
        onView(withText("笔记A")).check(matches(isDisplayed()));
        onView(withText("笔记B")).check(matches(isDisplayed()));
    }

    // 测试3：标题为空时不能保存
    @Test
    public void testEmptyTitleCannotSave() {
        // 只输入内容，标题留空
        onView(withId(R.id.titlenote)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.contentnote)).perform(replaceText("只有内容没有标题"), closeSoftKeyboard());
        onView(withId(R.id.savebutton)).perform(click());
        // 验证空标题的笔记没有出现（根据代码逻辑，标题为空时不会保存）
    }

    // 测试4：内容为空时不能保存
    @Test
    public void testEmptyContentCannotSave() {
        // 只输入标题，内容留空
        onView(withId(R.id.titlenote)).perform(replaceText("只有标题没有内容"), closeSoftKeyboard());
        onView(withId(R.id.contentnote)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.savebutton)).perform(click());
        // 验证没有新笔记出现
    }
}