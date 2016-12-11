package com.zwq.service.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.OptionInfo;
import com.zwq.service.OptionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Archer on 2016/12/11.
 */
@Transactional
public class OptionServiceImpl implements OptionService{
private OptionDao optionDao;

    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    @Override
    public void saveOption(OptionInfo optionInfo) {
        optionDao.saveOption(optionInfo);
    }
}
