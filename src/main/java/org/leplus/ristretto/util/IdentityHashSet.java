/*
 * Copyright 2016-present Thomas Leplus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.leplus.ristretto.util;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This {@link java.util.Set} relies on identity (==) to compare the objects it
 * contains. It does not matter what the objects {@link java.lang.Object#equals(java.lang.Object)} methods say.
 * 
 * This collection is Cloneable and Serializable. If you clone an instance of an IdentityHashSet, the clone is
 * equal to the original instance (i.e. original.equals(clone) == true and clone.equals(original) == true).
 * However this is not true for serialization, a deserialized instance of IdentityHashSet is not equal
 * to the instance originally serialized (i.e. original.equals(deserialized) == false
 * and deserialized.equals(original) == false).
 *
 * @author Thomas Leplus
 * @since 1.0.0
 *
 * @param <E> the type of the elements of the set.
 */
public class IdentityHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {

	private static final long serialVersionUID = -6954699503843913409L;

	/**
	 * A dummy singleton acting as a placeholder for the values in the underlying
	 * {@link java.util.IdentityHashMap}.
	 */
	private static final IdentityObject DUMMY = IdentityObject.IT;

	/**
	 * The underlying {@link java.util.IdentityHashMap} which keySet is backing the
	 * implementation of this class.
	 */
	private IdentityHashMap<E, Object> map;

	/**
	 * Constructs a new, empty identity hash set with a default expected maximum
	 * size (21).
	 */
	public IdentityHashSet() {
		super();
		map = new IdentityHashMap<>();
	}

	/**
	 * Constructs a new identity hash set containing the objects of the provided
	 * collection.
	 *
	 * @param c a collection of objects to put in this identity hash set.
	 */
	public IdentityHashSet(final Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * Constructs a new, empty set with the specified expected maximum size. Putting
	 * more than the expected number of objects into the set may cause the internal
	 * data structure to grow, which may be somewhat time-consuming.
	 *
	 * @param expectedMaxSize the expected maximum size of the set.
	 * @throws IllegalArgumentException if expectedMaxSize is negative.
	 */
	public IdentityHashSet(final int expectedMaxSize) {
		super();
		map = new IdentityHashMap<>(expectedMaxSize);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(final E e) {
		return map.put(e, DUMMY) == null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#clear()
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		try {
			@SuppressWarnings("unchecked")
			final IdentityHashSet<E> clone = (IdentityHashSet<E>) super.clone();
			clone.map = new IdentityHashMap<>(map);
			return clone;
		} catch (final CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(final Object o) {
		return map.containsKey(o);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractSet#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
        }
		return map.keySet().equals(obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractSet#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (final Object o : map.keySet()) {
			if (o != null) {
				hashCode += o.hashCode();
			}
		}
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final Object o) {
		return map.remove(o) == DUMMY;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return map.size();
	}

}
