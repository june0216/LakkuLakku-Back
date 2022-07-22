package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.SettingsInfoDto;
import com.efub.lakkulakku.domain.user.dto.SettingsUpdateDto;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.efub.lakkulakku.domain.user.exception.PasswordNotMatchedException;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/

@Service
@RequiredArgsConstructor
@Transactional
public class SettingsServiceImpl implements SettingsService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;


    @Override
    public void updatePassword(String beforePassword, String afterPassword, String email) throws PasswordNotMatchedException {
        //User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        /*if(!user.matchPassword(passwordEncoder, beforePassword) ) {
            throw new PasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(passwordEncoder, afterPassword);*/
    }


}
