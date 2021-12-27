package com.arkarmoe.springbootjwt.repo;

import com.arkarmoe.springbootjwt.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Arkar on 27-Dec-2021
 * **/
@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {
}
