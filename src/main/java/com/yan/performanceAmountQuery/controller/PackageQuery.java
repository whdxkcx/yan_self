package com.yan.performanceAmountQuery.controller;

import com.yan.core.annotation.MapperInject;
import com.yan.core.controller.BaseController;
import com.yan.core.model.PageModel;
import com.yan.performanceAmountQuery.mapper.PerformanceamountMapper;
import com.yan.performanceAmountQuery.model.Performanceamount;
import com.yan.performanceAmountQuery.model.PerformanceamountExample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/packageQuery")
public class PackageQuery extends BaseController {

    @MapperInject(PerformanceamountMapper.class)
    private PerformanceamountMapper performanceamountMapper;


    @RequestMapping("/amountQuery")
    public String amountQuery() {
        return "/package/amountQuery";
    }

    @RequestMapping("/queryByCondition")
    @ResponseBody
    public PageModel<Performanceamount> amountQueryByCondition(int offset, int limit, String search, String sort, String order) {
        this.offsetPage(offset, limit);

        PerformanceamountExample performanceamountExample = new PerformanceamountExample();
        performanceamountExample.setOrderByClause("year DESC,month,agentName");
        PerformanceamountExample.Criteria criteria = performanceamountExample.createCriteria();

        System.out.println("search:" + search);

        String[] strings = search.split("-");

        if (!strings[1].equalsIgnoreCase("all"))
            criteria.andYearLike("%" + strings[1] + "%");
        if (!strings[2].equalsIgnoreCase("all"))
            criteria.andMonthLike("%" + strings[2] + "%");
        if (strings.length > 3) {
            if (!isNull(strings[3]))
                criteria.andAgentnameLike("%" + strings[3] + "%");
        }

        List<Performanceamount> list = performanceamountMapper.selectByExample(performanceamountExample);
        System.out.println(list);

        return this.resultPage(list);

    }
}
