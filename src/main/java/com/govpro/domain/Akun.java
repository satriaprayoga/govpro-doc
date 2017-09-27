package com.govpro.domain;

import java.io.Serializable;

public class Akun implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long rekening;
	
	private String name;
	
	private Integer level;
	
	private Integer type;
	
	private String parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRekening() {
		return rekening;
	}

	public void setRekening(Long rekening) {
		this.rekening = rekening;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rekening == null) ? 0 : rekening.hashCode());
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
		Akun other = (Akun) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rekening == null) {
			if (other.rekening != null)
				return false;
		} else if (!rekening.equals(other.rekening))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Akun [id=" + id + ", rekening=" + rekening + ", name=" + name + ", level=" + level + ", type=" + type
				+ ", parent=" + parent + "]";
	}
	
	
	
	

}
