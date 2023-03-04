package com.example.drones.validation;

import com.example.drones.common.enums.State;
import com.example.drones.model.dto.DroneDto;
import com.example.drones.model.dto.MedicationDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@NoArgsConstructor
@Slf4j
public class DroneValidator {

    public void validate(DroneDto droneDto) {
        Validate.validateSize(droneDto.getSerialNumber(), 100, "Drone serial number");
        Validate.validateSize(droneDto.getWeight(), 500, "Drone weight");
    }

    public void validateMedications(Set<MedicationDto> medications) {
        medications.forEach(medicationDto -> {
            Validate.validateNameContainsLettersNumberDashAndUnderScore(medicationDto.getName(), "Medication name");
            Validate.validateNameContainsUpperLettersNumberAndUnderScore(medicationDto.getCode(), "Medication code");
        });
        validateSumOfAllMedicationWeights(medications);
    }

    private void validateSumOfAllMedicationWeights(Set<MedicationDto> medications) {
        int sumOfAllMedicationWeights = 0;
        for (MedicationDto medication : medications) {
            sumOfAllMedicationWeights += medication.getWeight();
        }
        if(sumOfAllMedicationWeights > 500)
            throw new IllegalArgumentException("The max weight of drone 500gm!");
    }

    public void validateDroneState(State droneState) {
        if(!droneState.equals(State.IDLE))
            throw new IllegalArgumentException("The drone state must be IDLE!");
    }
}
