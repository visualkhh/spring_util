package com.clone.chat.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import java.util.Arrays;

public class ModelBase {
    public <T> T map(Class<T> classs){
        return map(classs, null);
    }
    public <S, D> D map(Class<D> classs, PropertyMap<S, D>... propertyMap){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if(null!=propertyMap && propertyMap.length > 0) {
            Arrays.stream(propertyMap).forEach(it->mapper.addMappings(it));
        }
        return mapper.map(this, classs);
    }

    /*
    Matching Strategy 변경
        modelMapper.getConfiguration() .setMatchingStrategy(MatchingStrategies.LOOSE);

        Matching Strategy
        Standard
        - Default Strategy
        - 토큰들은 어떤 순서로든 매칭될 수 있다.
        Ex. appleBanana ==> apple & banana == banana & apple
        - 모든 Destination 객체의 프라퍼티 토큰들은 매칭되어야 한다
        - 모든 Source 객체의 프라퍼티들은 하나 이상의 토큰이 매칭되어야 한다

        Loose
        - 가장 느슨한 방식의 매핑. 대부분의 상황에서 잘못된 매핑은 큰일이 나기 때문에.. 잘 사용되지 않는다.

        Strict
        - Source, Destination 의 프라퍼티가 완전히 똑같아야 매핑된다.
        - 예상치 못한 변환이 있으면 안될 때 사용한다. (Strict사용을 권장)
        [출처] [JAVA] ModelMapper Customizing|작성자 뽕


     */
    public void include(Object frome) {
        include(frome, MatchingStrategies.STRICT);
    }
    public void include(Object frome, MatchingStrategy matchingStrategy) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(matchingStrategy);
        mapper.map(frome, this);
    }
//    public void mapTo(Object to) {
//        new ModelMapper().map(this, frome);
//    }
}
