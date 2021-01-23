package org.leplus.ristretto.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * This adapter class extends {@link java.util.Vector} to make it easier to replace {@link java.util.Vector} uses by
 * another {@link java.util.List} implementation.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 * @see org.leplus.ristretto.util.ArrayListVector
 *
 * @param <E> type of the elements in the collection.
 */
public abstract class VectorAdapter<E> extends Vector<E> {

	private static final long serialVersionUID = -5465186583387834552L;

	/**
	 * Default constructor. Should be called by subclasses.
	 */
	protected VectorAdapter() {
		super(0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#add(java.lang.Object)
	 */
	@Override
	public boolean add(final E o) {
		return delegate().add(o);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#add(int, java.lang.Object)
	 */
	@Override
	public void add(final int index, final E element) {
		delegate().add(index, element);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		return delegate().addAll(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		return delegate().addAll(index, c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#addElement(java.lang.Object)
	 */
	@Override
	public void addElement(final E obj) {
		delegate().add(obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#capacity()
	 */
	@Override
	public int capacity() {
		// Sadly even if underlying is an ArrayList,
		// we don't have access to it's capacity. So
		// the size is the closest thing we have.
		return delegate().size();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#clear()
	 */
	@Override
	public void clear() {
		delegate().clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#clone()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public VectorAdapter<E> clone() {
		return (VectorAdapter<E>) super.clone();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(final Object elem) {
		return delegate().contains(elem);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(final Collection<?> c) {
		return delegate().containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#copyInto(java.lang.Object[])
	 */
	@Override
	public void copyInto(final Object[] anArray) {
		delegate().toArray(anArray);
	}

	/**
	 * Accessor to the delegate provided by subclass.
	 *
	 * @return the delegate.
	 */
	protected abstract List<E> delegate();

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#elementAt(int)
	 * @return the component at the specified index
	 */
	@Override
	public E elementAt(final int index) {
		return delegate().get(index);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#elements()
	 */
	@Override
	public Enumeration<E> elements() {
		return new Vector<>(delegate()).elements();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#ensureCapacity(int)
	 */
	@Override
	public abstract void ensureCapacity(final int minCapacity);

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#equals(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#firstElement()
	 */
	@Override
	public E firstElement() {
		return delegate().get(0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#get(int)
	 */
	@Override
	public E get(final int index) {
		return delegate().get(index);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#hashCode()
	 */
	@Override
	public int hashCode() {
		return delegate().hashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(final Object elem) {
		return delegate().indexOf(elem);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#indexOf(java.lang.Object, int)
	 */
	@Override
	public int indexOf(final Object elem, final int index) {
		final List<E> delegate = delegate();
		final int r = delegate.subList(index, delegate.size()).indexOf(elem);
		return r < 0 ? r : index + r;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#insertElementAt(java.lang.Object, int)
	 */
	@Override
	public void insertElementAt(final E obj, final int index) {
		delegate().add(index, obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return delegate().isEmpty();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return delegate().iterator();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#lastElement()
	 */
	@Override
	public E lastElement() {
		final List<E> delegate = delegate();
		return delegate.get(delegate.size() - 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(final Object elem) {
		return delegate().lastIndexOf(elem);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#lastIndexOf(java.lang.Object, int)
	 */
	@Override
	public int lastIndexOf(final Object elem, final int index) {
		return delegate().subList(0, index).lastIndexOf(elem);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		return delegate().listIterator();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(final int index) {
		return delegate().listIterator(index);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#remove(int)
	 */
	@Override
	public E remove(final int index) {
		return delegate().remove(index);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final Object o) {
		return delegate().remove(o);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(final Collection<?> c) {
		return delegate().removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeAllElements()
	 */
	@Override
	public void removeAllElements() {
		delegate().clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeElement(java.lang.Object)
	 */
	@Override
	public boolean removeElement(final Object obj) {
		return delegate().remove(obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeElementAt(int)
	 */
	@Override
	public void removeElementAt(final int index) {
		delegate().remove(index);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeRange(int, int)
	 */
	@Override
	protected void removeRange(final int fromIndex, final int toIndex) {
		final List<E> delegate = delegate();
		for (int i = fromIndex; i < toIndex; i++) {
			delegate.remove(fromIndex);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(final Collection<?> c) {
		return delegate().retainAll(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#set(int, java.lang.Object)
	 */
	@Override
	public E set(final int index, final E element) {
		return delegate().set(index, element);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#setElementAt(java.lang.Object, int)
	 */
	@Override
	public void setElementAt(final E obj, final int index) {
		delegate().set(index, obj);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#setSize(int)
	 */
	@Override
	public void setSize(final int newSize) {
		while (delegate().size() < newSize) {
			add(null);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#size()
	 */
	@Override
	public int size() {
		return delegate().size();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#subList(int, int)
	 */
	@Override
	public List<E> subList(final int fromIndex, final int toIndex) {
		return delegate().subList(fromIndex, toIndex);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#toArray()
	 */
	@Override
	public Object[] toArray() {
		return delegate().toArray();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(final T[] a) {
		return delegate().toArray(a);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#toString()
	 */
	@Override
	public String toString() {
		return delegate().toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#trimToSize()
	 */
	@Override
	public abstract void trimToSize();

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#removeIf(Predicate<? super E>)
	 */
	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		return delegate().removeIf(filter);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#forEach(Consumer<? super E>)
	 */
	@Override
	public synchronized void forEach(Consumer<? super E> action) {
		delegate().forEach(action);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#replaceAll(UnaryOperator<E>)
	 */
	@Override
	public synchronized void replaceAll(UnaryOperator<E> operator) {
		delegate().replaceAll(operator);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#sort(Comparator<? super E>)
	 */
	@Override
	public synchronized void sort(Comparator<? super E> c) {
		delegate().sort(c);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Vector#spliterator()
	 */
	@Override
	public Spliterator<E> spliterator() {
		return delegate().spliterator();
	}

}
