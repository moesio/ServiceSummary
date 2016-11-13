package com.anowit.servicesummary.dao;

import android.content.Context;

import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.dao.GenericDaoImpl;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 2:58:24 PM
 */
public class ReportDaoImpl extends GenericDaoImpl<Report> implements ReportDao {

	/**
	 * @param context
	 */
	public ReportDaoImpl(Context context) {
		super(context);
	}

}
