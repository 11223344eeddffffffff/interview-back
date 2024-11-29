package org.example.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "hr")
@Entity
public class Interviewer {
    // 注意属性名要与数据表中的字段名一致
    // 主键自增int(10)对应long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // 用户名属性varchar对应String
    private String interviewId;
    // 密码属性varchar对应String
    private String password;
    private String name;

}

