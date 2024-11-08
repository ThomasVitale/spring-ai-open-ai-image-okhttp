package com.thomasvitale;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ImageController {

    private final ImageModel imageModel;

    ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/image")
    String image(String message) {
        var imageResponse = imageModel.call(new ImagePrompt(message, OpenAiImageOptions.builder()
                .withQuality("hd")
                .withHeight(1024)
                .withWidth(1024)
                .build()));
        return imageResponse.getResult().getOutput().getUrl();
    }

}
