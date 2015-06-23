package iss.dao.postgre;

import java.sql.ResultSet;
import java.sql.SQLException;

import iss.message.User;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

public class PgUserDao extends PgDatabaseOperation{

	public PgUserDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public User getUser(String username){
		User user= getJdbcTemplate().queryForObject("SELECT * FROM users WHERE email=?", new String[]{username}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User u = new User();
				u.setRole(rs.getString("role"));
				u.setId_user(rs.getInt("id_user"));
				return u;
			}});
		return user;
	}

}
