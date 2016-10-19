package org.leplus.util;

import java.io.Serializable;

public final class IdentityObject implements Cloneable, Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;
	
	public static final IdentityObject IT = new IdentityObject();
	
	private IdentityObject() {
		super();
	}
	
	@Override
	public int hashCode() {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public IdentityObject clone() {
		return this;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof IdentityObject;
	}
	
}
