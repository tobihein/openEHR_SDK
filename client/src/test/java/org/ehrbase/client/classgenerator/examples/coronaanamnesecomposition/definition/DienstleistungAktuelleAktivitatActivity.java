package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.643763200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class DienstleistungAktuelleAktivitatActivity implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Name der Dienstleistung
   */
  @Path("/description[at0009]/items[at0121]/value|value")
  private String nameDerDienstleistungValue;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Grund für die Anforderung
   */
  @Path("/description[at0009]/items[at0062]")
  private List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Komplexe Zeitplanung
   */
  @Path("/description[at0009]/items[at0151]")
  private List<Cluster> komplexeZeitplanung;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Spezifische Details
   */
  @Path("/description[at0009]/items[at0132]")
  private List<Cluster> spezifischeDetails;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Unterstützende Informationen
   */
  @Path("/description[at0009]/items[at0149]")
  private List<Cluster> unterstutzendeInformationen;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Anforderungen an Patienten
   */
  @Path("/description[at0009]/items[at0116]")
  private List<Cluster> anforderungenAnPatienten;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/timing
   */
  @Path("/timing")
  private DvParsable timing;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerDienstleistungValue(String nameDerDienstleistungValue) {
     this.nameDerDienstleistungValue = nameDerDienstleistungValue;
  }

  public String getNameDerDienstleistungValue() {
     return this.nameDerDienstleistungValue ;
  }

  public void setGrundFurDieAnforderung(
      List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung) {
     this.grundFurDieAnforderung = grundFurDieAnforderung;
  }

  public List<DienstleistungGrundFurDieAnforderungElement> getGrundFurDieAnforderung() {
     return this.grundFurDieAnforderung ;
  }

  public void setKomplexeZeitplanung(List<Cluster> komplexeZeitplanung) {
     this.komplexeZeitplanung = komplexeZeitplanung;
  }

  public List<Cluster> getKomplexeZeitplanung() {
     return this.komplexeZeitplanung ;
  }

  public void setSpezifischeDetails(List<Cluster> spezifischeDetails) {
     this.spezifischeDetails = spezifischeDetails;
  }

  public List<Cluster> getSpezifischeDetails() {
     return this.spezifischeDetails ;
  }

  public void setUnterstutzendeInformationen(List<Cluster> unterstutzendeInformationen) {
     this.unterstutzendeInformationen = unterstutzendeInformationen;
  }

  public List<Cluster> getUnterstutzendeInformationen() {
     return this.unterstutzendeInformationen ;
  }

  public void setAnforderungenAnPatienten(List<Cluster> anforderungenAnPatienten) {
     this.anforderungenAnPatienten = anforderungenAnPatienten;
  }

  public List<Cluster> getAnforderungenAnPatienten() {
     return this.anforderungenAnPatienten ;
  }

  public void setTiming(DvParsable timing) {
     this.timing = timing;
  }

  public DvParsable getTiming() {
     return this.timing ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
