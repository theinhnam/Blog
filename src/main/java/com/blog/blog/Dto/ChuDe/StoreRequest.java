package com.blog.blog.Dto.ChuDe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    @NotNull(message = "Không được để trống")
    private int id;

    @NotBlank(message = "Không được để trống")
    private String ten;

    @NotBlank(message = "Không được để trống")
    private String moTa;

    @NotNull(message = "Không được để trống")
    private int trangThai;
}
