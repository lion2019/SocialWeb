package com.social.domain;

/**
 * 定義血型
 */
public enum BloodType {
    O,A,B,AB;

    @Override
    public String toString() {
        return this.name() + '型';
    }
}
