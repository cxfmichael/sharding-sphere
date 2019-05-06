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

package org.apache.shardingsphere.dbtest.env.authority;

import com.google.common.base.Splitter;
import org.apache.shardingsphere.core.constant.DatabaseType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Authority SQL set xml entry.
 *
 * @author panjuan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class AuthoritySQLSet {
    
    @XmlAttribute(name = "db-types")
    private String databaseTypes = "H2,MySQL,Oracle,SQLServer,PostgreSQL";
    
    @XmlElementWrapper(name = "user-create")
    @XmlElement(name = "sql")
    private List<String> useCreateSQLs = new LinkedList<>();
    
    @XmlElementWrapper(name = "user-drop")
    @XmlElement(name = "sql")
    private List<String> useDropSQLs = new LinkedList<>();
    
    /**
     * Get all create user sqls.
     *
     * @param databaseType database type
     * @return create user sqls
     */
    public Collection<String> getCreateUserSQLs(final DatabaseType databaseType) {
        return getDatabaseTypes().contains(databaseType) ? useCreateSQLs : Collections.<String>emptyList();
    }
    
    /**
     * Get all drop user sqls.
     *
     * @param databaseType database type
     * @return create user sqls
     */
    public Collection<String> getDropUserSQLs(final DatabaseType databaseType) {
        return getDatabaseTypes().contains(databaseType) ? useDropSQLs : Collections.<String>emptyList();
    }
    
    private Collection<DatabaseType> getDatabaseTypes() {
        Collection<DatabaseType> result = new LinkedList<>();
        for (String each : Splitter.on(",").trimResults().splitToList(databaseTypes)) {
            result.add(DatabaseType.valueOf(each));
        }
        return result;
    }
}
