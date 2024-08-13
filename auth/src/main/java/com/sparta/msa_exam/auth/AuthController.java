package com.sparta.msa_exam.auth;

import com.sparta.msa_exam.auth.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestParam(value = "user_id") String userID){
        System.out.println("로그인 컨트롤러");
        String token = authService.signIn(userID);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        System.out.println("회원가입 컨트롤러");
//        User user = authService.signUp(signUpDto);
//        return ResponseEntity.ok(user);
        return ResponseEntity.ok("");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;
    }

}
