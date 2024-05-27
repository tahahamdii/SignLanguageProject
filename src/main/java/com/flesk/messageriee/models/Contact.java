package com.flesk.messageriee.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Contact {
    @Id
    private String id;
    private String email;


}
