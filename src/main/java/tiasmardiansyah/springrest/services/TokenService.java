package tiasmardiansyah.springrest.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {

        //this set the timer for expiration time
        Instant now = Instant.now();

        //this section will get the authority
        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(roles -> roles.startsWith("ROLE"))
                .collect(Collectors.joining(" "));

        String scope = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .filter(auths -> !auths.startsWith("ROLE"))
            .collect(Collectors.joining(" "));

        // this section is mostly the payload of jwt
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("Role", role)
                .build();

        // this will be the section about what the header is, like type of algorithm used
        // for encrypting the jwt
        JwsHeader jwsHeader = JwsHeader
                .with(MacAlgorithm.HS512)
                .build();

        var encoderParameters = JwtEncoderParameters.from(jwsHeader, claims);
        return this.encoder.encode(encoderParameters).getTokenValue();
    }
}
