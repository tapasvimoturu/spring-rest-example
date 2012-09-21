package com.mkyong.common.controller;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public final class Movie {

	private String name;
	private String rating;
	private String director;
	
	Movie(String n, String r, String d ){
		name = n;
		rating = r;
		director = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}
	
}
