package com.hbu.backend.model.entity.course.component;

import com.hbu.backend.model.entity.course.Component;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
public class TextComponent extends Component {

    @Nationalized
    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}
