package com.gruns.wrr.wod.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wod")
public class Wod {

    @Id
    private String id;
    private String name;
    private String box;
    private String description;
    private int likes;
}
