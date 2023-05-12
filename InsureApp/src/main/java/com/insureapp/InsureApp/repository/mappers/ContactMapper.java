package com.insureapp.InsureApp.repository.mappers;


import com.insurapp.InsureApp.repository.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

;

public class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException{
        Contact contact = new Contact();

        contact.setInquiryId(rs.getInt("inquiry_id"));
        contact.setName(rs.getString("name"));
        contact.setPhone(rs.getString("phone"));
        contact.setEmail(rs.getString("email"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        contact.setStatus(rs.getString("status"));
        contact.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        contact.setCreatedBy(rs.getString("created_by"));

        if (rs.getTimestamp("updated_at") != null)
            contact.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        if (rs.getString("updated_by") != null)
            contact.setUpdatedBy(rs.getString("updated_by"));

        return contact;

    }

}
