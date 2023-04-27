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

		// Non-conflict Serializable SQL Transactions - check BookingController
		jdbcTemplate.execute("START TRANSACTION;\n"
				+ "insert into vehicle values(590,'1G6DL67A890323058',1,'Maroon','Volkswagen','Passat','petrol','sedan',1);\n"
				+ "COMMIT;");

		// Non-conflict Serializable SQL Transactions - check BookingController
		jdbcTemplate.execute("START TRANSACTION;\n"
				+ "insert into driver values(520,'1825-59-4713','Stockton','Trailsway','95210','California','407',47,'01GRVQ8A61K5BN2GAAV2BDTH20','hroycroft0@dmoz.org',4,'Harriot','F','Roycroft','Harriot@95210Roycroft','209-359-9110',1,590);\n"
				+ "Delete from vehicle where id = 590;\n" + "COMMIT;");

	}
}