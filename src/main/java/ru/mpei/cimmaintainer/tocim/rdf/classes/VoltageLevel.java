package ru.mpei.cimmaintainer.tocim.rdf.classes;

import ru.mpei.cimmaintainer.tocim.rdf.RDFResourcesProperty;
import ru.mpei.cimmaintainer.tocim.rdf.RDFResourcesType;
import ru.mpei.cimmaintainer.tocim.rdf.XmlNameSpace;

public class VoltageLevel extends RDFResourcesType {
    public VoltageLevel(XmlNameSpace nameSpace, String nameType) {
        super(nameSpace, nameType);
    }
    public RDFResourcesProperty BaseVoltage = new RDFResourcesProperty(this, "BaseVoltage");
    public RDFResourcesProperty Substation = new RDFResourcesProperty(this, "Substation");
}
