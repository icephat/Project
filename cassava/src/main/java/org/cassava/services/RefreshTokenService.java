package org.cassava.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


import org.cassava.model.Refreshtoken;
import org.cassava.model.User;
import org.cassava.repository.RefreshTokenRepository;
import org.cassava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {

	@Value("${jwt.token.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private UserService userService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public Optional<Refreshtoken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public Refreshtoken createRefreshToken(User user) {
		 
		Optional<Refreshtoken> opt = refreshTokenRepository.findByUser(user);
		if (opt.isPresent()) {
			refreshTokenRepository.delete(opt.get());
		}
		Refreshtoken refreshToken = new Refreshtoken();
		refreshToken.setUser(user);
		Instant instant = Instant.now().plusMillis(refreshTokenDurationMs);

		refreshToken.setExpireDate(Timestamp.from(instant));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public Refreshtoken verifyExpiration(Refreshtoken token) {
		if (token.getExpireDate().toInstant().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + "Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(int userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}

}