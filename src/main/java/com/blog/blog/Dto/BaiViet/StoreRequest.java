package com.blog.blog.Dto.BaiViet;

import com.blog.blog.model.ChuDe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    @NotNull(message = "Không được để trống")
    private int id;
    @NotBlank(message = "Không được để trống")
    private String tieuDe;
    @NotBlank(message = "Không được để trống")
    private String noiDung;
    @NotBlank(message = "Không được để trống")
    private String gioiThieu;

    @NotNull(message = "Không được để trống")
    private int idChuDe;

    private MultipartFile thumbnail;

    private int trangThai;
}
