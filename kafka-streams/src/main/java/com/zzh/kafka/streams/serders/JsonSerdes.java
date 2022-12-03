package com.zzh.kafka.streams.serders;

import com.zzh.kafka.streams.module.*;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2022/11/28 21:29
 */
public class JsonSerdes {

    static public class WrapperSerde<T> implements Serde<T> {
        final private Serializer<T> serializer;
        final private Deserializer<T> deserializer;

        public WrapperSerde(Serializer<T> serializer, Deserializer<T> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
        }

        @Override
        public Serializer<T> serializer() {
            return serializer;
        }

        @Override
        public Deserializer<T> deserializer() {
            return deserializer;
        }
    }


    private final static class TransactionRewardSerde extends WrapperSerde<TransactionReward> {
        public TransactionRewardSerde(Serializer<TransactionReward> serializer, Deserializer<TransactionReward> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static TransactionRewardSerde transactionRewardSerde() {
        return new TransactionRewardSerde(new JsonSerializer<>(), new JsonDeserializer<>(TransactionReward.class));
    }


    public static TransactionSerde transactionSerde() {
        return new TransactionSerde(new JsonSerializer<>(), new JsonDeserializer<>(Transaction.class));
    }

    private final static class TransactionSerde extends WrapperSerde<Transaction> {
        public TransactionSerde(Serializer<Transaction> serializer, Deserializer<Transaction> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static SalesSerde salesSerde() {
        return new SalesSerde(new JsonSerializer<>(), new JsonDeserializer<>(Sales.class));
    }

    private final static class SalesSerde extends WrapperSerde<Sales> {
        public SalesSerde(Serializer<Sales> serializer, Deserializer<Sales> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static SalesStatusSerde salesStatusSerde() {
        return new SalesStatusSerde(new JsonSerializer<>(), new JsonDeserializer<>(SalesStatus.class));
    }

    private final static class SalesStatusSerde extends WrapperSerde<SalesStatus> {
        public SalesStatusSerde(Serializer<SalesStatus> serializer, Deserializer<SalesStatus> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static NetTrafficSerde netTrafficSerde() {
        return new NetTrafficSerde(new JsonSerializer<>(), new JsonDeserializer<>(NetTraffic.class));
    }

    private final static class NetTrafficSerde extends WrapperSerde<NetTraffic> {

        public NetTrafficSerde(Serializer<NetTraffic> serializer, Deserializer<NetTraffic> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static PatientSerde patientSerde() {
        return new PatientSerde(new JsonSerializer<>(), new JsonDeserializer<>(Patient.class));
    }

    private static final class PatientSerde extends WrapperSerde<Patient> {
        public PatientSerde(Serializer<Patient> serializer, Deserializer<Patient> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static final class SickRomeSerde extends WrapperSerde<SickRome> {
        public SickRomeSerde(Serializer<SickRome> serializer, Deserializer<SickRome> deserializer) {
            super(serializer, deserializer);
        }
    }

    public static SickRomeSerde sickRomeSerde() {
        return new SickRomeSerde(new JsonSerializer<>(), new JsonDeserializer<>(SickRome.class));
    }

    public static PatientWithSickRomeSerde patientWithSickRomeSerde() {
        return new PatientWithSickRomeSerde(new JsonSerializer<>(), new JsonDeserializer<>(PatientWithSickRome.class));
    }

    private static final class PatientWithSickRomeSerde extends WrapperSerde<PatientWithSickRome> {
        public PatientWithSickRomeSerde(Serializer<PatientWithSickRome> serializer, Deserializer<PatientWithSickRome> deserializer) {
            super(serializer, deserializer);
        }
    }
}
