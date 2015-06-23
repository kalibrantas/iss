package iss;

import java.security.Principal;

import iss.dao.postgre.PgQuestionnaireDao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeloController {

	//@Autowired
	JdbcTemplate jdbc;
	PgQuestionnaireDao qd;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbc = new JdbcTemplate(dataSource);
		qd = new PgQuestionnaireDao(dataSource);

	}

	@RequestMapping("/")
	public String a(ModelMap model, Principal p) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = (User) auth.getPrincipal();
		model.addAttribute("mboh", (auth instanceof AnonymousAuthenticationToken));
		return "home/index";
	}

	@RequestMapping("/coba")
	public @ResponseBody String b() {
		//jdbc.execute("insert into question(id, name) values('1', 'aan')");
		//qd.saveQuestionnaireAsset();
		return "helo";
	}

	}
