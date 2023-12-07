package com.tranv.fx22252.test;

import com.tranv.fx22252.service.TextFileService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TextFileServiceTest {

    @Test
    public void readFileTxt() {
        List<List<String>> data= TextFileService.readFileTxt("store/customer.txt");

        assertNotNull(data);
    }
}