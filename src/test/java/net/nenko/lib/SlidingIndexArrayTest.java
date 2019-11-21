package net.nenko.lib;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.nenko.coco.Coco;

public class SlidingIndexArrayTest {

	@Test
	public void test() {
		SlidingIndexArray<String> test1 = new SlidingIndexArray<String>(String.class, 10000);
		assertEquals("AAABBBCCC", test1.get(1));
	}

}
