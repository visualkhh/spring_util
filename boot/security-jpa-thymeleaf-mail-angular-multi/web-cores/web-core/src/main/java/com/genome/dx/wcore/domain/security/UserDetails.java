package com.genome.dx.wcore.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.CrudTypeCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.base.AdmBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.wcore.model.security.AuthDepthDetail;
import com.omnicns.web.spring.security.GrantedObjAuthority;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_ADM")
@Slf4j
//@Subselect("SELECT A.*, B.CORP_GRP_NM FROM T_ADM A LEFT JOIN T_CORP_GRP B ON A.CORP_GRP_SEQ = B.CORP_GRP_SEQ")
@NamedEntityGraph(name = "UserDetails.authDetails", attributeNodes = @NamedAttributeNode("authDetails"))
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
    @JsonView({JsonViewFrontEnd.class})
    List<AuthDetail> authDetails;


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
    @JsonView({JsonViewFrontEnd.class})
    public List<AuthDepthDetail> authDupDepthDetails() {
        List<AuthDepthDetail> data = this.getAuthMergeUrlDedups(1l, null, null).stream().map(it -> it.map(AuthDepthDetail.class)).collect(Collectors.toList());
        data.forEach(this::authDupDepthDetail);
        return data;
    }

    public void authDupDepthDetail(AuthDepthDetail parent) {
        Long nextLevel = parent.getMenuLvl().longValue() + 1;
        parent.setChilds(this.getAuthMergeUrlDedups(nextLevel, parent.getCrudTypeCd(), parent.getUrlSeq()).stream().map(it -> it.map(AuthDepthDetail.class)).collect(Collectors.toList()));
        for (AuthDepthDetail child : parent.getChilds()) {
            this.authDupDepthDetail(child);
        }
    }

    public Collection<AuthDetail> getAuthMergeUrlDedups(Long level, CrudTypeCd crudTypeCd, Long parentUrl) {
        List<AuthDetail> auths = Optional.ofNullable(this.authDetails).orElse(Collections.EMPTY_LIST);
        auths = auths.stream().filter(it -> level.equals(it.menuLvl)
                && (null != parentUrl ? parentUrl.equals(it.getPrntUrlSeq()) : null == it.getPrntUrlSeq())
                && (null != crudTypeCd ? crudTypeCd.equals(it.getCrudTypeCd()) : true)
        ).map(it -> {
            return AuthDetail.builder()
                    .urlSeq(it.urlSeq)
                    .menuLvl(it.menuLvl)
                    .crudTypeCd(it.crudTypeCd)
                    .menuIcon(it.menuIcon)
                    .menuOrd(it.menuOrd)
                    .i18nCd(it.i18nCd)
                    .url(it.url)
                    .useCd(it.useCd)
                    .hddnCd(it.hddnCd)
                    .regexpCd(it.regexpCd)
                    .urlXpln(it.urlXpln)
                    .build();
        }).collect(Collectors.toList());

        Map<String, AuthDetail> authDetailMap = new HashMap<String, AuthDetail>();
        auths.stream().forEach(it -> {
                String key = it.urlSeq + "_" + it.crudTypeCd;
                AuthDetail before = authDetailMap.get(key);
                if (null == before) {
                    before = authDetailMap.put(key, it);
                } else {
                    if (UseCd.USE001 == it.useCd) {
                        before.useCd = it.useCd;
                    }
                    if (UseCd.USE001 == it.hddnCd) {
                        before.hddnCd = it.hddnCd;
                    }
                    if (UseCd.USE001 == it.regexpCd) {
                        before.regexpCd = it.regexpCd;
                    }
                }
           });


        Collection<AuthDetail> values = authDetailMap.values();
//        values.stream().sorted(Comparator.comparing(AuthDetail::getMenuOrd));
        values = values.stream()
//                .sorted((a, b) -> a.menuOrd.longValue() > b.menuOrd.longValue() ? 0 : 1)
                .sorted(Comparator.comparing(a -> a.menuOrd))
                .sorted(Comparator.comparing(a -> a.crudTypeCd.name())).collect(Collectors.toList());
//        values.forEach(it -> {
//            it.authId = "ROLE";
//            it.authNm = "ROLE";
//        });
        return values;
    };

    @Override
    public Collection<? extends GrantedObjAuthority<List<AuthDetail>>> getAuthorities() {
        Map<String, GrantedObjAuthority<List<AuthDetail>>> contain = new HashMap<>();
        for (AuthDetail auth : ListUtils.emptyIfNull(authDetails)) {
            GrantedObjAuthority<List<AuthDetail>> selectedAuths = Optional.ofNullable(contain.get(auth.getAuthId())).orElseGet(() -> {
                GrantedObjAuthority<List<AuthDetail>> newAuths = new com.genome.dx.wcore.model.security.GrantedObjAuthority<>(auth.getAuthId(), new ArrayList<>());
                contain.put(auth.getAuthId(), newAuths);
                return newAuths;
            });
            selectedAuths.getAuth().add(auth);
        }
        contain.put("ROLE_AUTH", new com.genome.dx.wcore.model.security.GrantedObjAuthority<List<AuthDetail>>("ROLE_AUTH", null));
//		contain.put("ROLE_ANONYMOUS",   new GrantedObjAuthority<List<Auth>>("ROLE_ANONYMOUS",    null));
        return contain.values();
    }

    public List<AuthDetail> getUseAuthorities(String url, List<CrudTypeCd> crudTypeCds) {

        return this.authDetails.stream()
                .filter(it -> UseCd.USE001 == it.useCd && crudTypeCds.contains(it.crudTypeCd))
                .filter(it -> url.equals(it.url) || (UseCd.USE001 == it.regexpCd && url.matches(it.url)))
                .collect(Collectors.toList());
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
