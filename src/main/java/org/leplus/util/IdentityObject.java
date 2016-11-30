/*
 * Ristretto - A small library of (hopefully) useful java classes.
 * Copyright (C) 2016 Thomas Leplus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leplus.util;

import java.io.Serializable;

/**
 * This class is a singleton. It supports serialization and cloning.
 * In both case the unique instance of IdentityObject remains the same object.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class IdentityObject implements Cloneable, Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * The singleton.
	 */
	public static final IdentityObject IT = new IdentityObject();

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
