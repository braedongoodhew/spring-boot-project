package ca.sheridancollege.goodhewb.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.goodhewb.beans.Volume;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertVolume() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="INSERT INTO volume (height, width, depth, result)"
				+ " VALUES('10', '10', '10', '1000')";
	
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) 
			System.out.println("Inserted volume into database.");
	}
	
	public void insertVolume(int height, int width, int depth, int result) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="INSERT INTO volume (height, width, depth, result)"
				+ " VALUES(:height, :width, :depth, :result)";
		namedParameters.addValue("height", height);
		namedParameters.addValue("width", width);
		namedParameters.addValue("depth", depth);
		namedParameters.addValue("result", result);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) 
			System.out.println("Inserted volume into database.");
	}

	public List<Volume> getVolumes() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM volume";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Volume>(Volume.class));
	}
	
	public void deleteVolume(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM volume WHERE id = :id";
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Deleted Volume " + id + " from database");
	}
	
	public List<Volume> getVolumeById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM volume WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Volume>(Volume.class));
	}

	
}
