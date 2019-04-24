/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package cn.happyzhong.basics;

import java.util.Objects;

public class Multinomial {
    private int coef;
    private int exp;

    public Multinomial(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return coef + "x^" + exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Multinomial)) return false;
        Multinomial that = (Multinomial) o;
        return exp == that.exp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coef, exp);
    }
}
