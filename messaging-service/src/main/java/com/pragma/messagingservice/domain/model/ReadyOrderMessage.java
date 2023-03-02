package com.pragma.messagingservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadyOrderMessage {
    private Long idOrder;
    private String restaurant;
    private String phone;
    private String pin;
}
