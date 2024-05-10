package com.hasansahin.rediscachedemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private Long id;
    private String password;
}
