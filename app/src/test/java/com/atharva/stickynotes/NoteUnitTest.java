package com.atharva.stickynotes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteUnitTest {

    private Note note;

    @Before
    public void setUp() {
        note = new Note("测试标题", "测试内容");
    }

    // 测试1：构造函数是否正确设置标题和内容
    @Test
    public void noteConstructor_setsTitleAndContent() {
        assertEquals("测试标题", note.getTitle());
        assertEquals("测试内容", note.getContent());
    }

    // 测试2：setTitle 是否能正确修改标题
    @Test
    public void setTitle_updatesTitle() {
        note.setTitle("新标题");
        assertEquals("新标题", note.getTitle());
    }

    // 测试3：setContent 是否能正确修改内容
    @Test
    public void setContent_updatesContent() {
        note.setContent("新内容");
        assertEquals("新内容", note.getContent());
    }

    // 测试4：无参构造函数创建的对象，getTitle 和 getContent 应该为 null
    @Test
    public void emptyNote_returnsNull() {
        Note emptyNote = new Note();
        assertNull(emptyNote.getTitle());
        assertNull(emptyNote.getContent());
    }
}