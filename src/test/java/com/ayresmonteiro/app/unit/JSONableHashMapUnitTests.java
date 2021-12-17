package com.ayresmonteiro.app.unit;

import static org.junit.Assert.assertEquals;

import com.ayresmonteiro.app.lib.format.JSONableHashMap;

import org.junit.Test;

public class JSONableHashMapUnitTests {
	@Test
	public void assertIfNormalValuesAreCorrectlyParsed() {
		JSONableHashMap<String, String> map = new JSONableHashMap<String, String>() {
			{
				put("nome", "Will");
				put("idade", "16");
			}
		};

		assertEquals("{\"idade\":\"16\",\"nome\":\"Will\"}", map.toJson());
	}
}
