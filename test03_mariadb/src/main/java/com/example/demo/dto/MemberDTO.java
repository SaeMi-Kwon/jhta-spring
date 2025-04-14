package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String pwd;

    @Pattern(regexp =".*@.*", message = "이메일은 형식이 아닙니다.")
    private String email;

    @Min(value = 0, message = "나이는 최소 0세 이상이어야 합니다.")
    @Max(value = 150, message = "나이는 최대 150세 이하여야 합니다.")
    @NotNull(message = "나이는 필수 입력 항목입니다.")
    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;
}
