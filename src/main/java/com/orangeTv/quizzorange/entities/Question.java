package com.orangeTv.quizzorange.entities;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data  
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;
    private  int point;

    @OneToMany(targetEntity = Response.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "r_fk", referencedColumnName = "id")
    private List<Response> responses;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    public void setStartDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        this.startDate = calendar.getTime();
    }
    public Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,+7);
        return calendar.getTime();
    }
    public void setEndDate(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,+7);
        this.endDate = calendar.getTime();
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public Date getSDate(){
        return startDate;
    }
    public Date getEDate(){
        return endDate;
    }
}
