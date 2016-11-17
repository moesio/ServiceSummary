package com.anowit.servicesummary.manager;

import android.content.Context;

import com.anowit.servicesummary.dao.ReportDao;
import com.anowit.servicesummary.dao.ReportDaoImpl;
import com.anowit.servicesummary.model.Report;
import com.seimos.android.dbhelper.dao.GenericDao;
import com.seimos.android.dbhelper.manager.GenericManagerImpl;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 3:00:40 PM
 */
public class ReportManagerImpl extends GenericManagerImpl<Report, ReportDao> implements ReportManager {

	private ReportDao dao;

	/**
	 * @param context
	 */
	public ReportManagerImpl(Context context) {
		super(context);
		this.dao = new ReportDaoImpl(context);
	}

	@Override
	public GenericDao<Report> getDao() {
		return dao;
	}

}
