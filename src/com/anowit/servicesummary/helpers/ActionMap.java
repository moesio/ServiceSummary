package com.anowit.servicesummary.helpers;

import android.content.Context;
import android.util.SparseArray;
import android.widget.Toast;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 4:33:53 PM
 */
public class ActionMap extends SparseArray<ActionMenu> {

	private Context context;

	public ActionMap(Context context) {
		this.context = context;
	}

	public void runAction(int itemId) {
		ActionMenu actionMenu = get(itemId);
		if (actionMenu != null) {
			actionMenu.runAction();
		} else {
			String resourceName = context.getResources().getResourceName(itemId);
			Toast.makeText(context, resourceName.substring(resourceName.indexOf("/") + 1) + " not added as action to actionMap", Toast.LENGTH_LONG).show();
		}
	}
}
