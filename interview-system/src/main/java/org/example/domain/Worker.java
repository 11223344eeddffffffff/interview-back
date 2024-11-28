package org.example.domain;


import com.alibaba.fastjson2.annotation.JSONField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "worker")
@Entity
public class Worker {
    @Id
    @JSONField(ordinal = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JSONField(ordinal = 1)
    private String workerID;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String password;
    @JSONField(ordinal = 4)
    private String address;
    @JSONField(ordinal = 5)
    private String image;
}
