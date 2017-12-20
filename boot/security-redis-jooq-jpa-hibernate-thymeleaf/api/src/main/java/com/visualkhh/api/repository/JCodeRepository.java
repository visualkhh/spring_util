package com.visualkhh.api.repository;

import com.visualkhh.api.domain.Code;
import com.visualkhh.jooq.tables.JCode;
import com.visualkhh.jooq.tables.records.TCodeRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository @Slf4j
public class JCodeRepository {
    private final DSLContext dslContext;

    public JCodeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Code> codes(){


//        Table<?> prnt = JCode.CODE.as("PRNT");
//        Table<?> child = JCode.CODE.as("CHILD");
        Table<TCodeRecord> prnt = JCode.CODE.asTable("PRNT");
        Table<TCodeRecord> child = JCode.CODE.asTable("CHILD");

//        Field<?> prnt_cd = prnt.field("CD");
//        Field<?> child_prnt_cd = child.field("PRNT_CD");

        Field<String> prntCd = prnt.field(JCode.CODE.CD);
        Field<String> prntApiUseYn = prnt.field(JCode.CODE.API_USE_YN);
        Field<String> childPrntCd = child.field(JCode.CODE.PRNT_CD);
        Field<String> childApiUseYn = child.field(JCode.CODE.API_USE_YN);

        Map<Record, Result<Record>> result = this.dslContext.select().from(prnt)
            .join(child)
//            .on(JCode.CODE.CD.eq(JCode.CODE.PRNT_CD))
            .on(prntCd.eq(childPrntCd))
            .where(prntApiUseYn.eq("Y")).and(childApiUseYn.eq("Y"))
//            .on(prnt_cd.eq(child_prnt_cd))
            .fetch()
//                .getValues(JUser.USER.S)
            .intoGroups(prnt.fields());
//

        return result.entrySet().stream().map(it->{
            Code prntItem = it.getKey().into(Code.class);
            it.getValue().stream().forEach(sit->{
               prntItem.addCode(sit.into(Code.class));
            });
            return prntItem;
        }).collect(Collectors.toList());

//        return userResultMap
//                .values()
//                .stream()
//                .map(r -> {
//                    Record5<Integer, String, String, ZonedDateTime, ZonedDateTime> valuesRow = r.into(JUser.USER.USER_SEQ, JUser.USER.CPON_ID, JUser.USER.USE_YN, JUser.USER.LST_LGIN_DT, JUser.USER.REG_DT).get(0);
//                    List<UserDvc> userDvcs = r.sortAsc(JUser.USER.USER_SEQ).into(UserDvc.class).stream().filter( ud -> ud.getUserSeq() != null ).collect(Collectors.toList());
//                    return new User(valuesRow.value1(),valuesRow.value2(),valuesRow.value3(),valuesRow.value4(),valuesRow.value5(),userDvcs);
//                }).collect(Collectors.toList());
    }

	public Code findOne(String code) {
        Record result = this.dslContext.select().from(JCode.CODE)
                .where(JCode.CODE.CD.eq(code)).fetchOne();
        return result.into(Code.class);

    }
}
