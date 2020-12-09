package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.526762500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class SituationSection implements LocatableEntity {
  /**
   * open_eREACT-Care/Situation/Story/History
   */
  @Path("/items[openEHR-EHR-OBSERVATION.story.v1]")
  private StoryHistoryObservation storyHistory;

  /**
   * open_eREACT-Care/Situation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStoryHistory(StoryHistoryObservation storyHistory) {
     this.storyHistory = storyHistory;
  }

  public StoryHistoryObservation getStoryHistory() {
     return this.storyHistory ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
