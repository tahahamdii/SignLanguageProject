package com.flesk.messageriee.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    private String id;
    private String name;
    private String imageUrl;


    public Image(String name,String imageUrl){
        this.name=name;
        this.imageUrl=imageUrl;
    }
}
