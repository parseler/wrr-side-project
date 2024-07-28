package com.gruns.wrr.wod.Entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wod")
@Builder
@Getter
public class Wod {

    @Id
    private String id;
    private String name;
    private String box;
    private String description;
    private int likes;
}
