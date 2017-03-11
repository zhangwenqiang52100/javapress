package com.zwq.service.impl;

import com.zwq.dao.OptionDao;
import com.zwq.domain.OptionInfo;
import com.zwq.service.OptionService;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public OptionInfo findById(int sid) {

        return optionDao.findById(sid);
    }

    @Override
    public OptionInfo getWebOption(int sid) {
        return optionDao.getWebOption(sid);
    }
}
