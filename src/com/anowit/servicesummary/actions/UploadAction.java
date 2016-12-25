package com.anowit.servicesummary.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;

import com.anowit.servicesummary.R;
import com.anowit.servicesummary.helpers.ActionMenu;
import com.anowit.servicesummary.manager.ReportManagerImpl;
import com.anowit.servicesummary.model.Report;
import com.anowit.servicesummary.ui.MainActivity;
import com.seimos.android.dbhelper.factory.ManagerFactory;
import com.seimos.android.dbhelper.util.Application;

/**
 * @author moesio @ gmail.com
 * @date Nov 17, 2016 6:53:37 PM
 */
public class UploadAction extends ActionMenu {

	private static final int REQUEST_ENABLE_BT = 1;
	private List<Report> reportList;
	private UUID MY_UUID;
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	public UploadAction(Context context) {
		super(context);

		//		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		//		String uuid = tManager.getDeviceId();

		Random random = new Random(System.currentTimeMillis());
		MY_UUID = new UUID(Math.abs(random.nextInt()), Math.abs(random.nextInt()));
	}

	@Override
	public void runAction() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			((Activity) context).startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

			for (BluetoothDevice bluetoothDevice : pairedDevices) {
				bluetoothDevice.getName();
			}
		}
	}

	private class AcceptThread extends Thread {
		private final BluetoothServerSocket mmServerSocket;

		public AcceptThread() {
			// Use a temporary object that is later assigned to mmServerSocket,
			// because mmServerSocket is final
			BluetoothServerSocket tmp = null;
			try {
				// MY_UUID is the app's UUID string, also used by the client code
				tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(Application.getName(context), MY_UUID);
			} catch (IOException e) {
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

		/** Will cancel the listening socket, and cause the thread to finish */
		public void cancel() {
			try {
				mmServerSocket.close();
			} catch (IOException e) {
			}
		}
	}

	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			// Use a temporary object that is later assigned to mmSocket,
			// because mmSocket is final
			BluetoothSocket tmp = null;
			mmDevice = device;

			// Get a BluetoothSocket to connect with the given BluetoothDevice
			try {
				// MY_UUID is the app's UUID string, also used by the server code
				tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
			} catch (IOException e) {
			}
			mmSocket = tmp;
		}

		public void run() {
			// Cancel discovery because it will slow down the connection
			mBluetoothAdapter.cancelDiscovery();

			try {
				// Connect the device through the socket. This will block
				// until it succeeds or throws an exception
				mmSocket.connect();
			} catch (IOException connectException) {
				// Unable to connect; close the socket and get out
				try {
					mmSocket.close();
				} catch (IOException closeException) {
				}
				return;
			}

			// Do work to manage the connection (in a separate thread)
			manageConnectedSocket(mmSocket);
		}

		/** Will cancel an in-progress connection, and close the socket */
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}

	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			// Get the input and output streams, using temp objects because
			// member streams are final
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		public void run() {
			byte[] buffer = new byte[1024]; // buffer store for the stream
			int bytes; // bytes returned from read()

			// Keep listening to the InputStream until an exception occurs
			while (true) {
				try {
					// Read from the InputStream
					bytes = mmInStream.read(buffer);
					// Send the obtained bytes to the UI activity
					//	                mHandler.obtainMessage("mensagem", bytes, -1, buffer)
					//	                        .sendToTarget();
				} catch (IOException e) {
					break;
				}
			}
		}

		/* Call this from the main activity to send data to the remote device */
		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
			}
		}

		/* Call this from the main activity to shutdown the connection */
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @param mmSocket
	 */
	public void manageConnectedSocket(BluetoothSocket mmSocket) {
		int a = 1;
	}
}
