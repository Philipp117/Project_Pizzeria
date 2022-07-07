package com.example.demo.delegates;


import com.example.demo.KundeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KundeAnlegenDelegate implements JavaDelegate
{

    private final KundeService kundeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception
    {
        log.info("Neue Bestellung, Kunde anlegen ausführen");

        String kN = (String) delegateExecution.getVariable("kundeName");
        String kS = (String) delegateExecution.getVariable("kundeStrasse");
        Integer kH = (Integer) delegateExecution.getVariable("kundeHausnummer");
        String kB = (String) delegateExecution.getVariable("bestellung");
        Integer kId = (Integer) delegateExecution.getVariable("kundeId");

        kundeService.newKundenBestellung(kN, kS, kH, kB, kId);
    }
}
