package com.xx.中大机试;

public class Fraction {
    // 分子、分母成员变量
    private int molecule;
    private int denominator;

    // 1. 默认构造函数，初始值为1
    public Fraction() {
        this(1, 1);
    }

    // 2. 拷贝构造函数
    public Fraction(Fraction other) {
        this.molecule = other.molecule;
        this.denominator = other.denominator;
    }

    // 3. 带分子、分母的构造函数
    public Fraction(int molecule, int denominator) {
        if (denominator != 0) {
            // 统一符号规则：负号固定在分子上，分母保持正数
            if (denominator < 0) {
                molecule = -molecule;
                denominator = -denominator;
            }
            // 计算最大公约数，完成约分
            int gcdValue = gcd(Math.abs(molecule), denominator);
            this.molecule = molecule / gcdValue;
            this.denominator = denominator / gcdValue;
        } else {
            // 分母为0时直接赋值，输出时处理为NaN
            this.molecule = molecule;
            this.denominator = 0;
        }
    }

    // 辗转相除法
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 4. 四则运算
    // 加法运算
    public Fraction add(Fraction other) {
        int newMolecule = this.molecule * other.denominator + other.molecule * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newMolecule, newDenominator);
    }

    // 减法运算
    public Fraction subtract(Fraction other) {
        int newMolecule = this.molecule * other.denominator - other.molecule * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newMolecule, newDenominator);
    }

    // 乘法运算
    public Fraction multiply(Fraction other) {
        int newMolecule = this.molecule * other.molecule;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newMolecule, newDenominator);
    }

    // 除法运算
    public Fraction divide(Fraction other) {
        int newMolecule = this.molecule * other.denominator;
        int newDenominator = this.denominator * other.molecule;
        return new Fraction(newMolecule, newDenominator);
    }

    // 5. 输出流重载（Java通过重写toString实现规范输出）
    @Override
    public String toString() {
        // 分母为0，输出NaN
        if (denominator == 0) {
            return "NaN";
        }
        // 分子为0，输出0（无负号）
        if (molecule == 0) {
            return "0";
        }
        // 分母为1，输出整数
        if (denominator == 1) {
            return String.valueOf(molecule);
        }
        // 常规分数格式：分子/分母
        return molecule + "/" + denominator;
    }
}