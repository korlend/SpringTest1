package com.korlend.CRUD.mappers.standard;

import com.korlend.CRUD.tables.standard.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class GroupMapper implements RowMapper<Group> {
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setOID(rs.getInt("OID"));
        group.setName(rs.getString("Name"));
        group.setFaculty(rs.getInt("Faculty"));
        group.setCourse(rs.getInt("Course"));
        return group;
    }
}
