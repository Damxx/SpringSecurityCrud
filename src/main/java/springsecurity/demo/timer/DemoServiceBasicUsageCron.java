package springsecurity.demo.timer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springsecurity.demo.entity.Workers_salary;
import springsecurity.demo.service.UserService;
import springsecurity.demo.serviceEmailCycleTime.MailBoxForCron;

@Component
public class DemoServiceBasicUsageCron {

    @Autowired
    UserService userservice;

    @Autowired
    MailBoxForCron javaMailUtil;


    @Scheduled(cron = "30 31 15 * * ?")
    public void demoServiceMethod() {


        java.util.List<Workers_salary> workersList = userservice.getWorkers_salaries();

        //MailBoxForCron javaMailUtil = new MailBoxForCron(workersList,authentication.getName());

        javaMailUtil.setEmailParam(workersList, "Mary");

        System.out.println("Method executed at 15:30 ");

    }
}
