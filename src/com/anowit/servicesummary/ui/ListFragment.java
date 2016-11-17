package com.anowit.servicesummary.ui;

 import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Nov 15, 2016 11:40:28 AM
 */
public class ListFragment extends Fragment {

	private ReportManagerImpl reportManager;
	private List<Report> reportList = new ArrayList<Report>();
	private ListView listView;

	public ListView getListView() {
		return listView;
	}

	public ReportManagerImpl getReportManager() {
		return reportManager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list, container, false);

		listView = (ListView) view.findViewById(R.id.reportList);
		listView.setAdapter(new ListAdapter());

		listView.setOnItemClickListener(new ClickItemListener());

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		reportManager = ManagerFactory.getManager(this.getActivity(), ReportManagerImpl.class);
		reportList = reportManager.list();
		super.onAttach(activity);
	}

	/**
	 * @author moesio @ gmail.com
	 * @date Nov 15, 2016 1:09:11 PM
	 */
	private class ListAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;

			if (convertView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = layoutInflater.inflate(R.layout.report_item_list, parent, false);

				viewHolder = new ViewHolder();

				viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
				viewHolder.txtHours = (TextView) convertView.findViewById(R.id.txtHours);
				viewHolder.txtPlacements = (TextView) convertView.findViewById(R.id.txtPlacements);
				viewHolder.txtVideoShowings = (TextView) convertView.findViewById(R.id.txtVideoShowings);
				viewHolder.txtReturnVisits = (TextView) convertView.findViewById(R.id.txtReturnVisits);
				viewHolder.txtStudies = (TextView) convertView.findViewById(R.id.txtStudies);

				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			Report report = reportList.get(position);
			if (report != null) {
				viewHolder.txtName.setText(report.getName());
				viewHolder.txtHours.setText(String.format("%1$.2f", report.getHours()));
				viewHolder.txtPlacements.setText(report.getPlacements().toString());
				viewHolder.txtVideoShowings.setText(report.getVideoShowings().toString());
				viewHolder.txtReturnVisits.setText(report.getReturnVisits().toString());
				viewHolder.txtStudies.setText(report.getStudies().toString());
			}

			return convertView;
		}

		@Override
		public long getItemId(int position) {
			reportList.get(position).getId();
			return position;
		}

		@Override
		public Object getItem(int position) {
			return reportList.get(position);
		}

		@Override
		public int getCount() {
			return reportList.size();
		}

	}

	/**
	 * @author moesio @ gmail.com
	 * @date Nov 15, 2016 10:19:23 PM
	 */
	private class ClickItemListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(parent.getContext(), ((Report) parent.getItemAtPosition(position)).getId().toString(), Toast.LENGTH_SHORT).show();
		}

	}

	private static class ViewHolder {
		TextView txtName;
		TextView txtHours;
		TextView txtPlacements;
		TextView txtVideoShowings;
		TextView txtReturnVisits;
		TextView txtStudies;
	}
}
