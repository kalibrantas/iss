package iss.dao.postgre;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class PgDatabaseOperation {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private DataSourceTransactionManager transactionManager;
	private TransactionDefinition transactionDef;
	private TransactionStatus transactionStatus;
	private boolean transactionFailedStatus = false;

	public PgDatabaseOperation(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public SimpleJdbcInsert getSimpleJdbcInsert(String tableName, String autoGenerateKeyColumn){
		return new SimpleJdbcInsert(dataSource).withTableName(tableName).usingGeneratedKeyColumns(autoGenerateKeyColumn);
	}

	public void begin() {
		transactionFailedStatus = false;
		transactionManager = new DataSourceTransactionManager(dataSource);
		transactionDef = new DefaultTransactionDefinition();
		transactionStatus = transactionManager.getTransaction(transactionDef);
	}

	public int executeTransaction(String sql) {
		int res = -1;
		if (!transactionFailedStatus) {
			try {
				res=jdbcTemplate.update(sql);
			} catch (DataAccessException e) {
				transactionFailedStatus = true;
				transactionManager.rollback(transactionStatus);
				System.out.println(e.getMessage());
			}
		}
		return res;
	}

	public int executeTransaction(String sql, Object... o) {
		int res = -1;
		if (!transactionFailedStatus) {
			try {
				res = jdbcTemplate.update(sql, o);
			} catch (DataAccessException e) {
				transactionFailedStatus = true;
				transactionManager.rollback(transactionStatus);
				System.out.println(e.getMessage());
			}
		}
		return res;
	}
	
	public int executeTransactionForId(String sql, Object... o){
		
		return 1;
	}

	public int executeTransactionInsert(String tableName, Object... o) {
		String sql = "INSERT INTO " + tableName + " values(";
		for (int i = 0; i < o.length; i++)
			if (i == o.length - 1)
				sql += "?)";
			else
				sql += "?,";
		return executeTransaction(sql, o);
	}

	public void commit() {
		if (!transactionFailedStatus) {
			transactionManager.commit(transactionStatus);
		}
	}

}
