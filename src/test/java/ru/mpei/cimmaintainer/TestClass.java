package ru.mpei.cimmaintainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nti.dtps.dto.scheme.RawSchemeDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper;

public class TestClass {

    private ObjectMapper mapper = jacksonObjectMapper();
    
    @Test
    public void test() throws IOException {

        var json = Files.readString(Paths.get("src/test/resources/lr1_scheme.json"));
        var scheme = mapper.readValue(json, RawSchemeDto.class);
        var cim = CimConverter.toCim(scheme);

        Assertions.assertNotNull(cim);
    }

}
