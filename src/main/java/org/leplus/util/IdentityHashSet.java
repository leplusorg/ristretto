package org.leplus.util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

public class IdentityHashSet<E> extends AbstractSet<E>implements Set<E>, Cloneable {

	private static final Object DUMMY = new Object();

	private IdentityHashMap<E, Object> map;

	public IdentityHashSet() {
		super();
		map = new IdentityHashMap<E, Object>();
	}

	public IdentityHashSet(final Collection<? extends E> c) {
		this();
		for (final E e : c) {
			add(e);
		}
	}

	public IdentityHashSet(final int expectedMaxSize) {
		super();
		map = new IdentityHashMap<E, Object>(expectedMaxSize);
	}

	@Override
	public boolean add(final E e) {
		return map.put(e, DUMMY) == null;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Object clone() {
		try {
			@SuppressWarnings("unchecked")
			final IdentityHashSet<E> clone = (IdentityHashSet<E>) super.clone();
			clone.map = new IdentityHashMap<E, Object>(map);
			return clone;
		} catch (final CloneNotSupportedException e) {
			throw new Error(e);
		}
	}

	@Override
	public boolean contains(final Object o) {
		return map.containsKey(o);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Set)) {
			return false;
		}
		return map.keySet().equals(obj);
	}

	@Override
	public int hashCode() {
		return map.keySet().hashCode();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public boolean remove(final Object o) {
		return map.remove(o) == DUMMY;
	}

	@Override
	public int size() {
		return map.size();
	}

}
