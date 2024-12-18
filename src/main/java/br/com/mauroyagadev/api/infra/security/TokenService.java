package br.com.mauroyagadev.api.infra.security;

import br.com.mauroyagadev.api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.tocken.secret}")
    private String secret;

        public String gerarToken(Usuario usuario) {
            //Visulizar
            //System.out.println(secret);
            try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer("api-gerenciamento-clinica")
                        .withSubject(usuario.getLogin())
                        .withClaim("id", usuario.getId())
                        .withExpiresAt(dataExpiracao())
                        .sign(algoritmo);
            } catch (JWTCreationException exception) {
                throw  new RuntimeException("Erro ao gerar token", exception);
            }

        }

    private Instant dataExpiracao() {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
