package com.example.controllers;

import com.example.config.ServiceConfig;
import com.example.domain.Cargo;
import com.example.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/customers/{customerId}/cargos")
public class CargoServiceController {
    @Autowired
    private CargoService cargoService;

    @Autowired
    private ServiceConfig serviceConfig;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Cargo> getCargos(@PathVariable("customerId") Long customerId) {
        return cargoService.getCargosByCustomer(customerId);
    }

    @RequestMapping(value = "/{cargoId}", method = RequestMethod.GET)
    public Cargo getCargo(@PathVariable("customerId") Long customerId,
                          @PathVariable("cargoId") Long cargoId) {
        return cargoService.getCargo(cargoId).setProduct(serviceConfig.getExampleProduct());
    }

    @RequestMapping(value = "{cargoId}", method = RequestMethod.PUT)
    public String updateLicenses(@PathVariable("licenseId") Long cargoId) {
        return String.format("This is the put");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveCargo(@RequestBody Cargo cargo) {
        cargoService.saveCargo(cargo);
    }

    @RequestMapping(value = "{cargoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCargo(@PathVariable("cargoId") Long cargoId) {
        cargoService.deleteCargo(cargoId);
        System.out.println(String.format("This is the Delete"));
        return;
    }
}
