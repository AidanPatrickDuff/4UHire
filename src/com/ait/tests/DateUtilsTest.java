package com.ait.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.ait.beans.DateUtils;

public class DateUtilsTest {
	DateUtils dateUtil = new DateUtils();

	@Test
	public void testNullDate() {
		Date date = null;
		assertEquals("", DateUtils.formatDate(date));
	}

	@Test
	public void testValidDate() {
		Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
		Date myDate = myCalendar.getTime();
		
		assertEquals("Thursday, August 25, 2016", DateUtils.formatDate(myDate));
	}

}
