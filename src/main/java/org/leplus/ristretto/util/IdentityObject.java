/*
 * Copyright 2016-present Thomas Leplus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.leplus.ristretto.util;

import java.io.Serializable;

/**
 * This class is a singleton. It supports serialization and cloning. In both
 * case the unique instance of IdentityObject remains the same object.
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public IdentityObject clone() {
		return IT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof IdentityObject;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Integer.MAX_VALUE;
	}

	private Object readResolve() {
		return IT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName();
	}

}
