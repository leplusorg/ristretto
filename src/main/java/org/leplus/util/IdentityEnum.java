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
 * This enum is a very efficient singleton. It supports serialization but not cloning.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
public enum IdentityEnum {

	/**
	 * The singleton.
	 */
	IT;

}
