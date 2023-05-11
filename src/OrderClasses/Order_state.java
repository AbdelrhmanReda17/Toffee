package OrderClasses;


/**
 * Represents the state of an order.
 */
public enum Order_state {
    /**
     * The order is in progress.
     */
    IN_PROGRESS,

    /**
     * The order has been placed.
     */
    ORDERED,

    /**
     * The order is being delivered.
     */
    IN_DELIVERY,

    /**
     * The order has been canceled.
     */
    CANCELED,

    /**
     * The order has been delivered.
     */
    DELIVERED
}
