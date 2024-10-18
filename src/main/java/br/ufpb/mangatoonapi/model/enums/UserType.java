package br.ufpb.mangatoonapi.model.enums;

public enum UserType {
    WRITER(0),
    READER(1);

    private final int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserType fromId(int id) {
        for (UserType userType : UserType.values()) {
            if (userType.getId() == id) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Invalid UserType id: " + id);
    }
}

