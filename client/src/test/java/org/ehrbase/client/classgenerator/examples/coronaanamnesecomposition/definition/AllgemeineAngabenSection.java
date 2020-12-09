package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.618762300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class AllgemeineAngabenSection implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']")
  private AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']")
  private ChronischeErkrankungenObservation chronischeErkrankungen;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diagnose
   */
  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening
   */
  @Path("/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
  private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf
   */
  @Path("/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']")
  private GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation
   */
  @Path("/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos
   */
  @Path("/items[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus
   */
  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']")
  private ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung
   */
  @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  /**
   * Bericht/Allgemeine Angaben/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAndereAktuelleErkrankungen(
      AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen) {
     this.andereAktuelleErkrankungen = andereAktuelleErkrankungen;
  }

  public AndereAktuelleErkrankungenObservation getAndereAktuelleErkrankungen() {
     return this.andereAktuelleErkrankungen ;
  }

  public void setChronischeErkrankungen(ChronischeErkrankungenObservation chronischeErkrankungen) {
     this.chronischeErkrankungen = chronischeErkrankungen;
  }

  public ChronischeErkrankungenObservation getChronischeErkrankungen() {
     return this.chronischeErkrankungen ;
  }

  public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
     this.problemDiagnose = problemDiagnose;
  }

  public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
     return this.problemDiagnose ;
  }

  public void setFragebogenZumMedikationsScreening(
      FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening) {
     this.fragebogenZumMedikationsScreening = fragebogenZumMedikationsScreening;
  }

  public FragebogenZumMedikationsScreeningObservation getFragebogenZumMedikationsScreening() {
     return this.fragebogenZumMedikationsScreening ;
  }

  public void setGesundheitsbezogenerBeruf(
      GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf) {
     this.gesundheitsbezogenerBeruf = gesundheitsbezogenerBeruf;
  }

  public GesundheitsbezogenerBerufEvaluation getGesundheitsbezogenerBeruf() {
     return this.gesundheitsbezogenerBeruf ;
  }

  public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public List<WohnsituationEvaluation> getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setBewertungDesGesundheitsrisikos(
      List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
     this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
  }

  public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
     return this.bewertungDesGesundheitsrisikos ;
  }

  public void setProblemDiganoseCoronovirus(
      ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus) {
     this.problemDiganoseCoronovirus = problemDiganoseCoronovirus;
  }

  public ProblemDiganoseCoronovirusEvaluation getProblemDiganoseCoronovirus() {
     return this.problemDiganoseCoronovirus ;
  }

  public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
     this.dienstleistung = dienstleistung;
  }

  public List<DienstleistungInstruction> getDienstleistung() {
     return this.dienstleistung ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
