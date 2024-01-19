package com.jd.websocket.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User implements Serializable {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
