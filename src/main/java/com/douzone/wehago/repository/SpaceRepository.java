package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Space;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.postgresql.shaded.com.ongres.scram.common.util.CharAttribute;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SpaceRepository {

    private final SqlSession sqlSession;
    public List<Space> findspaceList(ReservationDTO reservationDTO){
        return sqlSession.selectList("com.douzone.wehago.mapper.SpaceMapper.findspaceList",reservationDTO);
    }
    public void save(Space space) {
        sqlSession.insert("com.douzone.wehago.mapper.SpaceMapper.save", space);
    }

    public List<Space> findAll() {
        return sqlSession.selectList("com.douzone.wehago.mapper.SpaceMapper.findAll");
    }

    public List<Space> searchSpace(String columnName, String searchString) {
        Map<String, String> map = new HashMap<>();
        map.put("columnName", converCamelToSnakeCase(columnName));
        map.put("searchString", searchString);
        return sqlSession.selectList("com.douzone.wehago.mapper.SpaceMapper.searchSpace", map);
    }

    public Space findOne (Integer spc_seq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.SpaceMapper.findOne", spc_seq);
    }

    public Integer update (Space space) {
        return  sqlSession.update("com.douzone.wehago.mapper.SpaceMapper.update", space);
    }

    public Space delete (Space space) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.SpaceMapper.delete", space);
    }

    private String converCamelToSnakeCase (String camelCase) {

        StringBuilder snakeCase = new StringBuilder();

        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_').append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }
}
