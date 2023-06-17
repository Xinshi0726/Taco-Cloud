package sia.tacocloud.sql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.tacos.UserInfo;
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);


}
