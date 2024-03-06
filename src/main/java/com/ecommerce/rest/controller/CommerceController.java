package com.ecommerce.rest.controller;


import java.util.List;
import java.util.ArrayList;

import com.ecommerce.rest.model.Ware;
import com.ecommerce.rest.exception.ResourceNotFoundException;
import com.ecommerce.rest.exception.WareNotFoundException;
import com.ecommerce.rest.repository.WaresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommerceController {

    @Autowired
    WaresRepository waresRepository;

    @GetMapping("/wares")
    public ResponseEntity<List<Ware>> getAllWares(@RequestParam(required = false) String name) {
        try {

            List<Ware> wares = new ArrayList<>();
            if (name == null)
                waresRepository.findAll().forEach(wares::add);
            else
                waresRepository.findByNameContaining(name).forEach(wares::add);

            if (wares.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                
            }
            return new ResponseEntity<>(wares, HttpStatus.OK);

        } catch(Exception e){
            e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/ware/{id}")
    public ResponseEntity<Ware> getWareById(@PathVariable(value = "id") long wareId) throws ResourceNotFoundException {
            Ware ware = waresRepository.findById(wareId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find ware with id of: " + wareId));

            return ResponseEntity.ok(ware);
    }

    @PutMapping("/update-ware/{id}")
    public ResponseEntity<Ware> updateWare(@PathVariable(value = "id") long wareId, @RequestBody Ware updatedWare) throws WareNotFoundException {
        Ware ware = waresRepository.findById(wareId)
                .orElseThrow(() -> new WareNotFoundException("Could not find ware with id of: " + wareId));

        ware.setName(updatedWare.getName());
        ware.setPrice(updatedWare.getPrice());

        waresRepository.save(ware);

        return ResponseEntity.ok(ware);
    }

    @PostMapping("/sell-ware")
    public ResponseEntity<Ware> addWare(@RequestBody Ware ware) {
        try {
            
            Ware _ware = waresRepository
                .save(new Ware(ware.getName(), ware.getPrice(), ware.getOwner()));
			return new ResponseEntity<>(_ware, HttpStatus.CREATED);

        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete-ware/{id}")
    public ResponseEntity<Ware> deleteWare(@PathVariable(value = "id") long id) throws WareNotFoundException {
        // Only owners should be able to delete their own wares

        Ware ware = waresRepository.findById(id)
            .orElseThrow(() -> new WareNotFoundException("Could not find ware with id: " + id));

        waresRepository.delete(ware);

        return ResponseEntity.ok(ware);

    }


}
