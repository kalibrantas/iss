package iss.dao.postgre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import iss.message.QuestionAnswer;
import iss.message.Respondent;

public class PgRespondentDao extends PgDatabaseOperation {

	public PgRespondentDao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	public boolean saveRespondent(Respondent respondent) {
		begin();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_user", respondent.getId_user());
		params.put("id_survey", respondent.getId_survey());
		int id_respondent = getSimpleJdbcInsert("respondents", "id_respondent")
				.executeAndReturnKey(params).intValue();
		System.out.println(id_respondent);
		respondent.getQuestionnaire_answer().forEach(
				new Consumer<QuestionAnswer>() {

					@Override
					public void accept(QuestionAnswer qa) {
						// TODO Auto-generated method stub
						executeTransaction(
								"INSERT INTO respondents_answer(id_question, id_respondent, value) values((SELECT id_question FROM questions WHERE  name=?),?,?)",
								 qa.getName(),
								id_respondent, qa.getAnswer());
					}
				});
		commit();
		return true;
	}
	
	public ArrayList<Respondent> getRespondentSurvey(int id_survey, int id_questionnaire){
		ArrayList<Respondent> respondentList = (ArrayList<Respondent>) getJdbcTemplate().query("SELECT * FROM respondents WHERE id_survey=?",new Object[]{id_survey}, new RowMapper<Respondent>(){
			@Override
			public Respondent mapRow(ResultSet rs, int index)
					throws SQLException {
				// TODO Auto-generated method stub
				Respondent r = new Respondent(rs.getInt("id_respondent"), rs.getInt("id_user"), rs.getInt("id_survey"));
				return r;
			}
		});
		
		return respondentList;
	}

}
