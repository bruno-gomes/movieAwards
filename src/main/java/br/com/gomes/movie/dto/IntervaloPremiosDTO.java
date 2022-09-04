/**
 * 
 */
package br.com.gomes.movie.dto;

import java.util.List;

/**
 * @author gomes.bruno
 *
 */
public class IntervaloPremiosDTO {
	
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
