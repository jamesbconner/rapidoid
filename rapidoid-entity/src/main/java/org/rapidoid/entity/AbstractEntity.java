package org.rapidoid.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * #%L
 * rapidoid-entity
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * @author Nikolche Mihajlovski
 * @since 2.2.0
 */
public abstract class AbstractEntity implements IEntity {

	private long id;

	private long version;

	private String createdBy;

	private Date createdOn;

	private String lastUpdatedBy;

	private Date lastUpdatedOn;

	private Map<Object, Object> extras;

	private Map<Object, Object> tmps;

	@Override
	public synchronized <T> T get(String attr) {
		return _extra(attr);
	}

	@Override
	public synchronized void set(String attr, Object value) {
		_extra(attr, value);
	}

	@Override
	public synchronized long id() {
		return id;
	}

	@Override
	public synchronized void id(long id) {
		this.id = id;
	}

	@Override
	public synchronized long version() {
		return version;
	}

	@Override
	public synchronized void version(long version) {
		this.version = version;
	}

	@Override
	public synchronized String createdBy() {
		return createdBy;
	}

	@Override
	public synchronized void createdBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public synchronized Date createdOn() {
		return createdOn;
	}

	@Override
	public synchronized void createdOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public synchronized String lastUpdatedBy() {
		return lastUpdatedBy;
	}

	@Override
	public synchronized void lastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public synchronized Date lastUpdatedOn() {
		return lastUpdatedOn;
	}

	@Override
	public synchronized void lastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public synchronized boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (id == 0) {
			return false;
		}

		if (!(obj instanceof IEntity)) {
			return false;
		}

		IEntity other = (IEntity) obj;
		if (id != other.id())
			return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <K, V> ConcurrentMap<K, V> _map(Object key) {
		_extras();

		if (!extras.containsKey(key)) {
			extras.put(key, new ConcurrentHashMap<K, V>());
		}

		return (ConcurrentMap<K, V>) extras.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> List<T> _list(Object key) {
		_extras();

		if (!extras.containsKey(key)) {
			extras.put(key, new ArrayList<T>());
		}

		return (List<T>) extras.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> Set<T> _set(Object key) {
		_extras();

		if (!extras.containsKey(key)) {
			extras.put(key, new LinkedHashSet<T>());
		}

		return (Set<T>) extras.get(key);
	}

	@SuppressWarnings("unchecked")
	private <T> T _extra(Object key) {
		return (T) _extras().get(key);
	}

	private void _extra(Object key, Object value) {
		_extras().put(key, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> T _tmp(Object key) {
		return (T) _tmps().get(key);
	}

	@Override
	public synchronized void _tmp(Object key, Object value) {
		_tmps().put(key, value);
	}

	@Override
	public synchronized Map<Object, Object> _extras() {
		if (extras == null) {
			extras = Collections.synchronizedMap(new HashMap<Object, Object>());
		}
		return extras;
	}

	@Override
	public synchronized Map<Object, Object> _tmps() {
		if (tmps == null) {
			tmps = Collections.synchronizedMap(new HashMap<Object, Object>());
		}
		return tmps;
	}

}
