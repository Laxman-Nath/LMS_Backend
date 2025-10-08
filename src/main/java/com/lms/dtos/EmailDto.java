package com.lms.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailDto implements Serializable{
    private String to;
    private String subject;
    private String body;
    public EmailDto(String to,String subject,String body){
        this.to=to;
        this.subject=subject;
        this.body=body;
    }
}
