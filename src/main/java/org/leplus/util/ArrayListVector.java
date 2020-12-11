package org.leplus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An {@link java.util.ArrayList}-backed implementation of
 * {@link org.leplus.util.VectorAdapter}.
 *
 * Using this class introduces a small memory overhead compared to using an
 * ArrayList directly. Typically that overhead is the size of an empty Vector,
 * e.g. 48 bytes on Oracle Java HotSpot 1.8.0 for Windows (64-Bit).
 *
 * @author Thomas Leplus
 * @since 1.0.0
 * @see org.leplus.util.VectorAdapter
 *
 * @param <E> type of the elements in the collection.
 */
public class ArrayListVector<E> extends VectorAdapter<E> {

	private static final long serialVersionUID = -7584729063793597958L;

	/**
	 * The {@link java.util.ArrayList} delegate.
	 */
	private ArrayList<E> delegate;

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */
	public ArrayListVector() {
		this(new ArrayList<>());
	}

	/**
	 * Constructs a list containing the elements of the specified collection, in the
	 * order they are returned by the collection's iterator.
	 *
	 * @param c the collection whose elements are to be placed into this list.
	 * @throws NullPointerException if the specified collection is null.
	 */
	public ArrayListVector(final Collection<? extends E> c) {
		this(new ArrayList<>(c));
	}

	/**
	 * Constructs an empty list with the specified initial capacity.
	 *
	 * @param initialCapacity the initial capacity of the list.
	 * @throws IllegalArgumentException if the specified initial capacity is
	 *                                  negative.
	 */
	public ArrayListVector(final int initialCapacity) {
		this(new ArrayList<E>(initialCapacity));
	}

	/**
	 * Common constructor called by the public ones once they have constructed the
	 * delegate.
	 *
	 * @param delegate the delegating {@link java.util.ArrayList}.
	 */
	private ArrayListVector(final ArrayList<E> delegate) {
		super();
		this.delegate = delegate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.leplus.util.VectorAdapter#clone()
	 */
	@Override
	public ArrayListVector<E> clone() {
		final ArrayListVector<E> clone = (ArrayListVector<E>) super.clone();
		clone.delegate = new ArrayList<>(delegate);
		return clone;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.leplus.util.VectorAdapter#delegate()
	 */
	@Override
	protected List<E> delegate() {
		return delegate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.leplus.util.VectorAdapter#ensureCapacity(int)
	 */
	@Override
	public void ensureCapacity(final int minCapacity) {
		delegate.ensureCapacity(minCapacity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.leplus.util.VectorAdapter#trimToSize()
	 */
	@Override
	public void trimToSize() {
		delegate.trimToSize();
	}

	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return delegate.equals(obj);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}
