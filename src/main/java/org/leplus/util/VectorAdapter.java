package org.leplus.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public abstract class VectorAdapter<E> extends Vector<E> {

	private static final long serialVersionUID = -5465186583387834552L;

	protected VectorAdapter() {
		super(0);
	}

	@Override
	public boolean add(final E o) {
		return delegate().add(o);
	}

	@Override
	public void add(final int index, final E element) {
		delegate().add(index, element);
	}

	@Override
	public boolean addAll(final Collection<? extends E> c) {
		return delegate().addAll(c);
	}

	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		return delegate().addAll(index, c);
	}

	@Override
	public void addElement(final E obj) {
		delegate().add(obj);
	}
	
	@Override
	public int capacity() {
		// Sadly even if underlying is an ArrayList,
		// we don't have access to it's capacity. So
		// the size is the closest thing we have.
		return delegate().size();
	}

	@Override
	public void clear() {
		delegate().clear();
	}

	@Override
	public abstract Object clone();

	@Override
	public boolean contains(final Object elem) {
		return delegate().contains(elem);
	}

	@Override
	public boolean containsAll(final Collection<?> c) {
		return delegate().containsAll(c);
	}

	@Override
	public void copyInto(final Object[] anArray) {
		delegate().toArray(anArray);
	}

	protected abstract List<E> delegate();

	@Override
	public E elementAt(final int index) {
		return delegate().get(index);
	}

	@Override
	public Enumeration<E> elements() {
		return new Vector<E>(delegate()).elements();
	}

	@Override
	public void ensureCapacity(final int minCapacity) {
		final List<E> delegate = delegate();
		if (delegate instanceof ArrayList) {
			((ArrayList<E>) delegate).ensureCapacity(minCapacity);
		} else if (delegate instanceof Vector) {
			((Vector<E>) delegate).ensureCapacity(minCapacity);
		} else {
			try {
				final Method method = delegate.getClass().getMethod("ensureCapacity", int.class);
				if (method != null) {
					method.invoke(delegate, minCapacity);
				}
			} catch (final Exception e) {
				handleException(e);
			}
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof List)) {
			return false;
		}
		final List<E> delegate = delegate();
		if (delegate == null) {
			return false;
		}
		return delegate.equals(obj);
	}

	@Override
	public E firstElement() {
		return delegate().get(0);
	}

	@Override
	public E get(final int index) {
		return delegate().get(index);
	}

	protected void handleException(final Exception e) {
		e.printStackTrace();
	}

	@Override
	public int hashCode() {
		final List<E> delegate = delegate();
		return delegate == null ? 0 : delegate.hashCode();
	}

	@Override
	public int indexOf(final Object elem) {
		return delegate().indexOf(elem);
	}

	@Override
	public int indexOf(final Object elem, final int index) {
		final List<E> delegate = delegate();
		final int r = delegate.subList(index, delegate.size()).indexOf(elem);
		return r < 0 ? r : index + r;
	}

	@Override
	public void insertElementAt(final E obj, final int index) {
		delegate().add(index, obj);
	}

	@Override
	public boolean isEmpty() {
		return delegate().isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return delegate().iterator();
	}

	@Override
	public E lastElement() {
		final List<E> delegate = delegate();
		return delegate.get(delegate.size() - 1);
	}

	@Override
	public int lastIndexOf(final Object elem) {
		return delegate().lastIndexOf(elem);
	}

	@Override
	public int lastIndexOf(final Object elem, final int index) {
		return delegate().subList(0, index).lastIndexOf(elem);
	}

	@Override
	public ListIterator<E> listIterator() {
		return delegate().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(final int index) {
		return delegate().listIterator(index);
	}

	@Override
	public E remove(final int index) {
		return delegate().remove(index);
	}

	@Override
	public boolean remove(final Object o) {
		return delegate().remove(o);
	}

	@Override
	public boolean removeAll(final Collection<?> c) {
		return delegate().removeAll(c);
	}

	@Override
	public void removeAllElements() {
		delegate().clear();
	}

	@Override
	public boolean removeElement(final Object obj) {
		return delegate().remove(obj);
	}

	@Override
	public void removeElementAt(final int index) {
		delegate().remove(index);
	}

	@Override
	protected void removeRange(final int fromIndex, final int toIndex) {
		final List<E> delegate = delegate();
		for (int i = fromIndex; i < toIndex; i++) {
			delegate.remove(fromIndex);
		}
	}

	@Override
	public boolean retainAll(final Collection<?> c) {
		return delegate().retainAll(c);
	}

	@Override
	public E set(final int index, final E element) {
		return delegate().set(index, element);
	}

	@Override
	public void setElementAt(final E obj, final int index) {
		delegate().set(index, obj);
	}

	@Override
	public void setSize(final int newSize) {
		while (delegate().size() < newSize) {
			add(null);
		}
	}

	@Override
	public int size() {
		return delegate().size();
	}

	@Override
	public List<E> subList(final int fromIndex, final int toIndex) {
		return delegate().subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return delegate().toArray();
	}

	@Override
	public <T> T[] toArray(final T[] a) {
		return delegate().toArray(a);
	}

	@Override
	public String toString() {
		return delegate().toString();
	}

	@Override
	public void trimToSize() {
		final List<E> delegate = delegate();
		if (delegate instanceof ArrayList) {
			((ArrayList<E>) delegate).trimToSize();
		} else if (delegate instanceof Vector) {
			((Vector<E>) delegate).trimToSize();
		} else {
			try {
				final Method method = delegate.getClass().getMethod("trimToSize");
				if (method != null) {
					method.invoke(delegate);
				}
			} catch (final Exception e) {
				handleException(e);
			}
		}
	}

}
