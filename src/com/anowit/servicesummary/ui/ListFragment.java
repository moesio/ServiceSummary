package com.anowit.servicesummary.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Profile;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.factory.ManagerFactory;

/**
 * @author moesio @ gmail.com
 * @date Nov 15, 2016 11:40:28 AM
 */
public class ListFragment extends Fragment implements android.view.View.OnClickListener {

	private ReportManagerImpl reportManager;
	private List<Report> reportList = new ArrayList<Report>();
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list, container, false);

		ImageView imgHours = (ImageView) view.findViewById(R.id.imgHours);
		ImageView imgPlacements = (ImageView) view.findViewById(R.id.imgPlacements);
		ImageView imgVideoShowing = (ImageView) view.findViewById(R.id.imgVideoShowing);
		ImageView imgReturnVisits = (ImageView) view.findViewById(R.id.imgReturnVisits);
		ImageView imgStudies = (ImageView) view.findViewById(R.id.imgStudies);

		imgHours.setOnClickListener(this);
		imgPlacements.setOnClickListener(this);
		imgVideoShowing.setOnClickListener(this);
		imgReturnVisits.setOnClickListener(this);
		imgStudies.setOnClickListener(this);

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

				viewHolder.txtProfileAbbreviation = (TextView) convertView.findViewById(R.id.txtProfileAbbreviation);
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

				Profile profile = report.getProfile();
				if (profile != null) {
					int ordinal = profile.ordinal();
					String[] stringArray = getResources().getStringArray(R.array.profile_abbreviation);
					String profileAbbreviation = stringArray[ordinal];
					viewHolder.txtProfileAbbreviation.setText(profileAbbreviation);
					switch (profile) {
					case PU:
						viewHolder.txtProfileAbbreviation.setBackgroundColor(0x5d3c8900);
						break;
					case AP:
						viewHolder.txtProfileAbbreviation.setBackgroundColor(0x89483c00);
						break;
					case RP:
						viewHolder.txtProfileAbbreviation.setBackgroundColor(0x43893c00);
						break;
					case SP:
						viewHolder.txtProfileAbbreviation.setBackgroundColor(0x3c588900);
						break;
					}
				}
				viewHolder.txtName.setText(report.getName());
				viewHolder.txtHours.setText(String.format("%1$.2f", report.getHours()));
				viewHolder.txtPlacements.setText(report.getPlacements().toString());
				viewHolder.txtVideoShowings.setText(report.getVideoShowings().toString());
				viewHolder.txtReturnVisits.setText(report.getReturnVisits().toString());
				viewHolder.txtStudies.setText(report.getStudies().toString());
			}

			int color;
			if (position % 2 == 0) {
				color = 0xccffffff;
			} else {
				color = 0x00ffffff;
			}
			convertView.setBackgroundColor(color);

			return convertView;
		}

		@Override
		public long getItemId(int position) {
			return reportList.get(position).getId();
		}

		@Override
		public Object getItem(int position) {
			return reportList.get(position);
		}

		@Override
		public int getCount() {
			return reportList.size();
		}

		@Override
		public void notifyDataSetChanged() {
			reportList = reportManager.list();
			super.notifyDataSetChanged();
		}

	}

	/**
	 * @author moesio @ gmail.com
	 * @date Nov 15, 2016 10:19:23 PM
	 */
	private class ClickItemListener implements OnItemClickListener {

		@Override
		public void onItemClick(final AdapterView<?> parent, View view, int position, final long id) {

			LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.details, (ViewGroup) ((Activity) getActivity()).findViewById(R.id.detailsDialog));

			final AlertDialog itemDetail = new AlertDialog.Builder(getActivity()).create();
			itemDetail.setView(layout);
			TextView txtViewName = (TextView) layout.findViewById(R.id.textViewName);
			TextView txtViewHours = (TextView) layout.findViewById(R.id.textViewTotalHours);
			TextView txtViewPlacements = (TextView) layout.findViewById(R.id.textViewTotalPlacements);
			TextView txtViewVideoShowing = (TextView) layout.findViewById(R.id.textViewTotalVideoShowing);
			TextView txtViewReturnVisits = (TextView) layout.findViewById(R.id.textViewTotalReturnVisits);
			TextView txtViewStudies = (TextView) layout.findViewById(R.id.textViewTotalStudies);
			ImageButton btnDelete = (ImageButton) layout.findViewById(R.id.btnDelete);

			Report report = reportManager.retrieve(id);

			txtViewName.setText(report.getName());
			txtViewHours.setText(String.format("%1$.2f", report.getHours()));
			txtViewPlacements.setText(report.getPlacements().toString());
			txtViewVideoShowing.setText(report.getVideoShowings().toString());
			txtViewReturnVisits.setText(report.getReturnVisits().toString());
			txtViewStudies.setText(report.getStudies().toString());

			OnClickListener listener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case AlertDialog.BUTTON_POSITIVE:
						reportManager.delete(id);
						itemDetail.dismiss();
						((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
						break;
					default:
						break;
					}
				}
			};

			final AlertDialog confirmDeleteAlert = new AlertDialog.Builder(getActivity()).create();
			confirmDeleteAlert.setMessage(getActivity().getResources().getString(R.string.delete_one));
			confirmDeleteAlert.setButton(AlertDialog.BUTTON_POSITIVE, getActivity().getResources().getString(android.R.string.yes), listener);
			confirmDeleteAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getActivity().getResources().getString(android.R.string.no), listener);

			android.view.View.OnClickListener a = new android.view.View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					confirmDeleteAlert.show();
				}
			};
			btnDelete.setOnClickListener(a);
			
			itemDetail.show();
		}

	}

	private static class ViewHolder {
		TextView txtProfileAbbreviation;
		TextView txtName;
		TextView txtHours;
		TextView txtPlacements;
		TextView txtVideoShowings;
		TextView txtReturnVisits;
		TextView txtStudies;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity(), ((ImageView) v).getContentDescription(), Toast.LENGTH_SHORT).show();
	}
}
