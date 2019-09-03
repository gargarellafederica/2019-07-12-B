package it.polito.tdp.food.model;

public class Adiacenti implements Comparable<Adiacenti> {
	
	private Integer cibo1;
	private Integer cibo2;
	private double media;
	
	public Adiacenti(Integer cibo1, Integer cibo2, double media) {
		super();
		this.cibo1 = cibo1;
		this.cibo2 = cibo2;
		this.media = media;
	}

	public Integer getCibo1() {
		return cibo1;
	}

	public void setCibo1(Integer cibo1) {
		this.cibo1 = cibo1;
	}

	public Integer getCibo2() {
		return cibo2;
	}

	public void setCibo2(Integer cibo2) {
		this.cibo2 = cibo2;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cibo1 == null) ? 0 : cibo1.hashCode());
		result = prime * result + ((cibo2 == null) ? 0 : cibo2.hashCode());
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
		Adiacenti other = (Adiacenti) obj;
		if (cibo1 == null) {
			if (other.cibo1 != null)
				return false;
		} else if (!cibo1.equals(other.cibo1))
			return false;
		if (cibo2 == null) {
			if (other.cibo2 != null)
				return false;
		} else if (!cibo2.equals(other.cibo2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adiacenti [cibo1=" + cibo1 + ", cibo2=" + cibo2 + ", media=" + media + "]";
	}

	@Override
	public int compareTo(Adiacenti a) {
		return Double.compare(a.getMedia(), this.getMedia());
	}
 
}
