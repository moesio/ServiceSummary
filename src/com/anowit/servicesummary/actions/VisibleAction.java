package com.anowit.servicesummary.actions;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;

/**
 * @author moesio @ gmail.com
 * @date Jan 8, 2017 6:30:36 PM
 */
public class VisibleAction extends ActionMenu {

	private BluetoothAdapter mBluetoothAdapter;

	public VisibleAction(Context context) {
		super(context);

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (mBluetoothAdapter == null) {
			Toast.makeText(context, R.string.bluetooth_is_not_available, Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void runAction() {
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			((Activity) context).startActivity(discoverableIntent);
		}
	}
}
