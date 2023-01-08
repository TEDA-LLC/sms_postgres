package com.example.sms_postgres.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageDTO {
    private String text;

    private String phone, statusType;
}
