package net.nenko.coco;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class dateParseTest {

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "2019-08-19";
		try {
			Date dDate = sdf.parse(sDate);
			assertEquals("2019-08-19", sdf.format(dDate));
		} catch(Exception e) {
			fail("E:" + e);
		}
	}

}
