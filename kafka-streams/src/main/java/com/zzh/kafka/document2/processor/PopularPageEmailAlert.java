package com.zzh.kafka.document2.processor;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

// A processor that sends an alert message about a popular page to a configurable email address
public class PopularPageEmailAlert implements Processor<String, Long, Void, Void> {

    private final String emailAddress;
    private ProcessorContext<Void, Void> context;

    public PopularPageEmailAlert(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void init(ProcessorContext<Void, Void> context) {
        this.context = context;

        // Here you would perform any additional initializations such as setting up an email client.
    }


    @Override
    public void process(Record<String, Long> record) {
        // Here you would format and send the alert email.
        //
        // In this specific example, you would be able to include
        // information about the page's ID and its view count
    }

    @Override
    public void close() {
        // Any code for clean up would go here, for example tearing down the email client and anything
        // else you created in the init() method
        // This processor instance will not be used again after this call.
    }

}
