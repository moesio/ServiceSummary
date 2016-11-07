package com.anowit.servicesummary.helpers;

/**
 * @author moesio @ gmail.com
 * @date Nov 5, 2016 11:02:07 AM
 */
public class Sections {

	private int currentSession;
	private String[] titles;
	private int[] layouts;
	private int[] menus;

	@SuppressWarnings("unused")
	private Sections() {
	}

	public Sections(String[] titles, int[] layouts, int[] menus) {
		this.titles = titles;
		if (layouts.length != titles.length) {
			throw new IllegalArgumentException("number of layouts different from number of sections");
		} else {
			this.layouts = layouts;
		}
		this.menus = menus;
	}

	public String[] getTitles() {
		return titles;
	}
	
	public void setCurrentSession(int currentSession) {
		this.currentSession = currentSession;
	}

	public int getCurrentLayout() {
		return layouts[currentSession - 1];
	}

	public int getCurrentMenuOption() {
		return menus[currentSession - 1];
	}
}
