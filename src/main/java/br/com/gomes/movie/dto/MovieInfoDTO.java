package br.com.gomes.movie.dto;

import java.io.Serializable;

public class MovieInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2814849892062387534L;
	private String year;
	private String title;
	private String studios;
	private String producers;
	private String winner;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudios() {
		return studios;
	}
	public void setStudios(String studios) {
		this.studios = studios;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
}
