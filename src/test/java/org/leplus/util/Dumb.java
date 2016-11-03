package org.leplus.util;

/**
 * Dumb class meant to confuse the identity related tests.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
public class Dumb {

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	public int hashcode() {
		return 0;
	}

}
