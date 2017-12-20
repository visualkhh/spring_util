package com.visualkhh.common.config.db;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;
import java.util.Locale;

public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl implements PhysicalNamingStrategy, Serializable {

    private String tablePrefix = "T_";

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalSchemaName(name, context);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String nameStr = null;
        if(!name.getText().startsWith(this.tablePrefix)){
            nameStr = this.tablePrefix+addUnderscores(name.getText());
        }else{
            nameStr = addUnderscores(name.getText());
        }
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(nameStr,name.isQuoted());
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                addUnderscores(name.getText()),
                name.isQuoted()
        );
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                addUnderscores(name.getText()),
                name.isQuoted()
        );
    }



    protected static String addUnderscores(String name) {
        final StringBuilder buf = new StringBuilder( name.replace('.', '_') );
        for (int i=1; i<buf.length()-1; i++) {
            if (
                    Character.isLowerCase( buf.charAt(i-1) ) &&
                            Character.isUpperCase( buf.charAt(i) ) &&
                            Character.isLowerCase( buf.charAt(i+1) )
                    ) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toUpperCase(Locale.ROOT);
    }
}
