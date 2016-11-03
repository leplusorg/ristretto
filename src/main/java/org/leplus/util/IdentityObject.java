package org.leplus.util;

import java.io.Serializable;

/**
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class IdentityObject implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * 
	 */
	public static final IdentityObject IT = new IdentityObject();

	/**
	 * 
	 */
	private IdentityObject() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public IdentityObject clone() { //NOSONAR
		return IT;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof IdentityObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Integer.MAX_VALUE;
	}

	/**
	 * @return
	 */
	private Object readResolve() {
		return IT;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName();
	}

}
