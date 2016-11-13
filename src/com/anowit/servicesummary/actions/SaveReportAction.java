package com.anowit.servicesummary.actions;

import android.content.Context;
import android.widget.Toast;

import com.anowit.servicesummary.helpers.ActionMenu;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 4:51:57 PM
 */
public class SaveReportAction extends ActionMenu {

	public SaveReportAction(Context context) {
		super(context);
	}

	public void runAction() {
		Toast.makeText(super.context, "asdasdfasdfa sdf" + "", Toast.LENGTH_SHORT).show();
	}

}
