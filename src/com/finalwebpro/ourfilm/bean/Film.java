package com.finalwebpro.ourfilm.bean;

public class Film {
	private int id;
	private String name;
	private String distributor;
	private String genre;
	private String year;
	private String country;
	private String duration;
	private String trailer;
	
	public Film() {
		super();
	}
	
	public Film(String name, String distributor, String genre, String year, String country, String duration,
			String trailer) {
		super();
		this.name = name;
		this.distributor = distributor;
		this.genre = genre;
		this.year = year;
		this.country = country;
		this.duration = duration;
		this.trailer = trailer;
	}

	public Film(int id, String name, String distributor, String genre, String year, String country, String duration,
			String trailer) {
		super();
		this.id = id;
		this.name = name;
		this.distributor = distributor;
		this.genre = genre;
		this.year = year;
		this.country = country;
		this.duration = duration;
		this.trailer = trailer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setContry(String country) {
		this.country = country;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	
	
}