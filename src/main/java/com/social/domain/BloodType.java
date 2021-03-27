package com.social.domain;

public enum BloodType {
    O,A,B,AB;

    @Override
    public String toString() {
        return this.name() + '型';
    }
}
