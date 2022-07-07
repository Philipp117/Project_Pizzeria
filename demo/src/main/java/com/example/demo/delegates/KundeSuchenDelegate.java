package com.example.demo.delegates;

import com.example.demo.KundeService;
import com.example.demo.db.KundeRepository;
import com.example.demo.model.Kunde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Component
@Slf4j
@RequiredArgsConstructor
public class KundeSuchenDelegate implements JavaDelegate
{

    private final KundeService kundeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception
    {
        log.info("Kunde suchen ausführen");

        Integer kId = (Integer) delegateExecution.getVariable("kundeId");

        Integer kID;

        kID = kundeService.sucheKunde(kId);

        delegateExecution.setVariable("kundeId", kID);
        //throw new RuntimeException("Fehler!!!");
    }
}
