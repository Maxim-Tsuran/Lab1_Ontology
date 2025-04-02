package ru.mpei.cimmaintainer.tocim.equipment;

import ru.mpei.cimmaintainer.tocim.rdf.CIMClasses;
import ru.mpei.cimmaintainer.tocim.rdf.RDFResourcesBuilder;
import ru.mpei.cimmaintainer.tocim.rdf.RDFResourcesId;
import ru.mpei.cimmaintainer.tocim.rdf.RdfResource;
import ru.nti.dtps.dto.scheme.RawEquipmentNodeDto;
import ru.nti.dtps.proto.lib.field.FieldLibId;

import java.util.List;

public class Breaker {
    public static RdfResource convert(String id, RawEquipmentNodeDto valueSet) {

        List<RawEquipmentNodeDto.PortDto> ports = valueSet.getPorts();

        for (int i = 0; i < ports.size(); i++) {
            System.out.println(Terminal.convert(id, i + 1, valueSet));
        }

        return new RDFResourcesBuilder(new RDFResourcesId(id), CIMClasses.breaker)
                .addDataProperty(CIMClasses.identifiedObject.mRID, id)
                .addDataProperty(CIMClasses.identifiedObject.name, valueSet.getName())
                .addDataProperty(CIMClasses.aswitch.open, String.valueOf(valueSet.getLocked()))
                .addDataProperty(CIMClasses.aswitch.ratedCurrent, valueSet.getFields().get(FieldLibId.RATED_CURRENT))
                .addObjectProperty(CIMClasses.conductingEquipment.BaseVoltage, new RDFResourcesId("#e6240428-fe41-4cb9-80d2-52bd2bbe2c03"))
                .addObjectProperty(CIMClasses.equipment.EquipmentContainer, new RDFResourcesId("7e3860fe-15e8-4d3a-bdeb-01321c768bf7"))
                .builder();
    }
}
