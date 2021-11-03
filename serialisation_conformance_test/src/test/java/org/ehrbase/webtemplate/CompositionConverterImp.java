/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate;

import care.better.platform.web.template.converter.CompositionConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class CompositionConverterImp implements CompositionConverter {

  public static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  @Override
  public String convertRawToFlat(String template, String defaultLanguage, String rawComposition)
      throws Exception {
    Composition unmarshal =
        new CanonicalJson()
            .unmarshal(rawComposition.replace("\"@class\"", "\"_type\""), Composition.class);

    return getFlatJson(template, FlatFormat.SIM_SDT)
        .marshal(unmarshal)
        // Test expect without |id even if this is wrong
        .replace("_identifier:0|id", "_identifier:0")
        .replace("_identifier:1|id", "_identifier:1")
        .replace("originating_system_item_id:0|id", "originating_system_item_id:0")
        .replace("originating_system_item_id:1|id", "originating_system_item_id:1")
        .replace("feeder_system_item_id:0|id", "feeder_system_item_id:0")
        .replace("feeder_system_item_id:1|id", "feeder_system_item_id:1");
  }

  @Override
  public String convertRawToStructured(
      String template, String defaultLanguage, String rawComposition) throws Exception {

    Composition unmarshal =
        new CanonicalJson()
            .unmarshal(rawComposition.replace("\"@class\"", "\"_type\""), Composition.class);
    return getFlatJson(template, FlatFormat.STRUCTURED).marshal(unmarshal);
  }

  @Override
  public String convertFlatToRaw(
      String template,
      String defaultTemplateLanguage,
      String flatComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Map<String, Object> currentValues = new HashMap<>();
    for (Iterator<Map.Entry<String, JsonNode>> it =
            OBJECT_MAPPER.readTree(flatComposition).fields();
        it.hasNext(); ) {
      Map.Entry<String, JsonNode> e = it.next();
      currentValues.put(e.getKey(), e.getValue().asText());
    }

    compositionBuilderContext.forEach((k, v) -> currentValues.put(replace(k), v));
    replaceKey(
        currentValues,
        "ficha_individual_da_aten_cao_basica/resumo_do_atendimento/problemas/problem_diagnosis:0/related_item:0/item/value",
        "ficha_individual_da_aten_cao_basica/resumo_do_atendimento/problemas/problem_diagnosis:0/related_item:0/item/text_value");

    // topography is single  valued  in the template
    replaceKey(
        currentValues,
        "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography:78",
        "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography");
    replaceKey(
        currentValues,
        "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography:19",
        "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography");
    replaceKey(
        currentValues,
        "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography:137",
        "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography");

    currentValues.entrySet().stream()
        .filter(e -> "video/mp4".equals(e.getValue()))
        .forEach(e -> currentValues.replace(e.getKey(), "video/H263-2000"));

    // add missing terminology for some flat examples
    addTerminology(
        currentValues, "drug_related_problem_report/medication_error/medra_classification");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect:1/intervention_details:1/intervention_result");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect:1/intervention_details:2/intervention_result");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect:1/intervention_details/intervention_result");
    addTerminology(
        currentValues, "medication_error_report/medication_error/adverse_effect:1/reaction");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect/intervention_details:1/intervention_result");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect/intervention_details:2/intervention_result");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect/intervention_details:2/intervention_result");
    addTerminology(
        currentValues,
        "medication_error_report/medication_error/adverse_effect/intervention_details/intervention_result");
    addTerminology(
        currentValues, "medication_error_report/medication_error/adverse_effect/reaction");
    addTerminology(currentValues, "medication_error_report/medication_error/medra_classification");
    addTerminology(
        currentValues,
        "medication_event_case_summary/case_summary/summary_details/admission_diagnosis_classification");
    addTerminology(
        currentValues,
        "medication_event_case_summary/case_summary/summary_details/discharge_diagnosis_classification");

    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:1/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:1/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:2/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:2/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/reaction");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:1/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:1/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:2/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:2/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details/intervention");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details/intervention_result");
    addTerminology(
        currentValues,
        "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/reaction");
    addTerminology(
        currentValues, "adverse_drug_reaction_report/adverse_drug_reaction/medra_classification");

    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(currentValues));
    return new CanonicalJson().marshal(composition).replace("\"_type\"", "\"@class\"");
  }

  private void addTerminology(Map<String, Object> currentValues, String path) {
    if (currentValues.containsKey(path + "|code")) {
      currentValues.put(path + "|terminology", "terminology");
    }
  }

  private void replaceKey(Map<String, Object> currentValues, String key, String newKey) {
    if (currentValues.containsKey(key)) {
      Object o = currentValues.get(key);
      currentValues.remove(key);
      currentValues.put(newKey, o);
    }
  }

  private RMDataFormat getFlatJson(String template, FlatFormat flatFormat)
      throws XmlException, IOException {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

    RMDataFormat flatJson =
        new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
            .buildFlatJson(flatFormat, templateDocument.getTemplate().getTemplateId().getValue());
    return flatJson;
  }

  private String replace(String k) {
    if (k.equals("composerName")) {
      return "ctx/composer_name";
    } else if (k.equals("start_time")) {
      return "ctx/time";
    }
    return "ctx/" + k;
  }

  @Override
  public String convertFlatToStructured(
      String template,
      String defaultLanguage,
      String flatComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {

    Composition composition = getFlatJson(template, FlatFormat.SIM_SDT).unmarshal(flatComposition);
    return getFlatJson(template, FlatFormat.STRUCTURED).marshal(composition);
  }

  @Override
  public String convertStructuredToRaw(
      String template,
      String defaultLanguage,
      String structuredComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    Composition composition =
        getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredComposition);
    return new CanonicalJson().marshal(composition).replace("\"_type\"", "\"@class\"");
  }

  @Override
  public String convertStructuredToFlat(
      String template,
      String defaultLanguage,
      String structuredComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    Composition composition =
        getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredComposition);
    return getFlatJson(template, FlatFormat.SIM_SDT).marshal(composition);
  }

  @Override
  public String updateRawComposition(
      String template,
      String defaultLanguage,
      String rawComposition,
      Map<String, Object> compositionBuilderContext,
      Map<String, Object> deltaValues)
      throws Exception {
    throw new UnsupportedOperationException();
  }
}
