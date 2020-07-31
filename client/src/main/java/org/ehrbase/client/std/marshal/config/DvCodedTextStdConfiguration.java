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

package org.ehrbase.client.std.marshal.config;

import com.nedap.archie.rm.datavalues.DvCodedText;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DvCodedTextStdConfiguration extends AbstractsStdConfig<DvCodedText> {
    @Override
    public Class<DvCodedText> getRMClass() {
        return DvCodedText.class;
    }

    @Override
    public Map<String, Object> buildChildValues(String termLoop, DvCodedText child) {

        Map<String, Object> result = new HashMap<>();
        addValue(result, termLoop, "code", child.getDefiningCode().getCodeString());
        addValue(result, termLoop, "terminology", child.getDefiningCode().getTerminologyId().getValue());
        addValue(result, termLoop, "value", child.getValue());

        return result;
    }

    @Override
    public List<Integer> valueCount(Class<DvCodedText> clazz) {
        return Collections.singletonList(3);
    }
}
