package org.example.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class InterviewEvaluations {
    private long evaluationId;
    private String interviewId;
    private String intervieweeId;
    private String interviewerId;
    private int comprehensiveScore;
    private int languageExpression;
    private int logicalThinking;
    private int situationalResponse;
    private int professionalKnowledge;
    private int personalQuality;
    private String comments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;
    private String result;
    private String position;
}
