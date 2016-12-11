package com.zwq.dao.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.Option;
import com.zwq.domain.OptionEnum;
import com.zwq.domain.OptionInfo;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Archer on 2016/12/11.
 */
public class OptionDaoImpl implements OptionDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOption(OptionInfo optionInfo) {
        System.out.println("OptionDao执行了，，，，，");
        Session session = getSessionFactory().getCurrentSession();
        Option option = new Option(OptionEnum.stitle.toString(), optionInfo.getStitle());
        System.out.println(option);
        session.save(option);
    }
}
