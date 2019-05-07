package net.nenko.coco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CocoTest {

	private Coco coco;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDefCfg() {
		coco = new Coco();
		coco.setCovar("ABC");
		List<String> cfg = new ArrayList<>();
		cfg.add("123?AAA{X}BBB{Y}CCC");
		cfg.add("456?{X}AAA{Y}BBBCCC");
		cfg.add("AAABBBCCC");
		coco.setCfg(cfg);
		Map<String, String> dict = new HashMap<>();
		assertEquals("AAABBBCCC", coco.get(dict));
	}


	@Test
	public void testDefCfgSubstitution() {
		coco = new Coco();
		coco.setCovar("ABC");
		List<String> cfg = new ArrayList<>();
		cfg.add("123?AAA{X}BBB{Y}CCC");
		cfg.add("456?{X}AAA{Y}BBBCCC");
		cfg.add("AAA{X}CCC");
		coco.setCfg(cfg);
		Map<String, String> dict = new HashMap<>();
		dict.put("X",  "x");
		dict.put("Y",  "y");
		assertEquals("AAAxCCC", coco.get(dict));
	}

	@Test
	public void testDefCfgSubstitution2() {
		coco = new Coco();
		coco.setCovar("X");
		List<String> cfg = new ArrayList<>();
		cfg.add("123?AAA{X}BBB{Y}CCC");
		cfg.add("456?{X}AAA{Y}BBBCCC");
		cfg.add("AAA{X}CCC");
		coco.setCfg(cfg);
		Map<String, String> dict = new HashMap<>();
		dict.put("X",  "x");
		dict.put("Y",  "y");
		assertEquals("AAAxCCC", coco.get(dict));
	}

	@Test
	public void testCfgSubstitution() {
		coco = new Coco();
		coco.setCovar("X");
		List<String> cfg = new ArrayList<>();
		cfg.add("123?AAA{X}BBB{Y}CCC");
		cfg.add("x?{X}AAA{Y}BBBCCC");
		cfg.add("AAA{X}CCC");
		coco.setCfg(cfg);
		Map<String, String> dict = new HashMap<>();
		dict.put("X",  "x");
		dict.put("Y",  "y");
		assertEquals("xAAAyBBBCCC", coco.get(dict));
	}

	@Test
	public void testCfgMissingVar() {
		coco = new Coco();
		coco.setCovar("X");
		List<String> cfg = new ArrayList<>();
		cfg.add("123?AAA{X}BBB{Y}CCC");
		cfg.add("x?{X}AAA{Z}BBBCCC");
		cfg.add("AAA{X}CCC");
		coco.setCfg(cfg);
		Map<String, String> dict = new HashMap<>();
		dict.put("X",  "x");
		dict.put("Y",  "y");
		assertEquals("xAAA{Z}BBBCCC", coco.get(dict));
	}

}
