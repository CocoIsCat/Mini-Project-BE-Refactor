package com.example.finance7.cart.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CartRequestDTO {

    @NotBlank(message = "공백은 입력받을 수 없습니다.")
    @Pattern(regexp = "^[0-9]*$", message = "상품 ID는 숫자만 입력받을 수 있습니다.")
    private Long productId;
}
