package com.omnicns.medicine.service;

import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /* 퀵 트레이닝 */
    public Page<Adm> list(Pageable pageable) {
        return accountRepository.findAllByOrderByAdmSeqDesc(pageable);
    }

    public Adm create(Adm adm) {
        return accountRepository.save(adm);
    }

    public Adm update(Adm adm) {
        return accountRepository.save(adm);
    }

    public void delete(int admSeq) {
        accountRepository.delete(admSeq);
    }

}
