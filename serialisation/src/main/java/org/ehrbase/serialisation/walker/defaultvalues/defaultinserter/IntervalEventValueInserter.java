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

package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.stream.Stream;

public class IntervalEventValueInserter extends AbstractValueInserter<IntervalEvent> {
  @Override
  public void insert(IntervalEvent rmObject, DefaultValues defaultValues) {

    if (isEmpty(rmObject.getMathFunction())
        ) {
     rmObject.setMathFunction(MathFunction.ACTUAL.toCodedText());
    }


  }

  @Override
  public Class<IntervalEvent> getAssociatedClass() {
    return IntervalEvent.class;
  }
}
