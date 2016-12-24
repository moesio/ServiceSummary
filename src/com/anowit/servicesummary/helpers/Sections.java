package com.anowit.servicesummary.helpers;

import android.app.Fragment;

/**
 * @author moesio @ gmail.com
 * @date Nov 5, 2016 11:02:07 AM
 */
public class Sections {

	private int currentSession;
	private String[] titles;
	private Integer[] menus;
	private Fragment[] fragments;

	@SuppressWarnings("unused")
	private Sections() {
	}

	public Sections(String[] titles, Fragment[] fragments, Integer[] menus) {
		this.titles = titles;
		if (fragments.length != titles.length) {
			throw new IllegalArgumentException("number of layouts different from number of sections");
		} else {
			this.fragments = fragments;
		}
		this.menus = menus;
	}

	public void setCurrentSession(int currentSession) {
		this.currentSession = currentSession;
	}

	public String[] getTitles() {
		return titles;
	}

	public String getCurrentTitle() {
		return titles[currentSession];
	}

	public Integer getCurrentMenuOption() {
		return menus[currentSession];
	}

	public Fragment getCurrentFragment() {
		return fragments[currentSession];
	}

}
