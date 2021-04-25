package com.hbu.backend.model.entity;

import lombok.Data;
import com.hbu.backend.model.dto.UserDTO;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;


@Data
@Entity
@Table(name="admin")
public class Admin extends User {
    // does this need some member attribute that's different than the super class?
    private String referenceCode;
}
