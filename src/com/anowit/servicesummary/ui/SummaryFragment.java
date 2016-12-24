package com.anowit.servicesummary.ui;

import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Profile;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;
import com.seimos.android.dbhelper.persistence.Filter;
import com.seimos.android.dbhelper.persistence.Restriction;

/**
 * @author moesio @ gmail.com
 * @date Dec 24, 2016 9:13:29 AM
 */
public class SummaryFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(R.string.sum);
		setHasOptionsMenu(true);

		View layout = inflater.inflate(R.layout.summary, container, false);

		populateTotalsForPublishers(layout);
		populateTotalsForAuxiliaryPioneer(layout);
		populateTotalsForRegularPioneer(layout);
		populateTotalsForSpecialPioneer(layout);

		return layout;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
	}

	private void populateTotalsForPublishers(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(getActivity(), ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.PU.toString()));

		TextView textViewTotalPublishers = (TextView) layout.findViewById(R.id.textViewTotalPublishers);
		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursPublisher);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsPublisher);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingPublisher);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsPublisher);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesPublisher);

		textViewTotalPublishers.setText(Integer.toString(list.size()));
		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForAuxiliaryPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(getActivity(), ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.AP.toString()));

		TextView textViewTotalAuxiliaryPioneer = (TextView) layout.findViewById(R.id.textViewTotalAuxiliaryPioneer);
		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursAuxiliaryPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsAuxiliaryPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingAuxiliaryPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsAuxiliaryPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesAuxiliaryPioneer);

		textViewTotalAuxiliaryPioneer.setText(Integer.toString(list.size()));
		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForRegularPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(getActivity(), ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.RP.toString()));

		TextView textViewTotalRegularPioneer = (TextView) layout.findViewById(R.id.textViewTotalRegularPioneer);
		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursRegularPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsRegularPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingRegularPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsRegularPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesRegularPioneer);

		textViewTotalRegularPioneer.setText(Integer.toString(list.size()));
		populateFieldsFromList(list, textViewTotalHours, textViewTotalPlacements, textViewTotalVideoShowing, textViewTotalReturnVisits, textViewTotalStudies);
	}

	private void populateTotalsForSpecialPioneer(View layout) {
		ReportManagerImpl manager = ManagerFactory.getManager(getActivity(), ReportManagerImpl.class);

		List<Report> list = manager.filter(new Filter("profile", Restriction.EQ, Profile.SP.toString()));

		TextView textViewTotalSpecialPioneer = (TextView) layout.findViewById(R.id.textViewTotalSpecialPioneer);
		TextView textViewTotalHours = (TextView) layout.findViewById(R.id.textViewTotalHoursSpecialPioneer);
		TextView textViewTotalPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacementsSpecialPioneer);
		TextView textViewTotalVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowingSpecialPioneer);
		TextView textViewTotalReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisitsSpecialPioneer);
		TextView textViewTotalStudies = (TextView) layout.findViewById(R.id.textViewTotalStudiesSpecialPioneer);

		textViewTotalSpecialPioneer.setText(Integer.toString(list.size()));
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
