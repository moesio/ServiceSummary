package com.anowit.servicesummary.helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * @author moesio @ gmail.com
 * @date Nov 11, 2016 1:44:29 PM
 */
public class ActionMenu {

	protected Context context;

	public ActionMenu(Context context) {
		this.context = context;
	}

	public void runAction() {
		Toast.makeText(context, getClass().getSimpleName() + " must implement runAction", Toast.LENGTH_LONG).show();
	}
}
