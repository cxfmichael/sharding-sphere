/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.parse.old.parser.clause;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.core.parse.old.lexer.LexerEngine;
import org.apache.shardingsphere.core.parse.old.lexer.token.DefaultKeyword;
import org.apache.shardingsphere.core.parse.old.lexer.token.Keyword;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Select rest clause parser.
 *
 * @author zhangliang
 */
@RequiredArgsConstructor
public abstract class SelectRestClauseParser implements SQLClauseParser {
    
    private final LexerEngine lexerEngine;
    
    /**
     * Parse select rest.
     */
    public final void parse() {
        Collection<Keyword> unsupportedRestKeywords = new LinkedList<>();
        unsupportedRestKeywords.addAll(Arrays.asList(DefaultKeyword.UNION, DefaultKeyword.INTERSECT, DefaultKeyword.EXCEPT, DefaultKeyword.MINUS));
        unsupportedRestKeywords.addAll(Arrays.asList(getUnsupportedKeywordsRest()));
        lexerEngine.unsupportedIfEqual(unsupportedRestKeywords.toArray(new Keyword[unsupportedRestKeywords.size()]));
    }
    
    protected abstract Keyword[] getUnsupportedKeywordsRest();
}
