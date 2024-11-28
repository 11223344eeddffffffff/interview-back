package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.InterviewEvaluations;

public interface InterviewEvaluationsService extends IService<InterviewEvaluations> {
    public IPage<InterviewEvaluations> getAllRecord(int pageNo, int pageSize, String condition);

    public IPage<InterviewEvaluations> selectById(int pageNo, int pageSize, String condition);
}
