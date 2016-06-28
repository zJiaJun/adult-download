package com.github.zjiajun.adult;

import com.github.zjiajun.adult.pic.PicApp;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/26 12:59
 */
public class PicAppTest {

    private static PicApp picApp;
    private static final String GET_ROSIXZ_PAGE_INFO = "getRosixzPageInfo";

    @BeforeClass
    public static void setPicApp() {
        picApp = new PicApp();
    }

    @Test
    public void shouldEqualsPageCount() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRosixzPageInfoMethod = PicApp.class.getDeclaredMethod(GET_ROSIXZ_PAGE_INFO);
        getRosixzPageInfoMethod.setAccessible(true);
        Object value = getRosixzPageInfoMethod.invoke(picApp);
        Map<Integer, String> pageInfo = (Map<Integer, String>) value;
        assertEquals(pageInfo.size(),22);
    }

    @Test
    public void shouldEqualsPageInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRosixzPageInfoMethod = PicApp.class.getDeclaredMethod(GET_ROSIXZ_PAGE_INFO);
        getRosixzPageInfoMethod.setAccessible(true);
        Object value = getRosixzPageInfoMethod.invoke(picApp);
        Map<Integer, String> pageInfo = (Map<Integer, String>) value;
        assertEquals(pageInfo.get(1),"http://www.rosixz.com/page-1.html");
    }

    @Test
    public void shouldEqualsDetailImgName() {
        String normal = "http://www.rosixz.com/photo2/320/rosimm-320-001.jpg";
        String unnoram = "http:/www.rosixz.com/photo/177/ROSI_177_001.jpg";
        normal = normal.substring(normal.lastIndexOf("/") + 1,normal.length());
        unnoram = unnoram.substring(unnoram.lastIndexOf("/") + 1,unnoram.length());
        assertEquals(normal,"rosimm-320-001.jpg");
        assertEquals(unnoram,"ROSI_177_001.jpg");
    }

}
