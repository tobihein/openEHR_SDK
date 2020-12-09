package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.recommendation.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.678762200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class RecommendationEvaluation implements EntryEntity {
  /**
   * open_eREACT-Care/Response/Recommendation/Recommendation
   */
  @Path("/data[at0001]/items[at0002]")
  private List<RecommendationRecommendationElement> recommendation;

  /**
   * open_eREACT-Care/Response/Recommendation/Extension
   */
  @Path("/protocol[at0004]/items[at0005]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Response/Recommendation/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Response/Recommendation/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Response/Recommendation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRecommendation(List<RecommendationRecommendationElement> recommendation) {
     this.recommendation = recommendation;
  }

  public List<RecommendationRecommendationElement> getRecommendation() {
     return this.recommendation ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
