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

	@Test
	public void assertIfRecursiveHashMapsAreCorrectlyParsed() {
		JSONableHashMap<String, JSONableHashMap<String, String>> map = new JSONableHashMap<String, JSONableHashMap<String, String>>() {
			{
				put("estabelecimento", new JSONableHashMap<String, String>() {
					{
						put("nome", "Estabelecimento Teste");
					}
				});
			}
		};

		assertEquals("{\"estabelecimento\":{\"nome\":\"Estabelecimento Teste\"}}", map.toJson());
	}
}
