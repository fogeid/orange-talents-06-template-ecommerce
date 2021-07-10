package br.com.zupacademy.diego.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemFormDTO {
    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemFormDTO(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImages() {
        return imagens;
    }
}
