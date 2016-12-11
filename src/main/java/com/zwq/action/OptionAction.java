package com.zwq.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zwq.domain.Option;
import com.zwq.domain.OptionEnum;
import com.zwq.domain.OptionInfo;
import com.zwq.service.OptionService;

/**
 * Created by Archer on 2016/12/10.
 */
public class OptionAction extends ActionSupport implements ModelDriven<OptionInfo>{
    private  OptionInfo optionInfo=new OptionInfo();
    private OptionService optionService;

    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    public OptionInfo getModel() {
        return optionInfo;
    }
    public String saveOptionWebInfo(){
        optionService.saveOption(optionInfo);
        return SUCCESS;
    }
}
