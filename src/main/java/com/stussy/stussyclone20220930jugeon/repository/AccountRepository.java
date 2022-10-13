package com.stussy.stussyclone20220930jugeon.repository;

import com.stussy.stussyclone20220930jugeon.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //IoC에 등록되고, account.xml이랑 연결
public interface AccountRepository {

    public User findUserByEmail(String email) throws Exception;
    public int saveUser(User user) throws Exception; // insert 되면 1 안되면 0
}
