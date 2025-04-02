package DTO.Response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class TokenResponse {
    private String token;
    private String refreshToken;
}
