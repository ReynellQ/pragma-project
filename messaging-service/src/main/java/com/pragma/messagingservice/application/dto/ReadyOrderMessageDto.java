package com.pragma.messagingservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadyOrderMessageDto {
    private Long idOrder;
    private String restaurant;
    private String phone;
    private String pin;
}
