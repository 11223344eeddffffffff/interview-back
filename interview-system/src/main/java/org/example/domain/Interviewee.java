package org.example.domain;


import com.alibaba.fastjson2.annotation.JSONField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "interviewee")
@Entity
public class Interviewee {
    @Id
    @JSONField(ordinal = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JSONField(ordinal = 1)
    private String intervieweeId;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String password;
    @JSONField(ordinal = 4)
    private String profile;

}
