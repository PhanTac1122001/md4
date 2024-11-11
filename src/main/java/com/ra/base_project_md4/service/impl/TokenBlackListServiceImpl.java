package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.entity.TokenBlackList;
import com.ra.base_project_md4.repository.TokenBlackListRepository;
import com.ra.base_project_md4.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {
    private final TokenBlackListRepository tokenBlackListRepository;
    @Override
    public void addTokenToBlackList(String token) {
        TokenBlackList tokenBlacklist = new TokenBlackList();
        tokenBlacklist.setToken(token);
        tokenBlackListRepository.save(tokenBlacklist);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return !tokenBlackListRepository.existsByToken(token);
    }
}
