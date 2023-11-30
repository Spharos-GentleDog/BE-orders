//package egenius.orders.domain.batch.presentation;
//
//import egenius.orders.global.common.exception.BaseException;
//import egenius.orders.global.common.response.BaseResponse;
//import egenius.orders.global.common.response.BaseResponseStatus;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PaymentTransferJobLauncher {
//    private final JobLauncher jobLauncher;
//    private final Job paymentJob;
//
//    @Scheduled(cron = "${spring.scheduler.daily_settlement_job_launcher.cron}",
//            zone = "${spring.scheduler.daily_settlement_job_launcher.zone}")
//    public BaseResponse paymentDataTransferBatch() {
//        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        // jobParams : job을 실행할때 넘겨주고싶은 파라미터 & job을 고유하게 식별하는 역할
//        // 중복방지 : jobParams에 addDate만 남겨두면, 키는 일정하고 params도 하루동안은 일정하기에 중복 실행을 방지할 수 있음
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("Date", today)
//                .addLong("time",System.currentTimeMillis())
//                .toJobParameters();
//        try {
//            JobExecution jobExecution = jobLauncher.run(paymentJob,jobParameters);
//            if (jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
//                log.info("job success");
//            } else {
//                log.info("job failed");
//                log.info("fail code: " + jobExecution.getExitStatus());
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new BaseException(BaseResponseStatus.PAYMENT_DATA_TRANSFER_FAILED);
//        }
//        return new BaseResponse();
//    }
//}
