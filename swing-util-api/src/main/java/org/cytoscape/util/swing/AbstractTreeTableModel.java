/*
 * The contents of this file are subject to the Sapient Public License
 * Version 1.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://carbon.sf.net/License.html.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is The Carbon Component Framework.
 *
 * The Initial Developer of the Original Code is Sapient Corporation
 *
 * Copyright (C) 2003 Sapient Corporation. All Rights Reserved.
 */
package org.cytoscape.util.swing;


/*
 * @(#)AbstractTreeTableModel.java    1.2 98/10/27
 *
 * Copyright 1997, 1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;


/**
 * An abstract implementation of the TreeTableModel interface, handling the list
 * of listeners.
 * @author Philip Milne
 * @CyAPI.Abstract.Class 
 */
public abstract class AbstractTreeTableModel implements TreeTableModel {
	/**
	 * The root of this AbstractTreeTableModel.
	 */
	private Object root;
	/**
	 * The {@link EventListenerList} associated with this AbstractTreeTableModel.
	 */
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * Creates a new AbstractTreeTableModel object.
	 *
	 * @param root  The root of this AbstractTreeTableModel.
	 */
	public AbstractTreeTableModel(Object root) {
		this.root = root;
	}

	/**
	 *  Returns the root of this AbstractTreeTableModel.
	 *
	 * @return the root of this AbstractTreeTableModel.
	 */
	public Object getRoot() {
		return root;
	}

	/**
	 *  Returns true if the specified node is a leaf, false otherwise.
	 *
	 * @param node The node to check.
	 *
	 * @return  True if the specified node is a leaf, false otherwise.
	 */
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	/**
	 * Should be overridden. 
	 *
	 * @param path the TreePath. 
	 * @param newValue the new value. 
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	// This is not called in the JTree's default mode: use a naive implementation.
	/**
	 * Returns the index of a child given a parent. 
	 *
	 * @param parent The parent object. 
	 * @param child The child whose index we want. 
	 *
	 * @return The index of the child. -1 if the child isn't found.
	 */
	public int getIndexOfChild(Object parent, Object child) {
		for (int i = 0; i < getChildCount(parent); i++) {
			if (getChild(parent, i).equals(child)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * @param l The TreeModelListener to add.
	 */
	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	/**
	 * @param l The TreeModelListener to remove.
	 */
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.  The event instance
	 * is lazily created using the parameters passed into
	 * the fire method.
	 * @see EventListenerList
	 */
	protected void fireTreeNodesChanged(Object source, Object[] path, int[] childIndices,
	                                    Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);

				((TreeModelListener) listeners[i + 1]).treeNodesChanged(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.  The event instance
	 * is lazily created using the parameters passed into
	 * the fire method.
	 * @see EventListenerList
	 */
	protected void fireTreeNodesInserted(Object source, Object[] path, int[] childIndices,
	                                     Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);

				((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.  The event instance
	 * is lazily created using the parameters passed into
	 * the fire method.
	 * @see EventListenerList
	 */
	protected void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,
	                                    Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);

				((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.  The event instance
	 * is lazily created using the parameters passed into
	 * the fire method.
	 * @see EventListenerList
	 */
	protected void fireTreeStructureChanged(Object source, Object[] path, int[] childIndices,
	                                        Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);

				((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
			}
		}
	}

	//
	/**
	 * Returns the class of the column specified in the argument. 
	 * Should probably be overridden since this implementation always returns Object.
	 *
	 * @param column The index of the column.
	 *
	 * @return the class of the column specified in the argument. 
	 */
	public Class<?> getColumnClass(int column) {
		return Object.class;
	}

	/** By default, make the column with the Tree in it the only editable one.
	 *  Making this column editable causes the JTable to forward mouse
	 *  and keyboard events in the Tree column to the underlying JTree.
	 */
	public boolean isCellEditable(Object node, int column) {
		return getColumnClass(column) == TreeTableModel.class;
	}

	/**
	 * Should be overridden - this is a no-op. 
	 *
	 * @param aValue The value. 
	 * @param node The node. 
	 * @param column The column. 
	 */
	public void setValueAt(Object aValue, Object node, int column) {
	}

	// Left to be implemented in the subclass:

	/*
	 *   public Object getChild(Object parent, int index)
	 *   public int getChildCount(Object parent)
	 *   public int getColumnCount()
	 *   public String getColumnName(Object node, int column)
	 *   public Object getValueAt(Object node, int column)
	 */
}
