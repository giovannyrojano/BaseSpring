package com.example.demo.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.security.auth.models.AuthenticationRequest;
import com.example.demo.security.auth.models.AuthenticationResponse;
import com.example.demo.security.config.JwtService;
import com.example.demo.security.config.token.Token;
import com.example.demo.security.config.token.TokenRepository;
import com.example.demo.security.config.token.TokenType;
import com.example.demo.users.models.User;
import com.example.demo.users.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
     private final UserRepository repository;
      private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
     private final JwtService jwtService;
    
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = repository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }


  
  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }


   private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }


}
