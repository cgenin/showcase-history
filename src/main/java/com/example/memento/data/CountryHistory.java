package com.example.memento.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class CountryHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer beanId;

    @Column(nullable = false)
    private String oldName;

    @Column(nullable = false)
    private String NewName;

    public CountryHistory() {
    }

    public CountryHistory(Integer beanId, String oldName, String newName) {
        this.beanId = beanId;
        this.oldName = oldName;
        this.NewName = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CountryHistory that = (CountryHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeanId() {
        return beanId;
    }

    public void setBeanId(Integer beanId) {
        this.beanId = beanId;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return NewName;
    }

    public void setNewName(String newName) {
        NewName = newName;
    }
}
