package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.anowit.servicesummary.ui.R;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 4:51:57 PM
 */
public class SaveReportAction extends ActionMenu {

	private ReportManagerImpl reportManager;
	private EditText editName;
	private EditText editHours;
	private EditText editPlacements;
	private EditText editVideoShowing;
	private EditText editReturnVisit;
	private EditText editStudies;

	public SaveReportAction(Context context) {
		super(context);
	}

	@Override
	public void runAction() {
		extractForm();

		reportManager = ManagerFactory.getManager(context, ReportManagerImpl.class);
		Report report = createReport();
		if (reportManager.create(report)) {
			Toast.makeText(super.context, R.string.reportSaved, Toast.LENGTH_SHORT).show();
//			clearForm();
		}
	}

	private void extractForm() {
		Activity activity = (Activity) context;
		editName = (EditText) activity.findViewById(R.id.editName);
		editHours = (EditText) ((Activity) context).findViewById(R.id.editHours);
		editPlacements = (EditText) ((Activity) context).findViewById(R.id.editPlacements);
		editVideoShowing = (EditText) ((Activity) context).findViewById(R.id.editVideoShowing);
		editReturnVisit = (EditText) ((Activity) context).findViewById(R.id.editReturnVisit);
		editStudies = (EditText) ((Activity) context).findViewById(R.id.editStudies);
	}
	
	private void clearForm() {
		editName.setText("");
		editHours.setText("");
		editPlacements.setText("");
		editVideoShowing.setText("");
		editReturnVisit.setText("");
		editStudies.setText("");
	}

	private Report createReport() {
		String name = editName.getText().toString();
		Double hours = Double.valueOf(editHours.getText().toString());
		Integer placements = Integer.valueOf(editPlacements.getText().toString());
		Integer videoShowings = Integer.valueOf(editVideoShowing.getText().toString());
		Integer returnVisits = Integer.valueOf(editReturnVisit.getText().toString());
		Integer studies = Integer.valueOf(editStudies.getText().toString());

		Report report = new Report();
		report.setName(name).setHours(hours).setPlacements(placements).setVideoShowings(videoShowings).setReturnVisits(returnVisits).setStudies(studies);

		return report;
	}

}
