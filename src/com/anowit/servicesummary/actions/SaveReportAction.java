package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 4:51:57 PM
 */
public class SaveReportAction extends ActionMenu {

	private ReportManagerImpl reportManager;
	private Spinner spinProfile;
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
			clearForm();
		}
	}

	private void extractForm() {
		Activity activity = (Activity) context;
		spinProfile = (Spinner) activity.findViewById(R.id.spinProfile);
		editName = (EditText) activity.findViewById(R.id.editName);
		editHours = (EditText) ((Activity) context).findViewById(R.id.editHours);
		editPlacements = (EditText) ((Activity) context).findViewById(R.id.editPlacements);
		editVideoShowing = (EditText) ((Activity) context).findViewById(R.id.editVideoShowing);
		editReturnVisit = (EditText) ((Activity) context).findViewById(R.id.editReturnVisit);
		editStudies = (EditText) ((Activity) context).findViewById(R.id.editStudies);
	}

	private void clearForm() {
		spinProfile.setSelection(0);
		editName.setText("");
		editHours.setText("");
		editPlacements.setText("");
		editVideoShowing.setText("");
		editReturnVisit.setText("");
		editStudies.setText("");
	}

	private Report createReport() {
		String name = editName.getText().toString();
		Double hours = getValue(editHours, Double.class);
		Integer placements = getValue(editPlacements, Integer.class);
		Integer videoShowings = getValue(editVideoShowing, Integer.class);
		Integer returnVisits = getValue(editReturnVisit, Integer.class);
		Integer studies = getValue(editStudies, Integer.class);

		Report report = new Report();
		report.setName(name).setHours(hours).setPlacements(placements).setVideoShowings(videoShowings).setReturnVisits(returnVisits).setStudies(studies);

		return report;
	}

	private <T extends Number> T getValue(EditText edit, Class<T> clazz) {
		T value = null;
		try {
			value = clazz.getConstructor(String.class).newInstance(edit.getText().toString());
		} catch (Exception e) {
			try {
				value = clazz.getConstructor(String.class).newInstance("0");
			} catch (Exception e1) {
			}
		}
		return value;
	}
}
