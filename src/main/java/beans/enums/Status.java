package beans.enums;

public enum Status {

    sold, //The machine has been sold

    unavailable, //The machine is in reparation/revision

    reserved, //The machine is used in a uncompleted command

    available //The machine is ready for service but can be planed for a rent
}
