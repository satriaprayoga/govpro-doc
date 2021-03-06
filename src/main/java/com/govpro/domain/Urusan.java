package com.govpro.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Urusan implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long code;
	
	private String name;
	
	private Fungsi fungsi;
	
	private Set<Organisasi> organisasis=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setFungsi(Fungsi fungsi) {
		this.fungsi = fungsi;
	}
	
	public Fungsi getFungsi() {
		return fungsi;
	}
	
	public void setOrganisasis(Set<Organisasi> organisasis) {
		this.organisasis = organisasis;
	}
	
	public Set<Organisasi> getOrganisasis() {
		return organisasis;
	}

	@Override
	public String toString() {
		return "Urusan {id=" + id + ", code=" + code + ", name=" + name + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Urusan other = (Urusan) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
