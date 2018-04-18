package com.psk.bank.httpconverters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.psk.bank.controller.ImageConverterController.Base64Image;

public class Base64ImageHttpMessageConverter extends AbstractHttpMessageConverter<Base64Image> {

    public Base64ImageHttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.IMAGE_PNG);
        setSupportedMediaTypes(mediaTypes);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.equals(Base64Image.class);
    }

    @Override
    protected Base64Image readInternal(Class<? extends Base64Image> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void writeInternal(Base64Image t, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
       outputMessage.getBody().write(Base64.decodeBase64(t.getContent()));
       outputMessage.getBody().flush();
    }

}
