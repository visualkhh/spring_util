package com.visualkhh.api.service;

import com.visualkhh.api.domain.Brd;
import com.visualkhh.api.repository.BrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrdService {
    @Autowired
    private BrdRepository brdRepository;

    // TODO: BBC 코드화로 바꿔야 함

    /* 공지사항 */
    public List<Brd> notice() {
        return brdRepository.findByCateCdAndUseYnOrderByGrpNoDescGrpOrdAsc("BCC001", "Y");
    }

    /* 도움말 */
    public List<Brd> faq() {
        return brdRepository.findByCateCdAndUseYnOrderByGrpNoDescGrpOrdAsc("BCC002", "Y");
    }

    /* 가이드 */
    public List<Brd> guide() {
        return brdRepository.findByCateCdAndUseYnOrderByGrpNoDescGrpOrdAsc("BCC003", "Y");
    }
}
