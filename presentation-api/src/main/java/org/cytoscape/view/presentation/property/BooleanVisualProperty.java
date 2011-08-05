
/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.view.presentation.property; 

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;

public class BooleanVisualProperty extends AbstractVisualProperty<Boolean> { 
	
	private static final Range<Boolean> BOOLEAN_RANGE;
	
	static {
		final Set<Boolean> bRange = new HashSet<Boolean>();
		bRange.add(true);
		bRange.add(false);
		BOOLEAN_RANGE = new DiscreteRange<Boolean>(Boolean.class, bRange); 
	}
	
	public BooleanVisualProperty(final Boolean def, final String id, final String name, final Class<?> targetDataType) {
		this(def, id, name, false, targetDataType);
	}
	
	public BooleanVisualProperty(final Boolean def, final String id, final String name, final Boolean ignoreDefault, final Class<?> targetDataType) {
		super(def, BOOLEAN_RANGE, id, name, targetDataType);
		this.shouldIgnoreDefault = ignoreDefault;
	}
	
	public String toSerializableString(final Boolean value) {
		return value.toString();
	}

	public Boolean parseSerializableString(final String text) {
		return Boolean.valueOf(text);
	}
}
