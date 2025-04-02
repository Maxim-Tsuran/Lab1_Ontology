package ru.mpei.cimmaintainer;

import ru.mpei.cimmaintainer.tocim.equipment.*;
import ru.mpei.cimmaintainer.tocim.line.Line;
import ru.mpei.cimmaintainer.tocim.rdf.RdfResource;
import ru.mpei.cimmaintainer.tocim.substation.Substation;
import ru.nti.dtps.dto.scheme.RawEquipmentNodeDto;
import ru.nti.dtps.dto.scheme.RawSchemeDto;
import ru.nti.dtps.proto.lib.equipment.EquipmentLibId;

import java.util.List;
import java.util.Map;

public class CimConverter {
    public static String toCim(RawSchemeDto scheme) {
        ConsoleInterceptor interceptor = new ConsoleInterceptor();
        interceptor.start();
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<rdf:RDF xmlns:cim=\"http://iec.ch/TC57/2016/CIM-schema-cim#\" " +
                "xmlns:cim302=\"http://iec.ch/TC57/2018/CIM-schema-cim#\" xmlns:dtps=\"" +
                "http://dtps.cloud/2023/schema-cim01#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">");
        try {
            // Основная логика конвертации
            List<RdfResource> resources = Substation.convert(scheme);
            for (RdfResource resource : resources) {
                System.out.println(resource);
            }

            List<RdfResource> lines = Line.convert(scheme);
            for (RdfResource line : lines) {
                System.out.println(line);
            }

            Map<String, RawEquipmentNodeDto> nodes = scheme.getNodes();
            for (Map.Entry<String, RawEquipmentNodeDto> entry : nodes.entrySet()) {
                String key = entry.getKey();
                RawEquipmentNodeDto value = entry.getValue();

                if (value.getLibEquipmentId() == EquipmentLibId.TRANSMISSION_LINE_SEGMENT) {
                    System.out.println(ACLineSegment.convert(key, value));
                }
                if (value.getLibEquipmentId() == EquipmentLibId.CIRCUIT_BREAKER) {
                    System.out.println(Breaker.convert(key, value));
                }
                if (value.getLibEquipmentId() == EquipmentLibId.POWER_SYSTEM_EQUIVALENT) {
                    System.out.println(EquivalentInjection.convert(key, value));
                }
                if (value.getLibEquipmentId() == EquipmentLibId.BUS) {
                    System.out.println(BusbarSection.convert(key, value));
                }
                if (value.getLibEquipmentId() == EquipmentLibId.TWO_WINDING_POWER_TRANSFORMER) {
                    System.out.println(PowerTransformer.convert(key, value));
                }
                if (value.getLibEquipmentId() == EquipmentLibId.LOAD) {
                    System.out.println(EnergyConsumer.convert(key, value));
                }
            }

            return resources.toString();

        } finally {
            // Гарантированно сохраняем и восстанавливаем потоки
            System.out.println("</rdf:RDF>");

            interceptor.restore();
            XmlGenerator.saveRawXml(interceptor.getOut(), "cim_output.xml");
        }
    }
}