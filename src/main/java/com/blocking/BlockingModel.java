package com.blocking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "blocking_model")
public class BlockingModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BlockingModel() {}

    public BlockingModel(Long id) {
        this.id = id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }
}
