package com.anowit.servicesummary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Add drawLeft/drawStart to a textView
 * 
 * @author moesio @ gmail.com
 * @date Nov 12, 2016 10:54:45 AM
 */
public class TextWithIconArrayAdapter<T> extends ArrayAdapter<T> {

	private int[] mIcons;
	private int mFieldId;

	public TextWithIconArrayAdapter(Context context, int layout, int textViewResourceId, T[] objects, int[] icons) {
		super(context, layout, textViewResourceId, objects);
		this.mFieldId = textViewResourceId;
		this.mIcons = icons;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		TextView text = (TextView) view.findViewById(mFieldId);
		text.setCompoundDrawablesWithIntrinsicBounds(mIcons[position], 0, 0, 0);

		return view;
	}

}
