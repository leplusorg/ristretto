package org.leplus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Thomas Leplus
 * @since 1.0.0
 * 
 * @param <E>
 */
public class ArrayListVector<E> extends VectorAdapter<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7584729063793597958L;

	/**
	 * 
	 */
	private ArrayList<E> delegate;

	/**
	 * 
	 */
	public ArrayListVector() {
		this(new ArrayList<E>());
	}

	/**
	 * @param c
	 */
	public ArrayListVector(final Collection<? extends E> c) {
		this(new ArrayList<E>(c));
	}

	/**
	 * @param initialCapacity
	 */
	public ArrayListVector(final int initialCapacity) {
		this(new ArrayList<E>(initialCapacity));
	}

	/**
	 * @param delegate
	 */
	private ArrayListVector(final ArrayList<E> delegate) {
		super();
		this.delegate = delegate;
	}

	/* (non-Javadoc)
	 * @see org.leplus.util.VectorAdapter#clone()
	 */
	@Override
	public ArrayListVector<E> clone() { //NOSONAR
		final ArrayListVector<E> clone = (ArrayListVector<E>) super.clone();
		clone.delegate = new ArrayList<E>(delegate);
		return clone;
	}

	/* (non-Javadoc)
	 * @see org.leplus.util.VectorAdapter#delegate()
	 */
	@Override
	protected List<E> delegate() {
		return delegate;
	}

	/* (non-Javadoc)
	 * @see org.leplus.util.VectorAdapter#ensureCapacity(int)
	 */
	@Override
	public void ensureCapacity(int minCapacity) {
		delegate.ensureCapacity(minCapacity);
	}

	/* (non-Javadoc)
	 * @see org.leplus.util.VectorAdapter#trimToSize()
	 */
	@Override
	public void trimToSize() {
		delegate.trimToSize();
	}
	
}
