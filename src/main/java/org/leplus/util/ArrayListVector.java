package org.leplus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayListVector<E> extends VectorAdapter<E> {

	private static final long serialVersionUID = -7584729063793597958L;

	private final List<E> delegate;

	public ArrayListVector() {
		this(new ArrayList<E>());
	}

	public ArrayListVector(final Collection<? extends E> c) {
		this(new ArrayList<E>(c));
	}

	public ArrayListVector(final int initialCapacity) {
		this(new ArrayList<E>(initialCapacity));
	}

	private ArrayListVector(final List<E> delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	protected List<E> delegate() {
		return delegate;
	}

}
