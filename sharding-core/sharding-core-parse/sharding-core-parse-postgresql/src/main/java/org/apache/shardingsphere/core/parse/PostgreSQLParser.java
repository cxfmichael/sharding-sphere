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

package org.apache.shardingsphere.core.parse;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.apache.shardingsphere.core.parse.antlr.autogen.PostgreSQLStatementParser;
import org.apache.shardingsphere.core.parse.antlr.parser.advanced.AdvancedErrorStrategy;
import org.apache.shardingsphere.core.parse.antlr.parser.advanced.AdvancedMatchHandler;
import org.apache.shardingsphere.core.parse.antlr.parser.advanced.AdvancedParserATNSimulator;
import org.apache.shardingsphere.core.parse.api.SQLParser;

/**
 * SQL parser for PostgreSQL.
 * 
 * @author duhongjun
 */
public final class PostgreSQLParser extends PostgreSQLStatementParser implements SQLParser {
    
    private final AdvancedMatchHandler advancedMatchHandler;
    
    public PostgreSQLParser(final TokenStream input) {
        super(input);
        _interp = new AdvancedParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache, IDENTIFIER_);
        _errHandler = new AdvancedErrorStrategy(IDENTIFIER_);
        advancedMatchHandler = new AdvancedMatchHandler(this, IDENTIFIER_);
    }
    
    @Override
    public Token match(final int tokenType) throws RecognitionException {
        if (Token.EOF == tokenType) {
            matchedEOF = true;
        }
        return advancedMatchHandler.getMatchedToken(tokenType);
    }
}
