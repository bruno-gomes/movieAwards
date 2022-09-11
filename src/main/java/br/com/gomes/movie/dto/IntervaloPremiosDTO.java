/**
 * 
 */
package br.com.gomes.movie.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author gomes.bruno
 *
 */
public class IntervaloPremiosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6126340476678023471L;
	private List<ResultadoPremioDTO> min;
	private List<ResultadoPremioDTO> max;
	
	
	public List<ResultadoPremioDTO> getMin() {
		return min;
	}

	public void setMin(List<ResultadoPremioDTO> min) {
		this.min = min;
	}

	public List<ResultadoPremioDTO> getMax() {
		return max;
	}

	public void setMax(List<ResultadoPremioDTO> max) {
		this.max = max;
	}

}
