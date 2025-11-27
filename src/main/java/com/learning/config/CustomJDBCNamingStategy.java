package com.learning.config;

import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomJDBCNamingStategy implements NamingStrategy {

    @Override
    public String getKeyColumn(RelationalPersistentProperty property) {
        Assert.notNull(property, "Property must not be null");
        String var10000 = this.getReverseColumnName(property);
        return var10000 + "_id";

    }
}
