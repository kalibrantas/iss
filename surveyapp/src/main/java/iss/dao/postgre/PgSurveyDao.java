package iss.dao.postgre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import iss.message.Survey;
import iss.message.User;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

public class PgSurveyDao extends PgDatabaseOperation{

	public PgSurveyDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public boolean createNewSurvey(Survey survey){
		begin();
		executeTransaction("INSERT INTO surveys (id_user,name) values(?,?)", survey.getId_user() ,survey.getName());
		commit();
		return true;
	}
	
	public ArrayList<Survey> getAllSurvey(User user){
		ArrayList<Survey> surveys= (ArrayList<Survey>)getJdbcTemplate().query("SELECT * FROM surveys WHERE id_user="+user.getId_user(), new RowMapper<Survey>() {

			@Override
			public Survey mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				Survey s = new Survey();
				s.setName(rs.getString("name"));
				s.setId_survey(rs.getInt("id_survey"));
				s.setId_user(rs.getInt("id_user"));
				return s;
			}
		});
		return surveys;
	}
	
	
}
