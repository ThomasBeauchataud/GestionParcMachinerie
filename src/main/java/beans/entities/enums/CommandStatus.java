package beans.entities.enums;

public enum CommandStatus {

    created, // the command is just created and it can be modified

    inProgress, //the command is in progress and waiting for payment to be terminated

    terminated //the command is terminated, payed and it can't be modified anymore

}
