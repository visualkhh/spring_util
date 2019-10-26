package com.omnicns.medicine.controller;

import com.omnicns.medicine.code.*;
import com.omnicns.medicine.domain.*;
import com.omnicns.medicine.domain.base.UserBase;
import com.omnicns.medicine.domain.convert.PrivateConvert;
import com.omnicns.medicine.exception.ErrorMsgException;
import com.omnicns.medicine.model.DataTablePageResponse;
import com.omnicns.medicine.model.error.Error;
import com.omnicns.medicine.repository.*;
import com.omnicns.web.spring.mail.Mailer;
import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(ClinicalController.URI_PREFIX)
public class ClinicalController {
    public static final String URI_PREFIX = "/clinical";
    @Autowired
    private CustomReloadableResourceBundleMessageSource customReloadableResourceBundleMessageSource;
    @Autowired
    PrivateConvert privateConvert;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private GameSetDayRstRepository gameSetDayRstRepository;
    @Autowired
    private GameSetWeekRstRepository gameSetWeekRstRepository;
    @Autowired
    private GameSetRstRepository gameSetRstRepository;
    @Autowired
    private GameRstRepository gameRstRepository;
    @Autowired
    private GiftRepository giftRepository;

    @GetMapping(value = "/participant")
    public DataTablePageResponse<User> participant(
            @PageableDefault(sort = {"regDt"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(value = "ptcpStDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime ptcpStDt,
            @RequestParam(value = "ptcpEndDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime ptcpEndDt,
            @RequestParam(value = "ptcpCd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) PtcpCd ptcpCd,
            @RequestParam(value = "ptcpGrpCd", required = false) PtcpGrpCd ptcpGrpCd,
            @RequestParam(value = "beforeWeekConformity", required = false) ConformityCd conformityCd,
            @RequestParam(value = "lstSerialNo", required = false, defaultValue = "") String lstSerialNo,
            @RequestParam(value = "cpon", required = false, defaultValue = "") String cpon,
            @RequestParam(value = "nm", required = false, defaultValue = "") String nm
    ) {

        ZonedDateTime zonedDateTime = ZonedDateTime.now().with(LocalTime.of(0, 0, 0, 0));
        // 1주일 뺀다  전주
        zonedDateTime = zonedDateTime.minusWeeks(1);
        Calendar cal1 = GregorianCalendar.from(zonedDateTime);
        Integer yearweek = null;
        if (null != conformityCd) {
            yearweek = Integer.parseInt(String.format("%d%d", cal1.getWeekYear(), cal1.get(Calendar.WEEK_OF_YEAR)));
        }
        log.debug("minusWeeks 1 week: " + yearweek);
//        //1월요일 ~ 7일요일  전주의 월요일을 구한다.
//        zonedDateTime = zonedDateTime.minusDays(zonedDateTime.getDayOfWeek().getValue() - 1);
//        ZonedDateTime currentWeekMonday = zonedDateTime.plusWeeks(1);

//        lstSerialNo = (null == lstSerialNo ? "" : lstSerialNo);
//        cpon = (StringUtils.isEmpty(cpon) ? null : cpon);
//        nm = (null == nm ? "" : nm);

        Page<User> participant = null;
        if (conformityCd.CFT001 == conformityCd) {  //순응
            participant = userRepository.findWeekCompletParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, yearweek, null, pageable);
//            List<GameSetDayRst> targetUsers = gameSetDayRstRepository.findSetCompletGroupByUserSeq(zonedDateTime, currentWeekMonday);
//            Set<Integer> targetUserSet  = targetUsers.stream().map(it -> it.getUserSeq()).collect(Collectors.toSet());
//            participant = userRepository.findCompletParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, zonedDateTime, currentWeekMonday, UseCd.USE001, pageable);
//            participant.forEach(it -> it.setConformityCd(conformityCd.CFT001));
        } else if (ConformityCd.CFT002 == conformityCd) { //미순응
            participant = userRepository.findWeekNotCompletParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, yearweek, null, pageable);
//            List<GameSetDayRst> targetUsers = gameSetDayRstRepository.findSetNotCompletGroupByUserSeq(zonedDateTime, currentWeekMonday);
//            Set<Integer> targetUserSet  = targetUsers.stream().map(it -> it.getUserSeq()).collect(Collectors.toSet());
//            participant = userRepository.findNotCompletParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, zonedDateTime, currentWeekMonday, UseCd.USE001, pageable);
//            participant.forEach(it -> it.setConformityCd(conformityCd.CFT002));
        } else { // 전체
            participant = userRepository.findWeekParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, null, pageable);
//            List<GameSetDayRst> completUsers = gameSetDayRstRepository.findSetCompletGroupByUserSeq(zonedDateTime, currentWeekMonday);
//            Set<Integer> completUserSet  = completUsers.stream().map(it -> it.getUserSeq()).collect(Collectors.toSet());
//            participant = userRepository.findParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, zonedDateTime, currentWeekMonday, UseCd.USE001, pageable);
//            participant.forEach(it -> it.setConformityCd(completUserSet.contains(it.getUserSeq()) ? conformityCd.CFT001 : conformityCd.CFT002));
        }
//        participant = userRepository.findCompletParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, zonedDateTime, currentWeekMonday, UseCd.USE001, pageable);

//        Page<User> participant = userRepository.findParticipant(
//                ptcpStDt, ptcpEndDt, ptcpCd, conformityCd, lstSerialNo, cpon, nm, ptcpGrpCd,
//                zonedDateTime, 2, UseCd.USE001, pageable);
//        Page<User> participant = userRepository.findParticipant(
//                ptcpStDt, ptcpEndDt, ptcpCd, conformityCd, lstSerialNo,
//                zonedDateTime, 2, UseCd.USE001, pageable);
//        return new DataTablePageResponse<User>(participant);
//        return new DataTablePageResponse(userRepository.findParticipant(UseCd.USE001, pageable));

        return new DataTablePageResponse(participant);
    }


    // 임상관리 / 참여자관리
    @GetMapping(value = "/participant/{userSeq}")
    public User get(@PathVariable(name = "userSeq") Integer userSeq) {
        return userRepository.findOne(userSeq);
    }

    // 임상관리 / 참여자관리 / 순응도
    @GetMapping(value = "/participant/{userSeq}/conformity")
    public DataTablePageResponse<GameSetWeekRst> getConformity(
            @PageableDefault(sort = {"yearweek"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @PathVariable(name = "userSeq") Integer userSeq,
            @RequestParam(value = "startDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDt,
            @RequestParam(value = "endDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDt
    ) {

        List<GameSetWeekRst> userData = null;
        Page<GameSetWeekRst> byBetweenWeek = null;
        if (null != startDt && null != endDt) {
            byBetweenWeek = gameSetWeekRstRepository.findByBetweenWeek(startDt, endDt, pageable);
            userData = gameSetWeekRstRepository.findByUserSeqAndStartDtGreaterThanEqualAndEndDtLessThanEqual(userSeq, startDt, endDt);
        } else {
            userData = gameSetWeekRstRepository.findByUserSeq(userSeq);
//            Optional<GameSetWeekRst> min = userData.stream().min(Comparator.comparing(GameSetWeekRst::getStartDt));
//            Optional<GameSetWeekRst> max = userData.stream().max(Comparator.comparing(GameSetWeekRst::getEndDt));
            Optional<GameSetWeekRst> min = userData.stream().min(Comparator.comparing(GameSetWeekRst::getYearweek));
            Optional<GameSetWeekRst> max = userData.stream().max(Comparator.comparing(GameSetWeekRst::getYearweek));
            if(max.isPresent() && min.isPresent())
            byBetweenWeek = gameSetWeekRstRepository.findByBetweenWeek(min.get().getStartDt(), max.get().getEndDt(), pageable);
        }

        List<GameSetWeekRst> finalUserData = userData;
        if(null!=byBetweenWeek) {
            byBetweenWeek.forEach(it -> {
                it.include(finalUserData.stream().filter(sit -> sit.getYearweek().equals(it.getYearweek())).findFirst().orElse(it));
            });
        } else {
            byBetweenWeek = new PageImpl<>(new ArrayList<>());
        }

       return new DataTablePageResponse<GameSetWeekRst>(byBetweenWeek);
    }



    @PostMapping(value = "/participant/{userSeq}/conformity/emails")
    public void sendEmail(@PathVariable(name = "userSeq") Integer userSeq, @RequestBody GameSetWeekRst gameSetWeekRst) throws Exception {
        User user = userRepository.findOne(userSeq);
        if(null == user.getGadEmail() || user.getGadEmail().length() <= 0) {
            throw new ErrorMsgException(MsgCode.E10002);
        }
        Mailer mailer = new Mailer();
        mailer.setFrom("fafa@fafa.com");
        mailer.setCharSet("UTF-8");
        mailer.setHost("localhost");
        mailer.setPort(25);
        mailer.setUsername("fafa");
        mailer.setPassword("");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        mailer.setJavaMailProperties(javaMailProperties);
        mailer.send(user.getGadEmail(), "hello 안녕하세요", "방갑습니다.");
    };


    // 임상관리 / 참여자관리 / 순응도 / 상세
    @GetMapping(value = "/participant/{userSeq}/conformity/details")
    public DataTablePageResponse<GameSetRst> getConformityDetails(
            @PageableDefault(sort = {"setRstSeq"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @PathVariable(name = "userSeq") Integer userSeq,
            @RequestParam(value = "startDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDt,
            @RequestParam(value = "endDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDt
    ) {
        Page<GameSetRst> history = gameSetRstRepository.findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(userSeq, startDt, endDt, pageable);
        return new DataTablePageResponse<GameSetRst>(history);
    }

    // 임상관리 / 참여자관리 / 집중도
    // new Sort(Sort.Direction.ASC, "cdOrd")
    @GetMapping(value = "/participant/{userSeq}/concentration")
    public List<GameSetRst> getConcentration(
            @SortDefault(value = "msmtEndDt", direction = Direction.ASC) Sort sort,
            @PathVariable(name = "userSeq") Integer userSeq,
            @RequestParam(value = "startDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDt,
            @RequestParam(value = "endDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDt
    ) {
        List<GameSetRst> history = null;
        if (null != startDt && null != endDt) {
            history = gameSetRstRepository.findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(userSeq, startDt, endDt, sort);
        } else {
            history = gameSetRstRepository.findByUserSeq(userSeq, sort);
        }
        return history;
    }
    // 임상관리 / 참여자관리 / 이력관리
    @GetMapping(value = "/participant/{userSeq}/history")
    public DataTablePageResponse<GameRst> getHistory(
            @PageableDefault(sort = {"rstSeq"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @PathVariable(name = "userSeq") Integer userSeq,
            @RequestParam(value = "startDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDt,
            @RequestParam(value = "endDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDt
    ) {
        Page<GameRst> history = null;
        if (null != startDt && null != endDt) {
            history = gameRstRepository.findByUserSeqAndMsmtStDtGreaterThanEqualAndMsmtEndDtLessThanEqual(userSeq, startDt, endDt, pageable);
        } else {
            history = gameRstRepository.findByUserSeq(userSeq, pageable);
        }
        return new DataTablePageResponse<GameRst>(history);
    }



    @PostMapping(value = "/participant")
    public void save(@RequestBody UserBase user) {
        user.setUseCd(UseCd.USE001);
        User dbUser = userRepository.findByCpon(user.getCpon());
        if(null == dbUser) {
            userRepository.save(user.map(User.class));
        } else {
            Error error = new Error(MsgCode.E10105, dbUser.getNm() + " " + customReloadableResourceBundleMessageSource.getMessage(MsgCode.E10105.value()));
            ErrorMsgException errorMsgException = new ErrorMsgException(error);
            throw errorMsgException;
        }
    }

    @PutMapping(value = "/participant/{userSeq}")
    public void update(@PathVariable(name = "userSeq") Integer userSeq, @RequestBody UserBase user) {
        user.setUserSeq(userSeq);
        User dbUser = userRepository.findByCpon(user.getCpon());
        if(null != dbUser && userSeq.equals(dbUser.getUserSeq())) {
            user.setUpdDt(ZonedDateTime.now());
            userRepository.save(user.map(User.class));
        } else if(null == dbUser){
            throw new ErrorMsgException(MsgCode.E10102);
        } else {
            Error error = new Error(MsgCode.E10105, dbUser.getNm() + " " + customReloadableResourceBundleMessageSource.getMessage(MsgCode.E10105.value()));
            ErrorMsgException errorMsgException = new ErrorMsgException(error);
            throw errorMsgException;
        }
    }

    @DeleteMapping(value = "/participant/{userSeq}")
    public void delete(@PathVariable(name = "userSeq") Integer userSeq) {
        User user = userRepository.findOne(userSeq);
        if (null != user && null != user.getUserSeq()) {
            user.setUseCd(UseCd.USE002);
            user.setUpdDt(ZonedDateTime.now());
            userRepository.save(user);
        }
    }



    // 임상관리 / 순응도관리 / 순응도
    @GetMapping(value = "/conformity/conforms")
    public List<User> getConforms(
            @PageableDefault(sort = {"regDt"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(value = "ptcpStDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime ptcpStDt,
            @RequestParam(value = "ptcpEndDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime ptcpEndDt,
            @RequestParam(value = "ptcpCd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) PtcpCd ptcpCd,
            @RequestParam(value = "ptcpGrpCd", required = false) PtcpGrpCd ptcpGrpCd,
            @RequestParam(value = "lstSerialNo", required = false, defaultValue = "") String lstSerialNo,
            @RequestParam(value = "cpon", required = false, defaultValue = "") String cpon,
            @RequestParam(value = "nm", required = false, defaultValue = "") String nm
    ) {
        Page<User> data = userRepository.findWeekParticipant(ptcpStDt, ptcpEndDt, ptcpCd, lstSerialNo, cpon, nm, ptcpGrpCd, UseCd.USE001, pageable);
//        Page<User> data = userRepository.findByGameSetDaySS(ptcpGrpCd, ptcpCd, lstSerialNo, cpon, nm, UseCd.USE001, pageable);
//        List<User> data = userRepository.findGameDayRsts();
        return data.getContent();
    }

    // 임상관리 / 순응도관리 / 기프티콘
    @GetMapping(value = "/conformity/gifts")
    public DataTablePageResponse<Gift> getGift(
            @PageableDefault(sort = {"reqDt"}, direction = Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(name = "nm", required = false, defaultValue = "") String nm,
            @RequestParam(name = "cpon", required = false, defaultValue = "") String cpon,
            @RequestParam(name = "comCd", required = false, defaultValue = "") ComCd comCd,
            @RequestParam(value = "startDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDt,
            @RequestParam(value = "endDt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDt
    ) {
        Page<Gift> data = giftRepository.findGift(startDt, endDt, nm, cpon, comCd, UseCd.USE001, pageable);
        return new DataTablePageResponse<Gift>(data);
    }
    // 임상관리 / 순응도관리 / 기프티콘 수
    @PutMapping(value = "/conformity/gifts/{giftSeq}")
    public void putGift(
            @PathVariable(name = "giftSeq") Integer giftSeq,
            @RequestBody Gift gift
    ) {
        Gift data = giftRepository.getOne(giftSeq);
        data.setComCd(gift.getComCd());
        data.setCont(gift.getCont());
        giftRepository.save(data);
    }



}
