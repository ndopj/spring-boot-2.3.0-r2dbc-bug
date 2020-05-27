package com;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("model")
public class Model {

    @Id private Long id;
    
    public Model() {}

    public Model(Long id) {
        this.id = id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }
}
