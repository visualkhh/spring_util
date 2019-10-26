package com.omnicns.medicine.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.base.AdmBase;
import com.omnicns.web.spring.security.GrantedObjAuthority;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_ADM")
@Slf4j
//@Subselect("SELECT A.*, B.CORP_GRP_NM FROM T_ADM A LEFT JOIN T_CORP_GRP B ON A.CORP_GRP_SEQ = B.CORP_GRP_SEQ")
@NamedEntityGraph(name = "UserDetails.auths", attributeNodes = @NamedAttributeNode("auths"))
//@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})
//@NamedNativeQueries({
//		@NamedNativeQuery(
//				name = "UserDetails.setLginFailCnt",
//				query = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE ADM_LGIN_ID = :admLginId",
//				resultSetMapping = "updateResult"
////				resultClass = UserDetails.class
//		)
//})
//@NamedQuery(name = "User.findByEmailAddress",
//		query = "select u from User u where u.emailAddress = ?1")
public class UserDetails extends AdmBase implements Serializable, org.springframework.security.core.userdetails.UserDetails {


    //	@OneToMany(fetch = FetchType.EAGER)
    @OneToMany
    @JoinColumn(name = "ADM_SEQ", referencedColumnName = "ADM_SEQ", insertable = false, updatable = false)
    @OrderBy(value = "menuLvl, menuOrd, prntUrlSeq asc")
    List<Auth> auths;


//	public Map<Auth,List<Auth>> menus(){
//		Map<Auth,List<Auth>> rtn = null;
//		if(null!=auths){
//			rtn = auths.stream().filter(it->null!=it.getMenuLvl()&&1==it.getMenuLvl().intValue())
//					.collect(LinkedHashMap::new,
//					(map, itemVar) -> map.put(itemVar, new ArrayList<>()), //Accumulator
//					(map, carryMap) -> map.putAll(carryMap)); //Combiner
//		}
//
//		rtn.entrySet().stream().forEach(it-> {
//			auths.stream().forEach(sit->{
//				if(sit.getUrlSeq().equals(sit.getPrntUrlSeq())) {
//					it.getValue().add(sit);
//				}
//			});
//		});
//
//		return rtn;
//	}


    @Override
    public Collection<? extends GrantedObjAuthority<List<Auth>>> getAuthorities() {

        Map<String, GrantedObjAuthority<List<Auth>>> contain = new HashMap<>();
        for (Auth auth : ListUtils.emptyIfNull(auths)) {
            GrantedObjAuthority<List<Auth>> selectedAuths = Optional.ofNullable(contain.get(auth.getAuthId())).orElseGet(() -> {
                GrantedObjAuthority<List<Auth>> newAuths = new GrantedObjAuthority<>(auth.getAuthId(), new ArrayList<>());
                contain.put(auth.getAuthId(), newAuths);
                return newAuths;
            });
            selectedAuths.getAuth().add(auth);
        }

        contain.put("ROLE_AUTH", new GrantedObjAuthority<List<Auth>>("ROLE_AUTH", null));
//		contain.put("ROLE_ANONYMOUS",   new GrantedObjAuthority<List<Auth>>("ROLE_ANONYMOUS",    null));

        return contain.values();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return getAdmLginPw();
    }

    @Override
    public String getUsername() {
        return getAdmNm();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UseCd.USE001.equals(getUseCd());
//		return getLginWaitDt().toEpochSecond() < ZonedDateTime.now().toEpochSecond();
//		getLginWaitDt().toEpochSecond(ZoneOffset.UTC);
//				LocalDateTime.ofInstant(offset).toEpochMillis()
//		return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UseCd.USE001.equals(getUseCd());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UseCd.USE001.equals(getUseCd());
//		return true;
    }

    @Override
    public boolean isEnabled() {
        return UseCd.USE001.equals(getUseCd());
    }
}
