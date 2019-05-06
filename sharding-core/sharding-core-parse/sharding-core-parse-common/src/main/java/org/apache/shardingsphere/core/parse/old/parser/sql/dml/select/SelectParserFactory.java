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

package org.apache.shardingsphere.core.parse.old.parser.sql.dml.select;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.core.constant.DatabaseType;
import org.apache.shardingsphere.core.metadata.table.ShardingTableMetaData;
import org.apache.shardingsphere.core.parse.old.lexer.LexerEngine;
import org.apache.shardingsphere.core.parse.old.parser.dialect.mysql.sql.MySQLSelectParser;
import org.apache.shardingsphere.core.parse.old.parser.dialect.oracle.sql.OracleSelectParser;
import org.apache.shardingsphere.core.parse.old.parser.dialect.postgresql.sql.PostgreSQLSelectParser;
import org.apache.shardingsphere.core.parse.old.parser.dialect.sqlserver.sql.SQLServerSelectParser;
import org.apache.shardingsphere.core.rule.ShardingRule;

/**
 * Select parser factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SelectParserFactory {
    
    /**
     * Create select parser instance.
     *
     * @param dbType database type
     * @param shardingRule databases and tables sharding rule
     * @param lexerEngine lexical analysis engine.
     * @param shardingTableMetaData sharding metadata.
     * @return select parser instance
     */
    public static AbstractSelectParser newInstance(final DatabaseType dbType, final ShardingRule shardingRule, final LexerEngine lexerEngine, final ShardingTableMetaData shardingTableMetaData) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new MySQLSelectParser(shardingRule, lexerEngine, shardingTableMetaData);
            case Oracle:
                return new OracleSelectParser(shardingRule, lexerEngine, shardingTableMetaData);
            case SQLServer:
                return new SQLServerSelectParser(shardingRule, lexerEngine, shardingTableMetaData);
            case PostgreSQL:
                return new PostgreSQLSelectParser(shardingRule, lexerEngine, shardingTableMetaData);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}
