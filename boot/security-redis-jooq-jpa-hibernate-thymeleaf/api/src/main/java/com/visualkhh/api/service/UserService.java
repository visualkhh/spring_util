package com.visualkhh.api.service;

import com.visualkhh.api.domain.DvcInfo;
import com.visualkhh.api.domain.User;
import com.visualkhh.api.domain.UserDvc;
import com.visualkhh.api.model.ApiHeader;
import com.visualkhh.api.repository.DvcInfoRepository;
import com.visualkhh.api.repository.UserDvcRepository;
import com.visualkhh.api.repository.UserRepository;
import com.visualkhh.common.code.Code;
import com.visualkhh.common.exception.ErrorException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired UserDvcRepository userDvcRepository;
    @Autowired DvcInfoRepository dvcInfoRepository;
    @Autowired SessionFactory sessionFactory;
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findFirstByCponId(String cponId) {
        User user = userRepository.findFirstByCponId(cponId);
        return user;
    }

    public UserDvc findUserDvcOne(Integer userDvcSeq) {
        UserDvc dvcInfo = userDvcRepository.findOne(userDvcSeq);
        return dvcInfo;
    }
    public UserDvc findFirstByUserSeqAndDvcSeq(Integer userSeq, Integer dvcSeq) {
        UserDvc dvcInfo = userDvcRepository.findFirstByUserSeqAndDvcSeq(userSeq, dvcSeq);
        return dvcInfo;
    }

    public UserDvc saveUserDvc(UserDvc userDvc) {
        return userDvcRepository.save(userDvc);
    }
    public Integer setUserDvcAgeCdAndGenCd(Integer userDvcSeq, String ageCd, String genCd) {
        //userDvcRepository.setUserDvcAgeCdAndGenCd(userDvcSeq, genCd, ageCd);
        Session s = ((SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory)).getSession();
        Query q = s.createQuery("update UserDvc as root set root.ageCd=:ageCd, root.genCd=:genCd where root.userDvcSeq=:userDvcSeq");
        q.setParameter("userDvcSeq",userDvcSeq);
        q.setParameter("ageCd",ageCd);
        q.setParameter("genCd",genCd);
        return q.executeUpdate();
    }





    @Transactional
    public ApiHeader headerToUserAndDevMerge(ApiHeader header){
        /////////db check
        //userDvcSeq가 존재하지 않을경우
        //장비 원장안에 존재하지 않으면 Reject
        //사용자 번호 들어왔을경우
        // 조회후 있을경우 사용중지면 Reject
        // 조회후 없는경우 사용자, 디바이스 Insert
        //  -> 디바이스 정보 확인후 SeqInsert
        if(StringUtils.isEmpty(header.getUserDvcSeq()) && !StringUtils.isEmpty(header.getSerialNo()) && !StringUtils.isEmpty(header.getCponId())){
            DvcInfo dvcInfo = dvcInfoRepository.findFirstBySerialNo(header.getSerialNo());
            if(null==dvcInfo){
                throw new ErrorException(Code.E10101);
            }
            User user = userRepository.findFirstByCponId(header.getCponId());
            if(null==user){
                user = new User();
                user.setCponId(header.getCponId());
                user.setUseYn("Y");
                user.setLstLginDt(ZonedDateTime.now());
                user = userRepository.save(user);
            }else if("N".equals(user.getUseYn())){
                throw new ErrorException(Code.E10103);
            }

            UserDvc userDvc = userDvcRepository.findFirstByUserSeqAndDvcSeq(user.getUserSeq(),dvcInfo.getDvcSeq());
            if(null==userDvc) {
                userDvc = new UserDvc();
                userDvc.setUserSeq(user.getUserSeq());
                userDvc.setDvcSeq(dvcInfo.getDvcSeq());
                userDvcRepository.save(userDvc);
            }
            header.setUserDvcSeq(userDvc.getUserDvcSeq());
        }else{//userDvcUser가 존재할경우 serialNo와 cponId를 체크한다
            UserDvc userDvc = userDvcRepository.findOne(header.getUserDvcSeq());
            if(null==userDvc || null==userDvc.getDvcInfo() || null==userDvc.getUser() || (!userDvc.getDvcInfo().getSerialNo().equals(header.getSerialNo()) && !userDvc.getUser().getCponId().equals(header.getCponId()))){
                throw new ErrorException(Code.E10104);
            }
        }
        return header;
    }


}
