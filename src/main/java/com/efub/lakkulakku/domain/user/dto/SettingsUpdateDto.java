package com.efub.lakkulakku.domain.user.dto;


import javax.validation.constraints.NotBlank;

public class SettingsUpdateDto {

    @NotBlank
    public String password;
}
