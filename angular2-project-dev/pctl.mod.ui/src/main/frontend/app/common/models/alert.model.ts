/**
 * This is an Alert Notification Interface
 * Alert types (attribute type)
 * info     : Information box
 * success  : Success Notification
 * warning  : Warning Notification
 * error    : Error Notitication
 * 
 */
export interface Alert {
    type: String;       // Alert type
    header: String;     // Heading of the alert
    message: String;    // Alert message
}
