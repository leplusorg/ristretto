package org.leplus.util;

/**
 * This class is meant to confuse the identity related tests. It is the evil
 * twin sister of {@link org.leplus.util.IdentityObject}. Whereas identity can
 * be seen as the strictest form of equality (i.e. being the same object
 * instance), instances of the DuplicityObject class have the weakest form of
 * equality (they are all equal to each other).
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class DuplicityObject {

	/**
	 * Creates a new instance.
	 */
	public DuplicityObject() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		return o instanceof DuplicityObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashcode()
	 */
	public int hashcode() {
		return Integer.MAX_VALUE;
	}

}
