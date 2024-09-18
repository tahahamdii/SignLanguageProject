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
    private String imageId;

    public Image(String name,String imageUrl,String imageId){
        this.name=name;
        this.imageUrl=imageUrl;
        this.imageId = imageId;
    }
}
