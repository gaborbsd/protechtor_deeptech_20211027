package com.javaoktato.blog.service;

import java.util.zip.CRC32;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    public String checksum(byte[] payload) {
        CRC32 crc = new CRC32();
        crc.update(payload);
        return Long.toString(crc.getValue());
    }
}
