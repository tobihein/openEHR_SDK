/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.flatencoding.std.umarshal.StdToCompositionWalker;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static org.ehrbase.serialisation.flatencoding.std.umarshal.StdToCompositionWalker.findRMUnmarshaller;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class DvOrderedPostprocessor extends AbstractUnmarshalPostprocessor<DvOrdered> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      DvOrdered rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    handleNormalRange(
        values, consumedPaths, context, term + "/_normal_range", rmObject::setNormalRange);

    FlatHelper.extractMultiValuedFullPath(term, "_other_reference_ranges", values)
        .forEach(
            (k, v) -> {
              ReferenceRange referenceRange = new ReferenceRange();
              rmObject.addOtherReferenceRange(referenceRange);

              Map<FlatPathDto, String> meaningValues =
                  FlatHelper.filter(values, term + "/_other_reference_ranges:" + k + "/meaning");

              if (!meaningValues.isEmpty()) {
                final DvText meaning;
                if (meaningValues.size() > 1) {
                  meaning = new DvCodedText();
                } else {
                  meaning = new DvText();
                }
                referenceRange.setMeaning(meaning);
                ((RMUnmarshaller<DvText>)
                        StdToCompositionWalker.findRMUnmarshaller(meaning.getClass()))
                    .handle(
                        term + "/_other_reference_ranges:" + k + "/meaning",
                        meaning,
                        meaningValues,
                        context,
                        consumedPaths);
              }

              handleNormalRange(
                  v,
                  consumedPaths,
                  context,
                  term + "/_other_reference_ranges:" + k,
                  referenceRange::setRange);
            });
  }

  private void handleNormalRange(
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      String term,
      Consumer<DvInterval> rangeConsumer) {

    Map<FlatPathDto, String> rangeValues = FlatHelper.filter(values, term);
    if (!rangeValues.isEmpty()) {
      DvInterval range = new DvInterval();
      rangeConsumer.accept(range);

      handleBorder(values, consumedPaths, context, "upper", range::setUpper, term);
      handleBorder(values, consumedPaths, context, "lower", range::setLower, term);
    }
  }

  private void handleBorder(
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      String borderName,
      Consumer<DvOrdered> borderConsumer,
      String term) {

    Map<FlatPathDto, String> borderValues =
        FlatHelper.filter(values, term + PATH_DIVIDER + borderName);
    if (!borderValues.isEmpty()) {

      DvOrdered lower =
          WebTemplateSkeletonBuilder.build(context.getNodeDeque().peek(), false, DvOrdered.class);
      borderConsumer.accept(lower);
      RMUnmarshaller rmUnmarshaller = findRMUnmarshaller(lower.getClass());
      rmUnmarshaller.handle(
          term + PATH_DIVIDER + borderName, lower, borderValues, context, consumedPaths);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvOrdered> getAssociatedClass() {
    return DvOrdered.class;
  }
}
