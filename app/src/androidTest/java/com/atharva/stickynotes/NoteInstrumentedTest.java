package com.atharva.stickynotes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class NoteInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    // 测试1：应用包名是否正确
    @Test
    public void appContext_packageNameIsCorrect() {
        Context context = ApplicationProvider.getApplicationContext();
        assertEquals("com.atharva.stickynotes", context.getPackageName());
    }

    // 测试2：SharedPreferences 能否正常写入和读取
    @Test
    public void sharedPreferences_writeAndRead_success() {
        Context context = ApplicationProvider.getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences("NotePrefs", Context.MODE_PRIVATE);

        // 写入测试数据
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("test_key", "test_value");
        editor.apply();

        // 读取并验证
        String value = prefs.getString("test_key", "");
        assertEquals("test_value", value);

        // 清理
        editor.remove("test_key").apply();
    }

    // 测试3：MainActivity 能正常启动（ActivityTestRule 已启动，能走到这里说明成功）
    @Test
    public void mainActivity_launchesSuccessfully() {
        // 只要 ActivityTestRule 没抛异常，就说明 Activity 能正常启动
        assertNotNull(activityRule.getActivity());
    }
}