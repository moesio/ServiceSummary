package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.ui.R;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 5:31:22 PM
 */
public class ClearListAction extends ActionMenu {

	public ClearListAction(Context context) {
		super(context);
	}

	@Override
	public void runAction() {
		Activity activity = (Activity) context;
		ListView listView = (ListView) activity.findViewById(R.id.reportList);
	}
}
