package com.springboot.uber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyTriggerInitializer {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MyTriggerInitializer(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void init() {
		jdbcTemplate.execute(
				"CREATE TRIGGER update_last_updated BEFORE UPDATE ON user FOR EACH ROW SET NEW.subscription = 0");

		jdbcTemplate.execute("delimiter $$"
				+ "create trigger update_vehicle_id after update on driver for each row begin update ride set vehicle_id=NEW.vehicle_id WHERE driver_id =NEW.id; END"
				+ "$$" + "delimiter ;");

	}
}