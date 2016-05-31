package CRUD.DAO;

import CRUD.mappers.standard.SubGroupMapper;
import CRUD.tables.standard.SubGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class SubGroupDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public SubGroupDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`subgroup`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("SubGroup");
    }

    public List<SubGroup> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from SubGroup", new SubGroupMapper());
    }

    public List<SubGroup> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select \"OID\", \"Group\" as GroupOID from \"SubGroup\"", new SubGroupMapper());
    }

    public void addListMySQL(List<SubGroup> list) {
        for (SubGroup entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<SubGroup> list) {
        for (SubGroup entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(SubGroup record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("GroupOID", record.getGroup())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(SubGroup record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Group", record.getGroup())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}