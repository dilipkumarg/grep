package com.imaginea.dilip.grep.helpers;

import java.util.HashMap;

/**
 * This class is implemented from HashMap with Default value case.
 * 
 * @author dilip
 * 
 * @param <K>
 * @param <V>
 */
public class DefaultHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -8032382596782323098L;
	protected V defaultValue;

	public DefaultHashMap(V defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * It will returns the value for that key. If the value not found in the
	 * HashMap it returns default value.
	 */
	@Override
	public V get(Object k) {
		V v = super.get(k);
		return ((v == null) && !this.containsKey(k)) ? this.defaultValue : v;
	}
}
