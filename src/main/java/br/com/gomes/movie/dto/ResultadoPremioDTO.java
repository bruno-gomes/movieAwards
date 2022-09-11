package br.com.gomes.movie.dto;

import java.io.Serializable;

public class ResultadoPremioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7438428877899508231L;
	private String producer;
	private int interval;
	private int previusWin;
	private int followingWin;
	
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getPreviusWin() {
		return previusWin;
	}
	public void setPreviusWin(int previusWin) {
		this.previusWin = previusWin;
	}
	public int getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}
	
	
}
