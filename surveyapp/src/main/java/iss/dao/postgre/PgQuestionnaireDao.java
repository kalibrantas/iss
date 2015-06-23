package iss.dao.postgre;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;

import iss.message.QuestionMessage;
import iss.message.QuestionnaireAssetMessage;
import iss.model.Question;

import javax.sql.DataSource;

import org.postgresql.jdbc4.Jdbc4Array;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import scala.annotation.meta.getter;

public class PgQuestionnaireDao extends PgDatabaseOperation {

	public PgQuestionnaireDao(DataSource dataSource) {
		super(dataSource);
	}

	public boolean saveQuestionnaireAsset(
			QuestionnaireAssetMessage questionnaireAsset) {
		ArrayList<QuestionMessage> questions = questionnaireAsset
				.getQuestionList();
		ArrayList<ArrayList<QuestionMessage>> tempQuestionsDeep = new ArrayList<ArrayList<QuestionMessage>>();
		tempQuestionsDeep.add(questions);
		int[] i = new int[1000];
		int deep = 0;

		begin();
		while (true) {
			if (i[deep] < tempQuestionsDeep.get(deep).size()) {
				QuestionMessage qm = tempQuestionsDeep.get(deep).get(i[deep]);
				if (qm.getType().equals("group")) {
					i[deep]++;
					tempQuestionsDeep.add(qm.getChildren());
					insertQuestionGroup(qm, sec(deep, i),
							createChildrenNameArray(qm.getChildren()));
					
					deep++;
					continue;
				} else {
					i[deep]++;
					insertQuestion(qm, sec(deep, i));
				}
				
			} else {
				if (deep > 0) {
					tempQuestionsDeep.remove(deep);
					i[deep]=0;
					deep--;
				} else {
					break;
				}
			}
		}
		commit();
		//
		// questions.forEach(new Consumer<QuestionMessage>() {
		//
		// @Override
		// public void accept(QuestionMessage q) {
		// // TODO Auto-generated method stub
		// System.out.println(q.getName());
		// executeTransaction("INSERT INTO questions(name, type, label) values(?,?,?)",
		// q.getName(),q.getType(),q.getLabel());
		// }
		// });
		//
		return true;
	}

	public void insertQuestion(QuestionMessage q, String sec) {
		executeTransaction(
				"INSERT INTO questions(name, type, label, sequence) values(?,?,?,?)",
				q.getName(), q.getType(), q.getLabel(), sec);
	}

	public void insertQuestionGroup(QuestionMessage q, String sec,
			String[] childrenArrName) {
		try {
			executeTransaction(
					"INSERT INTO questions(name, type, label,children, sequence) values(?,?,?,?,?)",
					q.getName(), q.getType(), q.getLabel(),
					getJdbcTemplate().getDataSource().getConnection()
							.createArrayOf("text", childrenArrName), sec);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(childrenArrName);
	}

	public String[] createChildrenNameArray(ArrayList<QuestionMessage> questions) {
		// String strNameArr = "";
		// for (int i = 0; i < questions.size(); i++) {
		// if (i == 0)
		// strNameArr += "'{";
		// strNameArr += (i == 0) ? "\"" + questions.get(i).getName() + "\""
		// : ",\"" + questions.get(i).getName() + "\"";
		// if (i == questions.size() - 1)
		// strNameArr += "}'";
		// }

		String[] strNameArr = new String[questions.size()];
		for (int i = 0; i < questions.size(); i++) {
			// if (i == 0)
			// strNameArr += "'{";
			// strNameArr += (i == 0) ? "\"" + questions.get(i).getName() + "\""
			// : ",\"" + questions.get(i).getName() + "\"";
			// if (i == questions.size() - 1)
			// strNameArr += "}'";
			strNameArr[i] = questions.get(i).getName();
		}

		return strNameArr;
	}

	private String sec(int deep, int[] i) {
		String sec = "";
		for (int j = 0; j <= deep; j++) {
			sec = (j == 0) ? sec + i[j] : sec + "." + i[j];

		}
		return sec;
	}

	public QuestionnaireAssetMessage getQuestionnaireAsset(int id_user,
			int id_survey) {
		QuestionnaireAssetMessage qa = new QuestionnaireAssetMessage();

		ArrayList<Question> qrs = (ArrayList<Question>) getJdbcTemplate()
				.query("SELECT * FROM questions ORDER BY sequence ASC", new RowMapper<Question>() {

					@Override
					public Question mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						// TODO Auto-generated method stub
						Question q = new Question(rs.getString("name"), rs
								.getString("type"), rs.getString("label"));
						if(rs.getArray("children")!=null){
							q.setChildren((String[])rs.getArray("children").getArray());
						}
						if(rs.getString("repeat_count")!=null)
							q.setRepeat_count(rs.getString("repeat_count"));

						return q;
					}

				});
		ArrayList<QuestionMessage> questions = new ArrayList<QuestionMessage>();
		// ArrayList<QuestionMessage> questionsTemp = new
		// ArrayList<QuestionMessage>();
		// ArrayList<String[]> childrenNames = new ArrayList<String[]>();
		// childrenNames.add(new String[] {});
		// // QuestionMessage qTemp= new QuestionMessage();
		// int deep = 0;
		try {
			while (qrs.size()!=0) {
				Question q = qrs.get(0);
				qrs.remove(0);
				if (q.getType().equals("group")) {
					QuestionMessage qm = new QuestionMessage(
							q.getName(), q.getType(),
							q.getLabel());
					qm.setChildren(buildChildrenQuestion(qrs, q.getChildren()));
					if(q.getRepeat_count()!=null)
						qm.setRepeat_count(q.getRepeat_count());
					questions.add(qm);
				} else {
					//System.out.println(q.getName());
					questions.add(new QuestionMessage(q.getName(), q.getType(),
							q.getLabel()));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ArrayList<QuestionMessage> questions = (ArrayList<QuestionMessage>)
		// getJdbcTemplate()
		// .query("SELECT * FROM questions WHERE id_question=67",
		// new RowMapper<QuestionMessage>() {
		//
		// @Override
		// public QuestionMessage mapRow(ResultSet rs,
		// int rowNum) throws SQLException {
		// // TODO Auto-generated method stub
		// QuestionMessage q = new QuestionMessage(rs
		// .getString("name"), rs
		// .getString("type"), rs
		// .getString("label"));
		// String []a = (String[]) rs.getArray("children").getArray();
		// return q;
		// }
		//
		// });

		qa.setQuestionList(new ArrayList<QuestionMessage>(questions));

		return qa;
	}

	public ArrayList<QuestionMessage> buildChildrenQuestion(
			ArrayList<Question> rs, String[] arrNames) throws SQLException {
		ArrayList<QuestionMessage> questions = new ArrayList<QuestionMessage>();
		for (int i = 0; i < arrNames.length; i++) {
			for (int j = 0; j < rs.size(); j++) {
				if (rs.get(j).getName().equals(arrNames[i])) {
					if (rs.get(j).getType().equals("group")) {
						Question r = rs.get(j);
						rs.remove(j);
						QuestionMessage qm = new QuestionMessage(
								r.getName(), r.getType(),
								r.getLabel());
						qm.setChildren(buildChildrenQuestion(rs, r.getChildren()));
						if(r.getRepeat_count()!=null)
							qm.setRepeat_count(r.getRepeat_count());
						questions.add(qm);

					} else {
						questions.add(new QuestionMessage(rs.get(j).getName(), rs.get(j).getType(),
								rs.get(j).getLabel()));
						rs.remove(j);

					}
					break;
				}

			}
		}
		return questions;
	}

}
