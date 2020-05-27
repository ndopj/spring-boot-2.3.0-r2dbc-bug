package com.reactive;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("model")
public class ReactiveModel {

    @Id
    @Column("id")
    private Long id;

    @Column("name") private String name;

    public ReactiveModel() { name = "testname"; }

    public ReactiveModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
