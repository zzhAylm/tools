package com.zzh.kafka.document2.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.Locale;

public class WordCountProcessor implements Processor<String, String, String, String> {
    private KeyValueStore<String, Integer> kvStore;

    @Override
    public void init(final ProcessorContext<String, String> context) {
        context.schedule(Duration.ofSeconds(1), PunctuationType.STREAM_TIME, timestamp -> {
            try (final KeyValueIterator<String, Integer> iter = kvStore.all()) {
                while (iter.hasNext()) {
                    final KeyValue<String, Integer> entry = iter.next();
                    context.forward(new Record<>(entry.key, entry.value.toString(), timestamp));
                }
            }
        });
        kvStore = context.getStateStore("Counts");
    }



    @Override
    public void process(final Record<String, String> record) {
        final String[] words = record.value().toLowerCase(Locale.getDefault()).split("\\W+");

        for (final String word : words) {
            final Integer oldValue = kvStore.get(word);

            if (oldValue == null) {
                kvStore.put(word, 1);
            } else {
                kvStore.put(word, oldValue + 1);
            }
        }
    }

    @Override
    public void close() {
        // close any resources managed by this processor
        // Note: Do not close any StateStores as these are managed by the library
    }
}
