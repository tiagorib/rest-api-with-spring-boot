package br.com.tiago.restapiwithspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageClass {

    private Long id;
    private String content;

}
