package com.anowit.servicesummary.actions;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.anowit.servicesummary.ui.R;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 5:31:22 PM
 */
public class ClearListAction extends ActionMenu implements OnClickListener {

	public ClearListAction(Context context) {
		super(context);
	}

	@Override
	public void runAction() {
		AlertDialog itemRemovalDialog = new AlertDialog.Builder(context).create();
		itemRemovalDialog.setTitle(context.getString(R.string.confirm));
		itemRemovalDialog.setMessage(context.getString(R.string.delete_all));
		itemRemovalDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getResources().getString(android.R.string.yes), this);
		itemRemovalDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(android.R.string.no), this);
		itemRemovalDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON_POSITIVE:
			Activity activity = (Activity) context;
			ListView listView = (ListView) activity.findViewById(R.id.reportList);

			ReportManagerImpl reportManager = ManagerFactory.getManager(context, ReportManagerImpl.class);
			List<Report> list = reportManager.list();
			for (Report report : list) {
				reportManager.delete(report.getId());
			}

			((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

			break;
		default:
			break;
		}
	}
}
