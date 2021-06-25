package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.TokenDao;
import com.heroku.spacey.dao.UserDao;
import com.heroku.spacey.entity.Token;
import com.heroku.spacey.entity.User;
import com.heroku.spacey.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private UserDao userDao;
    private final TokenDao tokenDao;

    public TokenServiceImpl(@Autowired UserDao userDao,
                            @Autowired TokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        Token verificationToken = new Token();
        verificationToken.setConfirmationToken(token);
        Long tokenId = tokenDao.insert(verificationToken);
        user.setTokenId(tokenId);
        userDao.updateUser(user);
    }

    @Override
    public Token getVerificationToken(String token) {
        return tokenDao.findByToken(token);
    }

    @Override
    public Token generateNewVerificationToken(User user, String existingVerificationToken) {
        Token token = tokenDao.findByToken(existingVerificationToken);
        if (token == null) {
            throw new NotFoundException("Token not found");
        }
        token.updateToken(UUID.randomUUID().toString());
        Long tokenId = tokenDao.insert(token);
        user.setTokenId(tokenId);
        userDao.updateUser(user);
        return tokenDao.findByTokenId(tokenId);
    }

    @Override
    public void checkTokenExpiration(Token token) throws TimeoutException {
        Calendar cal = Calendar.getInstance();
        if ((token.getDate().getTime() - cal.getTime().getTime()) > 86_400_000_000L) {
            throw new TimeoutException("Verification token is out of date");
        }
    }

    @Override
    public void deleteTokenById(Long id) {
        tokenDao.delete(id);
    }
}
