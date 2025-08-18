package com.mmauridev.insteelshipping.models;

import jakarta.persistence.*;

@Entity
@Table(name = "containers")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "container_id")
    private Long containerId;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "description", length = 255)
    private String description;

    public Container() {
    }

    public Container(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getContainerId() {
        return containerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
