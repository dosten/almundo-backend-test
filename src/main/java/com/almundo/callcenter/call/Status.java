package com.almundo.callcenter.call;

/**
 * These are the three possible statuses for a call:
 *
 *  - If the call status is STANDBY, that means the call is waiting for an answer.
 *  - If the call status is CONNECTED, that means a employee has been assigned to that call.
 *  - If the call status is ENDED, that means the call has been answered and already ended.
 */
public enum Status {
    STANDBY,
    CONNECTED,
    ENDED
}
