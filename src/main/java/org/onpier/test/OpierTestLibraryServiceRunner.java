package org.onpier.test;


import org.onpier.test.service.UserService;
import org.onpier.test.util.CsvDataConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OpierTestLibraryServiceRunner implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(OpierTestLibraryServiceRunner.class , args);
    }

    @Autowired
    private CsvDataConvertor csvDataConvertor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       csvDataConvertor.insertData();
    }
}