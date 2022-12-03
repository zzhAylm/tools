package com.zzh.kafka.streams.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/12/3 23:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientWithSickRome implements Serializable {
    private SickRome sickRome;
    private Patient patient;
    private Long heatBeat;

    public PatientWithSickRome( Patient patient,SickRome sickRome) {
        this.sickRome = sickRome;
        this.patient = patient;
    }


}
