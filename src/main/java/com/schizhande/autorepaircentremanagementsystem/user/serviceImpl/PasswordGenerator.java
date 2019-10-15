package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl;

import java.util.UUID;

class PasswordGenerator {

    private PasswordGenerator() {
    }

    static String generatePassword(int passwordLength) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "")
                .substring(0, passwordLength)
                .toUpperCase();

    }

}
