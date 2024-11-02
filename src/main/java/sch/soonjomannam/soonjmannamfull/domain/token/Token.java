package sch.soonjomannam.soonjmannamfull.domain.token;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "token", timeToLive = 12)
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class Token {

    @Id
    private String id;
    private String refreshToken;
    private String accessToken;
}
