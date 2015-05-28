package iss;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeloController {

	//@Autowired
	JdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbc = new JdbcTemplate(dataSource);

	}

	@RequestMapping("/")
	public String a() {
		return "home/index";
	}

	@RequestMapping("/coba")
	public String b() {
		jdbc.execute("insert into question(id, name) values('1', 'aan')");
		return "helo";
	}
}
