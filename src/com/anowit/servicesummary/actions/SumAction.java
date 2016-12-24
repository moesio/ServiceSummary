package com.anowit.servicesummary.actions;

import android.app.FragmentManager;
import android.content.Context;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.ui.MainActivity;
import com.anowit.servicesummary.ui.SummaryFragment;

/**
 * @author moesio @ gmail.com
 * @date Nov 29, 2016 10:36:58 PM
 */
public class SumAction extends ActionMenu {

	public SumAction(Context context) {
		super(context);
	}

	@Override
	public void runAction() {
		FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, new SummaryFragment()).addToBackStack(null).commit();
	}

}
