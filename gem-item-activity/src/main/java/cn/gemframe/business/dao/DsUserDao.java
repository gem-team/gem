package cn.gemframe.business.dao;

import cn.gemframe.business.domain.GemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DsUserDao extends JpaRepository<GemUser, Long>,JpaSpecificationExecutor<GemUser>{

}
