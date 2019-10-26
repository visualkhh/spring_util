package com.omnicns.medicine.controller.api;

import com.omnicns.medicine.code.GameSetCd;
import com.omnicns.medicine.code.OsType;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.GameRst;
import com.omnicns.medicine.domain.GameSet;
import com.omnicns.medicine.domain.GameSetRst;
import com.omnicns.medicine.model.MedicineHeader;
import com.omnicns.medicine.model.api.GameResult;
import com.omnicns.medicine.model.api.SetGameSetResult;
import com.omnicns.medicine.repository.GameRstRepository;
import com.omnicns.medicine.repository.GameSetRepository;
import com.omnicns.medicine.repository.GameSetRstRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiController.URI_PREFIX + GameController.URI_PREFIX)
@Api(tags = "Game")
public class GameController {
    public static final String URI_PREFIX = "/games";
    @Autowired
    private GameSetRepository gameSetRepository;
    @Autowired
    private GameSetRstRepository gameSetRstRepository;
    @Autowired
    private GameRstRepository gameRstRepository;


    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    @ApiOperation(value = "셋트 조회")
    @GetMapping(value = "/sets/infos")
    public List<GameSet> setInfos(
            @ApiParam(required = false, name = "setCd", value = "게임 셋트 코드")
            @RequestParam(value = "setCd", required = false) GameSetCd setCd
    ) {
        if (null!=setCd){
            return gameSetRepository.findBySetCdAndUseCd(setCd, UseCd.USE001);
        } else {
            return gameSetRepository.findByUseCd(UseCd.USE001);
        }
    }

    @ApiOperation(value = "셋트 저장")
    @PostMapping(value = "/{setMsmtNo}")
    public GameSetRst saveSet(
            @ApiParam(required = true, name = "setMsmtNo", value = "게임 셋트 측정번호", defaultValue = "test-setMsmtNo")
            @PathVariable("setMsmtNo") String setMsmtNo,
            @RequestHeader(name = MedicineHeader.HEADER_cponId) String cponId,
            @RequestHeader(name = MedicineHeader.HEADER_serialNo) String serialNo,
            @RequestHeader(name = MedicineHeader.HEADER_cponModel) String cponModel,
            @RequestHeader(name = MedicineHeader.HEADER_osTypeCd) OsType osType,
            @RequestHeader(name = MedicineHeader.HEADER_osVer) String osVer,
            @RequestHeader(name = MedicineHeader.HEADER_pkgNm) String pkgNm,
            @RequestHeader(name = MedicineHeader.HEADER_pkgVer) String pkgVer,
            @RequestHeader(name = MedicineHeader.HEADER_userSeq) int userSeq,
            @Valid @RequestBody SetGameSetResult gameSetResult
    ) {
        GameSetRst gameSetRst = gameSetResult.map(GameSetRst.class);
        gameSetRst.setSetMsmtNo(setMsmtNo);
        gameSetRst.setCponId(cponId);
        gameSetRst.setSerialNo(serialNo);
        gameSetRst.setCponModel(cponModel);
        gameSetRst.setOsTypeCd(osType);
        gameSetRst.setOsVer(osVer);
        gameSetRst.setPkgNm(pkgNm);
        gameSetRst.setPkgVer(pkgVer);
        gameSetRst.setUserSeq(userSeq);
        GameSetRst set = gameSetRstRepository.save(gameSetRst);

        gameSetResult.getResults().forEach(it -> {
            GameRst gameRst = it.map(GameRst.class);
            gameRst.setSetMsmtNo(setMsmtNo);
            gameRst.setSetCd(gameSetRst.getSetCd());
            gameRst.setCponId(cponId);
            gameRst.setSerialNo(serialNo);
            gameRst.setCponModel(cponModel);
            gameRst.setOsTypeCd(osType);
            gameRst.setOsVer(osVer);
            gameRst.setPkgNm(pkgNm);
            gameRst.setPkgVer(pkgVer);
            gameRst.setUserSeq(userSeq);
            gameRstRepository.save(gameRst);
        });
        return set;
    }

    @ApiOperation(value = "게임 저장")
    @PostMapping(value = "/{setMsmtNo}/{gameMsmtNo}")
    public GameRst saveGame(
            @ApiParam(required = true, name = "setMsmtNo", value = "게임 셋트 측정번호", defaultValue = "test-setMsmtNo")
            @PathVariable("setMsmtNo") String setMsmtNo,
            @ApiParam(required = true, name = "gameMsmtNo", value = "게임 측정번호", defaultValue = "test-gameMsmtNo")
            @PathVariable("gameMsmtNo") String gameMsmtNo,
            @RequestHeader(name = MedicineHeader.HEADER_cponId) String cponId,
            @RequestHeader(name = MedicineHeader.HEADER_serialNo) String serialNo,
            @RequestHeader(name = MedicineHeader.HEADER_cponModel) String cponModel,
            @RequestHeader(name = MedicineHeader.HEADER_osTypeCd) OsType osType,
            @RequestHeader(name = MedicineHeader.HEADER_osVer) String osVer,
            @RequestHeader(name = MedicineHeader.HEADER_pkgNm) String pkgNm,
            @RequestHeader(name = MedicineHeader.HEADER_pkgVer) String pkgVer,
            @RequestHeader(name = MedicineHeader.HEADER_userSeq) int userSeq,
            @Valid @RequestBody GameResult gameResult
    ) {
        GameRst gameRst = gameResult.map(GameRst.class);
        gameRst.setSetMsmtNo(setMsmtNo);
        gameRst.setGameMsmtNo(gameMsmtNo);
        gameRst.setCponId(cponId);
        gameRst.setSerialNo(serialNo);
        gameRst.setCponModel(cponModel);
        gameRst.setOsTypeCd(osType);
        gameRst.setOsVer(osVer);
        gameRst.setPkgNm(pkgNm);
        gameRst.setPkgVer(pkgVer);
        gameRst.setUserSeq(userSeq);
        return gameRstRepository.save(gameRst);
    }

    @ApiOperation(value = "게임 결과")
    @GetMapping(value = "/{rstSeq}") //, params = {"type=game"}
    public GameRst getGame(
            @ApiParam(required = true, name = "rstSeq", value = "게임 SEQ", defaultValue = "1")
            @PathVariable("rstSeq") Integer rstSeq
    ) {
        return gameRstRepository.findOne(rstSeq);
    }

    @ApiOperation(value = "셋트 결과")
    @GetMapping(value = "/sets/{setRstSeq}")
    public GameSetRst getSet(
            @ApiParam(required = true, name = "setRstSeq", value = "셋트 SEQ", defaultValue = "1")
            @PathVariable("setRstSeq") Integer setRstSeq
    ) {
        return gameSetRstRepository.findOne(setRstSeq);
    }

}
