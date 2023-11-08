package egenius.orders.domain.batch.presentation;

import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponse;
import egenius.orders.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/orders/payment/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {
    // batch
    private final JobLauncher jobLauncher;
    private final Job paymentJob;
    // service

    /**
     * Spring batch
     * 1. 결제 내역 전송
     */

    //1. 결제 내역, 정산 서버로 전송
    @GetMapping("")
    public BaseResponse paymentDataTransferBatch() {
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        JobParameters jobParameters = new JobParametersBuilder()
                        .addDate("Date:"+LocalDateTime.now(), today)
                        .toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(paymentJob, jobParameters);
            if (jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                log.info("job success");
            } else {
                log.info("job failed");
                log.info("fail code: " + jobExecution.getExitStatus());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.PAYMENT_DATA_TRANSFER_FAILED);
        }
        return new BaseResponse();
    }
}
