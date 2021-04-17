package com.hbu.backend.model.entity.course.component;

import com.hbu.backend.model.entity.File;
import com.hbu.backend.model.entity.course.Component;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class FileComponent extends Component {
    @OneToMany
    private List<File> files;
}
