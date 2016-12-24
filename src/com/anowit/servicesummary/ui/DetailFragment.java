package com.anowit.servicesummary.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.actions.DeleteAction;
import com.anowit.servicesummary.helpers.ActionMap;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Dec 24, 2016 10:37:07 AM
 */
public class DetailFragment extends Fragment {

	private long id;
	private ReportManagerImpl reportManager = ManagerFactory.getManager(getActivity(), ReportManagerImpl.class);

	public DetailFragment(long id) {
		this.id = id;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);

		ActionMap actionMap = ((MainActivity) getActivity()).getActionMap();
		actionMap.append(R.id.menuDelete, new DeleteAction(getActivity(), id));

		View layout = inflater.inflate(R.layout.details, (ViewGroup) ((Activity) getActivity()).findViewById(R.id.detailsDialog));

		TextView txtViewName = (TextView) layout.findViewById(R.id.textViewName);
		TextView txtViewHours = (TextView) layout.findViewById(R.id.textViewTotalHours);
		TextView txtViewPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacements);
		TextView txtViewVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowing);
		TextView txtViewReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisits);
		TextView txtViewStudies = (TextView) layout.findViewById(R.id.textViewTotalStudies);

		Report report = reportManager.retrieve(id);

		txtViewName.setText(report.getName());
		txtViewHours.setText(String.format("%1$.2f", report.getHours()));
		txtViewPlacements.setText(report.getPlacements().toString());
		txtViewVideoShowing.setText(report.getVideoShowings().toString());
		txtViewReturnVisits.setText(report.getReturnVisits().toString());
		txtViewStudies.setText(report.getStudies().toString());

		return layout;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		//		back((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.detail, menu);
	}

	public ReportManagerImpl getReportManager() {
		return reportManager;
	}

}
