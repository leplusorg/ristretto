package org.leplus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class VectorAdapter<E> extends Vector<E> {

	private static final long serialVersionUID = -5465186583387834552L;
	
	private List<E> underlying;
	
	public VectorAdapter(List<E> underlying) {
		super();
		this.underlying = underlying;
	}

	@Override
	public void copyInto(Object[] anArray) {
		underlying.toArray(anArray);
	}

	@Override
	public void trimToSize() {
		if (underlying instanceof ArrayList) {
			((ArrayList<E>) underlying).trimToSize();
		}
	}

	@Override
	public void ensureCapacity(int minCapacity) {
		if (underlying instanceof ArrayList) {
			((ArrayList<E>) underlying).ensureCapacity(minCapacity);
		}
	}

	@Override
	public void setSize(int newSize) {
		while (underlying.size() < newSize) {
			add(null);
		}
	}

	@Override
	public int capacity() {
		// Sadly even if underlying is an ArrayList,
		// we don't have access to it's capacity. So
		// the size is the closest thing we have.
		return underlying.size();
	}

	@Override
	public int size() {
		return underlying.size();
	}

	@Override
	public boolean isEmpty() {
		return underlying.isEmpty();
	}

	@Override
	public Enumeration<E> elements() {
		return new Vector<E>(underlying).elements();
	}

	@Override
	public boolean contains(Object elem) {
		return underlying.contains(elem);
	}

	@Override
	public int indexOf(Object elem) {
		return underlying.indexOf(elem);
	}

	@Override
	public int indexOf(Object elem, int index) {
		int r = underlying.subList(index, underlying.size()).indexOf(elem);
		return r < 0 ? r : index + r; 
	}

	@Override
	public int lastIndexOf(Object elem) {
		return underlying.lastIndexOf(elem);
	}

	@Override
	public int lastIndexOf(Object elem, int index) {
		return underlying.subList(0, index).lastIndexOf(elem);
	}

	@Override
	public E elementAt(int index) {
		return underlying.get(index);
	}

	@Override
	public E firstElement() {
		return underlying.get(0);
	}

	@Override
	public E lastElement() {
		return underlying.get(underlying.size() - 1);
	}

	@Override
	public void setElementAt(E obj, int index) {
		underlying.set(index, obj);
	}

	@Override
	public void removeElementAt(int index) {
		underlying.remove(index);
	}

	@Override
	public void insertElementAt(E obj, int index) {
		underlying.add(index, obj);
	}

	@Override
	public void addElement(E obj) {
		underlying.add(obj);
	}

	@Override
	public boolean removeElement(Object obj) {
		return underlying.remove(obj);
	}

	@Override
	public void removeAllElements() {
		underlying.clear();
	}

	@Override
	public Object[] toArray() {
		return underlying.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return underlying.toArray(a);
	}

	@Override
	public E get(int index) {
		return underlying.get(index);
	}

	@Override
	public E set(int index, E element) {
		return underlying.set(index, element);
	}

	@Override
	public boolean add(E o) {
		return underlying.add(o);
	}

	@Override
	public boolean remove(Object o) {
		return underlying.remove(o);
	}

	@Override
	public void add(int index, E element) {
		underlying.add(index, element);
	}

	@Override
	public E remove(int index) {
		return underlying.remove(index);
	}

	@Override
	public void clear() {
		underlying.clear();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return underlying.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return underlying.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return underlying.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return underlying.retainAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return underlying.addAll(index, c);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		VectorAdapter<E> other = (VectorAdapter<E>) obj;
		if (underlying == null) {
			if (other.underlying != null)
				return false;
		} else if (!underlying.equals(other.underlying))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((underlying == null) ? 0 : underlying.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return underlying.toString();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return underlying.subList(fromIndex, toIndex);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; i++) {
			underlying.remove(fromIndex);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return underlying.iterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return underlying.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return underlying.listIterator(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		VectorAdapter<E> clone = (VectorAdapter<E>) super.clone();
	    try {
	    	if (underlying instanceof Cloneable) {
	    		clone.underlying = (List<E>) underlying.getClass().getMethod("clone").invoke(underlying);
	    	} else {
	    		clone.underlying = underlying.getClass().newInstance();
	    	}
	    } catch (Exception e) {
	    	clone.underlying = new ArrayList<E>();
	    }
	    clone.underlying.addAll(underlying);
		return clone;
	}

}
