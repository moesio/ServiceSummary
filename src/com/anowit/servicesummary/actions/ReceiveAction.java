package com.anowit.servicesummary.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.anowit.servicesummary.helpers.ActionMenu;
import com.seimos.android.dbhelper.util.Application;

/**
 * @author moesio @ gmail.com
 * @date Jan 8, 2017 9:27:09 AM
 */
public class ReceiveAction extends ActionMenu {

	private UUID MY_UUID;
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private AcceptThread acceptThread;

	public ReceiveAction(Context context) {
		super(context);
	}

	@Override
	public void runAction() {
		if (MY_UUID == null) {
			MY_UUID = getUUID();
		}
		if (acceptThread == null) {
			acceptThread = new AcceptThread();
			Toast.makeText(context, "Aceitando relatórios", Toast.LENGTH_SHORT).show();
			acceptThread.run();
		} else {
			acceptThread.cancel();
			Toast.makeText(context, "Desligando recepção de relatórios", Toast.LENGTH_SHORT).show();
		}
	}

	private UUID getUUID() {
		TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String tmSerial = teleManager.getSimSerialNumber();
		String tmDeviceId = teleManager.getDeviceId();
		String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		if (tmSerial == null)
			tmSerial = "1";
		if (tmDeviceId == null)
			tmDeviceId = "1";
		if (androidId == null)
			androidId = "1";
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDeviceId.hashCode() << 32) | tmSerial.hashCode());
		//	    String uniqueId = deviceUuid.toString();
		//	    return uniqueId;
		return deviceUuid;
	}

	private class AcceptThread extends Thread {
		private final BluetoothServerSocket mmServerSocket;
		private byte[] buffer = new byte[1024];

		public AcceptThread() {
			// Use a temporary object that is later assigned to mmServerSocket,
			// because mmServerSocket is final
			BluetoothServerSocket tmp = null;
			try {
				// MY_UUID is the app's UUID string, also used by the client code
				tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(Application.getName(context), MY_UUID);
			} catch (IOException e) {
				Log.e("ServiceSummary", e.getMessage());
			}
			mmServerSocket = tmp;
		}

		public void run() {
			BluetoothSocket socket = null;
			// Keep listening until exception occurs or a socket is returned
			while (true) {
				try {
					socket = mmServerSocket.accept();
				} catch (IOException e) {
					break;
				}
				// If a connection was accepted
				if (socket != null) {
					// Do work to manage the connection (in a separate thread)
					manageConnectedSocket(socket);
					try {
						mmServerSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}

		private void manageConnectedSocket(BluetoothSocket socket) {
			try {
				InputStream inputStream = socket.getInputStream();
				int bytes = inputStream.read(buffer);
				if (bytes > 0) {
					Log.d("ServiceSummary", "ok");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//			Toast.makeText(context, "seinão", Toast.LENGTH_SHORT).show();
		}

		/** Will cancel the listening socket, and cause the thread to finish */
		public void cancel() {
			try {
				mmServerSocket.close();
			} catch (IOException e) {
			}
		}
	}

}
