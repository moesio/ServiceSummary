package com.anowit.servicesummary.ui;

import java.util.ArrayList;
import java.util.Set;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anowit.servicesummary.R;

/**
 * @author moesio @ gmail.com
 * @date Jan 8, 2017 10:37:19 PM
 */
public class BtDevicesFragment extends Fragment {

	private ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();

	private BluetoothAdapter bluetoothAdapter;

	/**
	 * @author moesio @ gmail.com
	 * @date Jan 9, 2017 6:50:02 PM
	 */
	private static class ViewHolder {
		TextView txtViewDeviceName;
	}

	/**
		 * @author moesio @ gmail.com
		 * @date Jan 9, 2017 6:42:22 PM
		 */
	public class DeviesAdapter extends BaseAdapter {

		public DeviesAdapter() {
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
			for (BluetoothDevice bluetoothDevice : bondedDevices) {
				devices.add(bluetoothDevice);
			}
		}

		@Override
		public int getCount() {
			return devices.size();
		}

		@Override
		public Object getItem(int position) {
			return devices.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;

			//		listView.setAdapter(new ArrayAdapter<Object>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, devices.toArray()));

			if (convertView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

				viewHolder = new ViewHolder();
				viewHolder.txtViewDeviceName = (TextView) convertView.findViewById(android.R.id.text1);
				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.txtViewDeviceName.setText(devices.get(position).getName());
			return convertView;
		}

	}

	private class ItemClickListnener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getActivity(), "" + id, Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.activity_device_list, container, false);

		//		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		//
		//		Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
		//		ArrayList<Device> devices = new ArrayList<Device>();
		//		for (BluetoothDevice bluetoothDevice : bondedDevices) {
		//			devices.add(new Device(bluetoothDevice));
		//		}
		//		listView.setAdapter(new ArrayAdapter<Object>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, devices.toArray()));

		ListView listView = (ListView) layout.findViewById(R.id.paired_devices);
		listView.setAdapter(new DeviesAdapter());
		listView.setOnItemClickListener(new ItemClickListnener());

		Button scanButton = (Button) layout.findViewById(R.id.button_scan);
		scanButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Urru", Toast.LENGTH_LONG).show();
				v.setVisibility(View.GONE);
			}
		});

		return layout;
	}

	private void doDiscovery() {

		// Turn on sub-title for new devices
		getActivity().findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

		// If we're already discovering, stop it
		if (bluetoothAdapter.isDiscovering()) {
			bluetoothAdapter.cancelDiscovery();
		}

		// Request discover from BluetoothAdapter
		bluetoothAdapter.startDiscovery();
	}

}
