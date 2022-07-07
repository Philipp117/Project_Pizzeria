package com.example.demo.listener;


import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.Plugin;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.Expression;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityListeners;
import java.net.http.WebSocket;

@Component
@Slf4j
public class KundeSuchenListener implements ExecutionListener
{

    public void notify(DelegateExecution execution) throws Exception
    {
        String processBaseInfo = buildProcessBaseInfo(execution);
        log.info(processBaseInfo);
    }

    private String buildProcessBaseInfo(DelegateExecution execution)
    {
        return "\n"
                + "ProzessBusinessKey: " + execution.getProcessBusinessKey() + "\n"
                + "ProzessInstanz ID: " + execution.getProcessInstanceId() +"\n"
                + "Aktuelle Activity: " + execution.getCurrentActivityName() +"\n"
                + "Prozessvariable KundeID: " + execution.getVariable("kundeId") +"\n"
                + "Event Name: " + execution.getEventName();
    }

}
