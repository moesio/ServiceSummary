package com.anowit.servicesummary.helpers;

import android.util.SparseArray;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 4:33:53 PM
 */
public class ActionMap extends SparseArray<ActionMenu> {

	public void runAction(int itemId) {
		ActionMenu actionMenu = get(itemId);
		if (actionMenu != null) {
			actionMenu.runAction();
		}
	}
}
