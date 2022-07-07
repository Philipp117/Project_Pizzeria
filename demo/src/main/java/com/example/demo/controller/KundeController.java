package com.example.demo.controller;

import camundajar.impl.scala.Int;
import com.example.demo.KundeService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/kunde")
@RequiredArgsConstructor
public class KundeController
{

    private final KundeService kundeService;

    private final RuntimeService runtimeService;

    @PostMapping    //Neuer Kunde anlegen
    public String neueBestellung(@RequestBody String newKundeName, String newStrasse, Integer newHausnummer, String newBestellung, Integer kundeID)
    {
        return kundeService.newKundenBestellung(newKundeName, newStrasse, newHausnummer, newBestellung, kundeID);
    }


    @GetMapping(path = "{Id}")      //Kunde abfragen
    public String showKunde(@PathVariable("Id") Integer kundeId)
    {
        return kundeService.showKunde(kundeId);
    }


    @PutMapping
    public String updateKunde(@RequestBody String newKundeName, String newStrasse, Integer newHausnummer, String newBestellung, Integer kundeId)
    {
        return kundeService.updateKunde(newKundeName, newStrasse, newHausnummer, newBestellung, kundeId);
    }

    @PostMapping(value = "/kundeBestellungProcess")
    public String StartProcess(@RequestBody String kundeName, String kundeStrasse, Integer kundeHausnummer, String bestellung, Integer kundeId)
    {

        return kundeService.StartProcess1(kundeName, kundeStrasse, kundeHausnummer, bestellung, kundeId);

    }
}
