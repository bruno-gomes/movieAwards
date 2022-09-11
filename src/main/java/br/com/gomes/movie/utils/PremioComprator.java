package br.com.gomes.movie.utils;

import java.util.Comparator;

import br.com.gomes.movie.entity.Premios;

public class PremioComprator implements Comparator<Premios>{

	@Override
	public int compare(Premios o1, Premios o2) {
		
		if (o1.getYear() < o2.getYear()) {
            return -1;
        }
        if (o1.getYear() > o2.getYear()) {
            return 1;
        }
        return 0;
	}
	
	

}
