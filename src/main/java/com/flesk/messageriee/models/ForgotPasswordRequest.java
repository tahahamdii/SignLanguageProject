package com.flesk.messageriee.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordRequest {
    private String email;

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }
}
