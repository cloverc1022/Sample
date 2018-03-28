package com.mmc.sampletest.bean;

import java.util.List;

/**
 * Created by 上海滩小马哥 on 2018/03/28.
 */

public class SearchResultBean {
    public String status; //0：请求失败；dialog_1：请求成功
    public String info; //错误原因
    public String infocode;
    public String count; //搜索方案数目(最大值为1000)
    public List<PoisInfoBean> pois;
}
