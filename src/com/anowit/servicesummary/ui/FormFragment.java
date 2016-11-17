package com.anowit.servicesummary.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author moesio @ gmail.com
 * @date Nov 15, 2016 11:55:02 AM
 */
public class FormFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.form, container, false);
		return view;
	}
}
