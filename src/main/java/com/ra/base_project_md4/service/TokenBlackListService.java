package com.ra.base_project_md4.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public interface TokenBlackListService {

    void addTokenToBlackList(String token);

    boolean isTokenBlacklisted(String token);

    String getTokenFromRequest(HttpServletRequest request);
}
