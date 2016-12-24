package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Dec 24, 2016 10:51:19 AM
 */
public class DeleteAction extends ActionMenu implements OnClickListener {

	private long id;

	public DeleteAction(Context context, long id) {
		super(context);
		this.id = id;
	}

	@Override
	public void runAction() {
		AlertDialog itemRemovalDialog = new AlertDialog.Builder(context).create();
		itemRemovalDialog.setTitle(context.getString(R.string.confirm));
		itemRemovalDialog.setMessage(context.getString(R.string.delete_one));
		itemRemovalDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getResources().getString(android.R.string.yes), this);
		itemRemovalDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(android.R.string.no), this);
		itemRemovalDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON_POSITIVE:
			ManagerFactory.getManager(context, ReportManagerImpl.class).delete(id);
			((Activity) context).getFragmentManager().popBackStack();
//			back((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
}
