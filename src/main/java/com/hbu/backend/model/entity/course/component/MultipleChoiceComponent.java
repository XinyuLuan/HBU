package com.hbu.backend.model.entity.course.component;

import com.hbu.backend.model.entity.course.Component;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import java.util.Map;

@Entity
@Data
public class MultipleChoiceComponent extends Component {

    @ElementCollection
//    @CollectionTable(name = "order_item_mapping",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "choice")
    @Column(name = "choosen_or_not")
    Map<String, Boolean> choices;
}
