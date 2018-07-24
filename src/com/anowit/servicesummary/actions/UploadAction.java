package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.ui.BtDevicesFragment;
import com.anowit.servicesummary.ui.MainActivity;

/**
 * @author moesio @ gmail.com
 * @date Nov 17, 2016 6:53:37 PM
 */
public class UploadAction extends ActionMenu {

	private BluetoothAdapter mBluetoothAdapter = null;

	public UploadAction(Context context) {
		super(context);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	@Override
	public void runAction() {
		if (mBluetoothAdapter == null) {
			Toast.makeText(context, R.string.bluetooth_is_not_available, Toast.LENGTH_SHORT).show();
		} else if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			((Activity) context).startActivity(enableBtIntent);
		} else {
			FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.container, new BtDevicesFragment()).addToBackStack(null).commit();

			//			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
			//
			//			for (BluetoothDevice bluetoothDevice : pairedDevices) {
			//				bluetoothDevice.getName();
			//			}
		}
	}

}
