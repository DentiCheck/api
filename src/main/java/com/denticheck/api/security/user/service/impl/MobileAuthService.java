package com.denticheck.api.security.user.service.impl;

import com.denticheck.api.common.util.JWTUtil;
import com.denticheck.api.domain.user.entity.SocialProviderType;
import com.denticheck.api.domain.user.entity.UserEntity;
import com.denticheck.api.domain.user.entity.UserRoleType;
import com.denticheck.api.domain.user.entity.UserStatusType;
import com.denticheck.api.domain.user.service.impl.UserServiceImpl;
import com.denticheck.api.security.jwt.dto.JWTResponseDTO;
import com.denticheck.api.security.jwt.service.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MobileAuthService {

    private final MobileIdTokenVerifierService googleVerifier;
    private final UserServiceImpl userServiceImpl;   // 너 프로젝트 유저 생성/조회 로직
    private final JwtServiceImpl jwtServiceImpl;     // refresh 저장/삭제
    private final JWTUtil jwtUtil;           // access/refresh 생성

    @Transactional
    public JWTResponseDTO googleLogin(String idToken) {

        Jwt jwt = googleVerifier.verify(idToken);

        // Google 표준 claim
        String username = SocialProviderType.GOOGLE.name() + "_" + jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String nickname = jwt.getClaimAsString("name");

        // TODO: 모바일 인증 수정 예정
        if (!userServiceImpl.existsUser(username)) {
            // 없으면 회원가입
            userServiceImpl.mobileCreateUser(UserEntity.builder()
                    .username(username)
                    .email(email)
                    .nickname(nickname)
                    .socialProviderType(SocialProviderType.GOOGLE)
                    .roleType(UserRoleType.USER)
                    .userStatusType(UserStatusType.ACTIVE)
                    .build()
            );
        }
        UserEntity user = userServiceImpl.findUser(username)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다: " + username));;


        String role = "ROLE_" + user.getRoleType().name();


        String accessToken = jwtUtil.createAccessJWT(user.getUsername(), role);
        String refreshToken = jwtUtil.createRefreshJWT(user.getUsername(), role);

        jwtServiceImpl.addRefresh(user.getUsername(), refreshToken);

        return new JWTResponseDTO(accessToken, refreshToken);
    }
}
