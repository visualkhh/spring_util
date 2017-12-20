package com.visualkhh.common.config.converter;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeConverter implements Converter<Timestamp, ZonedDateTime> {

    private static final long serialVersionUID = 1L;

    @Override
    public ZonedDateTime from(final Timestamp timestamp) {
        ZonedDateTime retVal = null;
        if(timestamp!=null){
            retVal = ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
        }
        return retVal;
    }

    @Override
    public Timestamp to(final ZonedDateTime zonedDateTime) {
        Timestamp retVal = null;
        if(zonedDateTime!=null){
            retVal = Timestamp.from(zonedDateTime.toInstant());
        }
        return retVal;
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<ZonedDateTime> toType() {
        return ZonedDateTime.class;
    }
}