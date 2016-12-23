package com.anowit.servicesummary.actions;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Profile;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;
import com.seimos.android.dbhelper.persistence.Filter;
import com.seimos.android.dbhelper.persistence.Restriction;

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
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.summary, (ViewGroup) ((Activity) context).findViewById(R.id.summaryDialog));

		populateTotalsForPublishers(layout);
		populateTotalsForAuxiliaryPioneer(layout);
		populateTotalsForRegularPioneer(layout);
		populateTotalsForSpecialPioneer(layout);

		AlertDialog summaryDialog = new AlertDialog.Builder(context).create();
		summaryDialog.setView(layout);
		summaryDialog.show();

	}

	private void populateTotalsForPublishers(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(context, ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.PU.toString()));

		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursPublisher);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsPublisher);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingPublisher);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsPublisher);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesPublisher);

		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForAuxiliaryPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(context, ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.AP.toString()));

		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursAuxiliaryPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsAuxiliaryPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingAuxiliaryPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsAuxiliaryPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesAuxiliaryPioneer);

		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForRegularPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(context, ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.RP.toString()));

		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursRegularPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsRegularPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingRegularPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsRegularPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesRegularPioneer);

		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForSpecialPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(context, ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.SP.toString()));

		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursSpecialPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsSpecialPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingSpecialPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsSpecialPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesSpecialPioneer);

		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateFieldsFromList(List<Report> list, TextView textViewTotalHours, TextView textViewTotalPlacements, TextView textViewTotalVideoShowing,
			TextView textViewTotalReturnVisits, TextView textViewTotalStudies) {
		Double totalHours = 0.0;
		Integer totalPlacements = 0;
		Integer totalVideoShowing = 0;
		Integer totalReturnVisits = 0;
		Integer totalStudies = 0;

		for (Report report : list) {
			totalHours += report.getHours();
			totalPlacements += report.getPlacements();
			totalVideoShowing += report.getVideoShowings();
			totalReturnVisits += report.getReturnVisits();
			totalStudies += report.getStudies();
		}

		textViewTotalHours.setText(totalHours.toString());
		textViewTotalPlacements.setText(totalPlacements.toString());
		textViewTotalVideoShowing.setText(totalVideoShowing.toString());
		textViewTotalReturnVisits.setText(totalReturnVisits.toString());
		textViewTotalStudies.setText(totalStudies.toString());
	}
}
