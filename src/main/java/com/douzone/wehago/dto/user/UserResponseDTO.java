package com.douzone.wehago.dto.user;

import com.douzone.wehago.jwt.TokenDTO;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDTO {

    private TokenDTO tokenDTO;
    private UserDTO userDTO;

}
