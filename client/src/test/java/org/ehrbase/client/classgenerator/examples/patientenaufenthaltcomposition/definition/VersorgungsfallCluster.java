package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.901761600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class VersorgungsfallCluster implements LocatableEntity {
  /**
   * Patientenaufenthalt/context/Versorgungsfall/Zugehörige Versorgungsfall-Kennung
   */
  @Path("/items[at0001 and name/value='Zugehörige Versorgungsfall-Kennung']/value|value")
  private String zugehorigeVersorgungsfallKennungValue;

  /**
   * Patientenaufenthalt/context/Versorgungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehorigeVersorgungsfallKennungValue(
      String zugehorigeVersorgungsfallKennungValue) {
     this.zugehorigeVersorgungsfallKennungValue = zugehorigeVersorgungsfallKennungValue;
  }

  public String getZugehorigeVersorgungsfallKennungValue() {
     return this.zugehorigeVersorgungsfallKennungValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
