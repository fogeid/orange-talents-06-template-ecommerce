package br.com.zupacademy.diego.ecommerce.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader {

    public Set<String> enviar(List<MultipartFile> images) {
        return images.stream().map(imagem -> "http://bucket.io/" +
                imagem.getOriginalFilename().toString()).collect(Collectors.toSet());
    }
}
