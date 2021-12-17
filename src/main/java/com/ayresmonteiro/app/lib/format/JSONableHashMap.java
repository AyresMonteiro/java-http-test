package com.ayresmonteiro.app.lib.format;

import java.util.HashMap;

import com.ayresmonteiro.app.lib.contracts.JSONable;

public class JSONableHashMap<K, V> extends HashMap<K, V> implements JSONable {
	@Override
	public String toString() {
		return this.toJson();
	}

	public String toJson() {
		String json_value = "{";

		for (Entry<K, V> entry : this.entrySet()) {
			json_value += "\"" + entry.getKey() + "\":";

			String value_to_string = entry.getValue().toString();

			if (value_to_string.charAt(0) != '{') {
				json_value += "\"";
			}

			json_value += value_to_string;

			if (value_to_string.charAt(0) != '{') {
				json_value += "\",";
			}
		}

		if (json_value.charAt(json_value.length() - 1) != '}') {
			json_value = json_value.substring(0, json_value.length() - 1);
		}

		json_value += "}";

		return json_value;
	}
}
