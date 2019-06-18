package com.lgu.cbc.cell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
@Slf4j
public class WorkerService {
    // DB에서 셀정보를 셋팅한다
    public List<Object> makeMap() {
        return null;
    }
    // 파일에서 셀정보를 가져온다
    public List<Object> parseRawCell() {
        return null;
    }
    // DB에서 backup Cell
    public List<Object> backupMap() {
        return null;
    }
    // DB에서 save Cell
    public List<Object> saveCell() {
        return null;
    }
    // DB에서 save tempCell
    public List<Object> saveTempCell() {
        return null;
    }
    // DB에서 save CellCode
    public List<Object> saveCellCode() {
        return null;
    }
    // DB에서 update CellCode
    public List<Object> updateCellCode() {
        return null;
    }
    // DB에서 cell 정보해서 jsonMakeCell
    public List<Object> jsonMakeCell() {
        return null;
    }
    // DB에서 cell 정보해서 jsonMakeVersion
    public List<Object> jsonMakeVersion() {
        return null;
    }
}
