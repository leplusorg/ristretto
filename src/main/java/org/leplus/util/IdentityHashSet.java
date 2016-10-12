package org.leplus.util;

import java.io.IOException;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class IdentityHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable {

	private static final Object DUMMY = new Object();

	private IdentityHashMap<E, Object> map;

	public IdentityHashSet() {
		super();
		map = new IdentityHashMap<E, Object>();
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
	public boolean contains(final Object o) {
		return map.containsKey(o);
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

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentityHashSet<?> other = (IdentityHashSet<?>) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}

    @SuppressWarnings("unchecked")
	public Object clone() {
        try {
        	IdentityHashSet<E> newSet = (IdentityHashSet<E>) super.clone();
            newSet.map = (IdentityHashMap<E, Object>) map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Unexpected cloning error");
        }
    }
    
}
