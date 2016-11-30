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

/**
 * This class is meant to confuse the identity related tests.
 * It is the evil twin sister of {@link org.leplus.util.IdentityObject}.
 * Whereas identity can be seen as the strictest form of equality
 * (i.e. being the same object instance), instances of the DuplicityObject class
 * have the weakest form of equality (they are all equal to each other).
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof DuplicityObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashcode()
	 */
	public int hashcode() {
		return Integer.MAX_VALUE;
	}

}
