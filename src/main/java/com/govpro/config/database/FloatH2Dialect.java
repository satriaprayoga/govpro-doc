package com.govpro.config.database;

import java.sql.Types;

import org.hibernate.dialect.H2Dialect;

public class FloatH2Dialect extends H2Dialect{

	public FloatH2Dialect() {
		super();
		registerColumnType(Types.FLOAT, "real");
	}
}
