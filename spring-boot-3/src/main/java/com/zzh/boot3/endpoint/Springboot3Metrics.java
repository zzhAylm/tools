package com.zzh.boot3.endpoint;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/6/20 17:57
 */
public class Springboot3Metrics {

    private static final Set<Tag> tags = new HashSet<>();

    static {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String ipAddress = localHost.getHostAddress();
        tags.add(Tag.of("instance", ipAddress));
    }


    private static final Counter emailMsgFailCounter = Metrics.counter("MetricsController_test", tags);
    private static final Counter applicatonMsgFailCounter = Metrics.counter("notifier_email_message_fail_total", tags);
    private static final Counter groupMsgFailCounter = Metrics.counter("notifier_group_message_fail_total", tags);
    private static final Counter robotMsgFailCounter = Metrics.counter("notifier_robot_message_fail_total", tags);
    private static final Counter smsMsgFailCounter = Metrics.counter("notifier_sms_message_fail_total", tags);
    private static final Counter voiceMsgFailCounter = Metrics.counter("notifier_voice_message_fail_total", tags);


    public static void incrEmailMsgFailCounter() {
        emailMsgFailCounter.increment();
        Metrics.gauge("notifier_email_message_fail", tags, 1);
    }

    public static void incrGroupMsgFailCounter() {
        groupMsgFailCounter.increment();
        Metrics.gauge("notifier_group_message_fail", tags, 1);
    }

    public static void incrApplicationMsgFailCounter() {
        applicatonMsgFailCounter.increment();
        Metrics.gauge("notifier_application_message_fail", tags, 1);
    }

    public static void incrRobotMsgFailCounter() {
        robotMsgFailCounter.increment();
        Metrics.gauge("notifier_robot_message_fail", tags, 1);
    }

    public static void incrSmsMsgFailCounter() {
        smsMsgFailCounter.increment();
        Metrics.gauge("notifier_sms_message_fail", tags, 1);
    }

    public static void incrVoiceMsgFailCounter() {
        voiceMsgFailCounter.increment();
        Metrics.gauge("notifier_voice_message_fail", tags, 1);
    }
}
