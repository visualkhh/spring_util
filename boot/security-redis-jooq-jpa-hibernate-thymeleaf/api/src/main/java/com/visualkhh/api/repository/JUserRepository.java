package com.visualkhh.api.repository;

import com.visualkhh.api.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;

@Repository
public class JUserRepository {
//    private final DSLContext dslContext;
//
//    public UserRepository(DSLContext dslContext) {
//        this.dslContext = dslContext;
//    }

    public Collection<User> selectAllUsers(){
//        Map<Record, Result<Record>> userResultMap = this.dslContext.select().from(JUser.USER)
//            .leftJoin(JUserDvc.USER_DVC)
//            .on(JUser.USER.USER_SEQ.eq(JUserDvc.USER_DVC.USER_SEQ))
//            .fetch()
////                .getValues(JUser.USER.S)
//            .intoGroups(JUser.USER.fields());
//
//        return userResultMap
//                .values()
//                .stream()
//                .map(r -> {
//                    Record5<Integer, String, String, ZonedDateTime, ZonedDateTime> valuesRow = r.into(JUser.USER.USER_SEQ, JUser.USER.CPON_ID, JUser.USER.USE_YN, JUser.USER.LST_LGIN_DT, JUser.USER.REG_DT).get(0);
//                    List<UserDvc> userDvcs = r.sortAsc(JUser.USER.USER_SEQ).into(UserDvc.class).stream().filter( ud -> ud.getUserSeq() != null ).collect(Collectors.toList());
//                    return new User(valuesRow.value1(),valuesRow.value2(),valuesRow.value3(),valuesRow.value4(),valuesRow.value5(),userDvcs);
//                }).collect(Collectors.toList());
        return new Collection<User>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<User> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(User user) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends User> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }
}
