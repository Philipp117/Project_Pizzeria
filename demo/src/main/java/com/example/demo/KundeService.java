package com.example.demo;


import com.example.demo.db.KundeRepository;
import com.example.demo.model.Kunde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KundeService
{
    private final KundeRepository kundeRepository;

    private final RuntimeService runtimeService;



    @Transactional
    public String newKundenBestellung(String newKundeName, String newStrasse, Integer newHausnummer, String newBestellung, Integer kundeId)
    {
        Kunde k;
        if(kundeId != null && kundeRepository.existsById(kundeId))
        {
            k = kundeRepository.getReferenceById(kundeId);
            updateKunde(newKundeName, newStrasse, newHausnummer, newBestellung, kundeId);
            String kN = k.getKundeName();
            String kS = k.getStrasseName();
            Integer kH = k.getHausNummer();
            String kB = k.getBestellung();
            Integer anzahlBestellungen = k.getAnzahlBestellungen();
            anzahlBestellungen++;
            k.setAnzahlBestellungen(anzahlBestellungen);
            k = kundeRepository.save(k);

            System.out.println("Bestandskunde: " + kN);
            System.out.println("Strasse: " + kS);
            System.out.println("Hausnummer: "+ kH);
            System.out.println("Bestellung: " +kB);
            System.out.println("Anzahl Bestellungen: " + anzahlBestellungen);
            return String.valueOf(kundeId);
        }
        else
        {

            k = new Kunde(newKundeName);

            k.setStrasseName(newStrasse);
            k.setHausNummer(newHausnummer);
            k.setBestellung(newBestellung);
            Integer erstBestellung = 1;
            k.setAnzahlBestellungen(erstBestellung);
            k = kundeRepository.save(k);


            return String.valueOf(k.getKundeId());

        }

    }

    @Transactional(readOnly = true)
    public String showKunde(Integer kundeId)
    {
        Integer kunde_Id = null;

        if(kundeRepository.existsById(kundeId))
        {
            Kunde kId = kundeRepository.getReferenceById(kundeId);  //Kunde anhand der ID auswählen

            String kunde_Name = kId.getKundeName();
            String kunde_Strasse = kId.getStrasseName();
            Integer kunde_Hausnummer = kId.getHausNummer();
            String bestellung = kId.getBestellung();
            Integer anzahl_Bestellungen = kId.getAnzahlBestellungen();
            kunde_Id = kId.getKundeId();


            return kId.toString();

        }
        else
        {
            System.out.println("Kunde nicht vorhanden");
        }
        return String.valueOf(kunde_Id);
    }


    @Transactional
    public String updateKunde(String newKundeName, String newStrasse, Integer newHausnummer, String newBestellung, Integer kundeId)
    {
        Integer iDD = null;
        if(kundeRepository.existsById(kundeId))
        {
            Kunde kId = kundeRepository.getReferenceById(kundeId);

            iDD = kId.getKundeId();

            kId.setKundeName(newKundeName);
            kId.setStrasseName(newStrasse);
            kId.setHausNummer(newHausnummer);
            kId.setBestellung(newBestellung);
            kId = kundeRepository.save(kId);
        }
        else
        {
            System.out.println("Kunde nicht vorhanden!!!");
        }
        return String.valueOf(iDD);
    }


    public Integer sucheKunde(Integer kundeId)
    {
        Kunde kundeID = kundeRepository.findByKundeId(kundeId);

        if(kundeID == null)
        {
            return null;
        }
        else
        {
            return kundeID.getKundeId();
        }
    }


    /*protected void waitForProcessInstance(String businessKey) throws InterruptedException
    {
        boolean ended = false;
        boolean inUserTask = false;
        boolean waiting = false;

        while(!ended && !inUserTask && !waiting)
        {
            Thread.sleep(1000);
            List<HistoricProcessInstance> list = historyService
        }
    }*/


    @Transactional()
    public String StartProcess1(String kundeName, String kundeStrasse, Integer kundeHausnummer, String bestellung, Integer kundeId)
    {


        ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey("Process_Bestellung").setVariables(Map.of("kundeName", kundeName, "kundeStrasse", kundeStrasse, "kundeHausnummer", kundeHausnummer, "bestellung", bestellung, "kundeId", kundeId)).executeWithVariablesInReturn();

        //runtimeService.startProcessInstanceByKey("Process_Bestellung", Map.of("kundeName", kundeName, "kundeStrasse", kundeStrasse, "kundeHausnummer", kundeHausnummer, "bestellung", bestellung, "kundeId", kundeId));

        log.info("fertig");
        log.info("ProzessInstanz ID = " + (String) processInstanceWithVariables.getVariables().get("status"));

        return (String) processInstanceWithVariables.getVariables().get("status");


        //throw new RuntimeException("Fehler!!!");


    }
}
