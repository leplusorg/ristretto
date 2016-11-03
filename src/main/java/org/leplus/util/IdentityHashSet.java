package org.leplus.util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Thomas Leplus
 * @since 1.0.0
 * 
 * @param <E>
 */
public class IdentityHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable {

	/**
	 * 
	 */
	private static final Object DUMMY = new Object();

	/**
	 * 
	 */
	private IdentityHashMap<E, Object> map;

	/**
	 * 
	 */
	public IdentityHashSet() {
		super();
		map = new IdentityHashMap<E, Object>();
	}

	/**
	 * @param c
	 */
	public IdentityHashSet(final Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * @param expectedMaxSize
	 */
	public IdentityHashSet(final int expectedMaxSize) {
		super();
		map = new IdentityHashMap<E, Object>(expectedMaxSize);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(final E e) {
		return map.put(e, DUMMY) == null;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#clear()
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() { //NOSONAR
		try {
			@SuppressWarnings("unchecked")
			final IdentityHashSet<E> clone = (IdentityHashSet<E>) super.clone();
			clone.map = new IdentityHashMap<E, Object>(map);
			return clone;
		} catch (final CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(final Object o) {
		return map.containsKey(o);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSet#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		return map.keySet().equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSet#hashCode()
	 */
	@Override
	public int hashCode() {
		return map.keySet().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final Object o) {
		return map.remove(o) == DUMMY;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return map.size();
	}

}
