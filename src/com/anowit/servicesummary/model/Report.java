package com.anowit.servicesummary.model;

import com.seimos.android.dbhelper.database.BaseEntity;

/**
 * @author moesio @ gmail.com
 * @date Nov 13, 2016 2:51:07 PM
 */
public class Report extends BaseEntity {

	private String name;
	private Double hours;
	private Integer placements;
	private Integer videoShowings;
	private Integer returnVisits;
	private Integer studies;

	public String getName() {
		return name;
	}

	public Report setName(String name) {
		this.name = name;
		return this;
	}

	public Double getHours() {
		return hours;
	}

	public Report setHours(Double hours) {
		this.hours = hours;
		return this;
	}

	public Integer getPlacements() {
		return placements;
	}

	public Report setPlacements(Integer placements) {
		this.placements = placements;
		return this;
	}

	public Integer getVideoShowings() {
		return videoShowings;
	}

	public Report setVideoShowings(Integer videoShowings) {
		this.videoShowings = videoShowings;
		return this;
	}

	public Integer getReturnVisits() {
		return returnVisits;
	}

	public Report setReturnVisits(Integer returnVisits) {
		this.returnVisits = returnVisits;
		return this;
	}

	public Integer getStudies() {
		return studies;
	}

	public Report setStudies(Integer studies) {
		this.studies = studies;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.seimos.android.dbhelper.database.BaseEntity#toString()
	 */
	@Override
	public String toString() {
		return null;
	}

}
