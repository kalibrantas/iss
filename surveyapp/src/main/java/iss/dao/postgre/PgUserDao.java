package iss.dao.postgre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import iss.message.Survey;
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
	
	public boolean saveUser(User user){
		begin();
		executeTransaction("INSERT INTO users(first_name,last_name,email,password,role, enabled) values(?,?,?,?,?,TRUE)", user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.getRole());
		commit();
		return true;
	}
	
	public ArrayList<User> getAllUser(){
		ArrayList<User> users= (ArrayList<User>)getJdbcTemplate().query("SELECT * FROM users", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				User u = new User();
				u.setFirst_name(rs.getString("first_name"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getString("role"));
				return u;
			}
		});
		return users;
	}

}
