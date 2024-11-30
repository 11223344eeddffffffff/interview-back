package org.example.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.example.domain.InterviewEvaluations;
import org.example.mapper.InterviewEvaluationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class interviewEvaluationsServiceImpl extends ServiceImpl<InterviewEvaluationsMapper, InterviewEvaluations>implements InterviewEvaluationsService {
    @Autowired
    InterviewEvaluationsMapper interviewEvaluationsMapper;

    @Override
    public IPage<InterviewEvaluations> getAllRecord(int pageNo, int pageSize, String condition) {
        LambdaQueryWrapper<InterviewEvaluations> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StrUtil.isNotEmpty(condition),InterviewEvaluations::getIntervieweeId,condition);
        IPage page = new Page<>(pageNo, pageSize);
        return interviewEvaluationsMapper.selectPage(page, lqw);
    }

    @Override
    public IPage<InterviewEvaluations> selectById(int pageNo, int pageSize, String condition) {
        LambdaQueryWrapper<InterviewEvaluations> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StrUtil.isNotEmpty(condition),InterviewEvaluations::getInterviewId,condition);
        IPage page = new Page<>(pageNo, pageSize);
        return interviewEvaluationsMapper.selectPage(page, lqw);
    }
}
