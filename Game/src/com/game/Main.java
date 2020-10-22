package com.game;

import java.util.Scanner;

public class Main {
    private static final Hero hero = new Hero();

    //分隔符
    public static void printSign() {
        for (int i = 0; i < 50; i++)
            System.out.print("*");
        System.out.println();
    }

    //初始界面
    public static void Welcome() {
        System.out.println("欢迎来到阿拉德大陆!");
        printSign();
        System.out.println("请选择你的职业:");
        System.out.println("1-射手(Blood:100,Energy:100)");
        System.out.println("2-法师(Blood:100,Energy:200)");
        System.out.println("3-战士(Blood:200)");
    }

    //创建角色
    public static void creatHero() {
        Scanner sc = new Scanner(System.in);

        System.out.print("职业选择:");
        int n = sc.nextInt();
        System.out.print("选择一个名字:");
        String name = sc.next();
        printSign();

        if (n == 1) {
            hero.setName(name);
            hero.setBlood(100);
            hero.setEnergy(100);
            hero.setId(1);
        } else if (n == 2) {
            hero.setName(name);
            hero.setBlood(100);
            hero.setEnergy(200);
            hero.setId(2);
        } else if (n == 3) {
            hero.setName(name);
            hero.setBlood(200);
            hero.setEnergy(0);
            hero.setId(3);
        }
    }

    //主游戏逻辑
    public static void mainGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println(hero.getName()+",Welcome");
        boolean flag = true;
        while (flag) {
            System.out.println("1-商店;2-战斗,3-信息,4-退出");
            System.out.print("你的选择:");
            int n = sc.nextInt();
            if (n == 1)
                Shop();
            if (n == 2) {
                Fight();
                System.out.println("战斗结束，请查看个人信息");
                printSign();
            }
            if (n == 3) {
                Show();
                printSign();
            }
            if (n == 4) {
                System.out.print("期待您的回归!");
                flag = false;
            }
        }
    }

    public static void Shop() {
        Scanner sc = new Scanner(System.in);
        System.out.println(hero.getName()+",您的金币数为:"+hero.getMoney());
        System.out.println("1-治疗与回复(10金币)");
        System.out.println("2-回复体力值(5金币)");
        System.out.println("3-升级(10金币)");
        System.out.println("4-离开(0金币)");
        System.out.print("你的选择:");
        int n = sc.nextInt();
        if(n == 1 && hero.getId() == 3) {
            hero.setBlood(200);
            hero.setEnergy(0);
            hero.setMoney(hero.getMoney() - 10);
            printSign();
        }
        else if(n == 1 && hero.getId() == 2) {
            hero.setBlood(100);
            hero.setEnergy(200);
            hero.setMoney(hero.getMoney() - 10);
            printSign();
        }
        else if(n == 1 && hero.getId() == 1) {
            hero.setBlood(100);
            hero.setEnergy(100);
            hero.setMoney(hero.getMoney() - 10);
            printSign();
        }

        if(n == 2) {
            hero.setStrength(100);
            hero.setMoney(hero.getMoney() - 5);
            printSign();
        }

        if(n == 3) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() - 10);
            printSign();
        }
        if(n == 4)
            printSign();
    }

    public static void Fight() {
        if(hero.getId() == 1) {
            hero.setBlood(hero.getBlood() - 2);
            hero.setEnergy(hero.getEnergy() - 2);
            hero.setStrength(hero.getStrength() - 1);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
        }
        if(hero.getId() == 2) {
            hero.setBlood(hero.getBlood() - 2);
            hero.setEnergy(hero.getEnergy() - 3);
            hero.setStrength(hero.getStrength() - 1);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
        }
        if(hero.getId() == 3) {
            hero.setBlood(hero.getBlood() - 1);
            hero.setStrength(hero.getStrength() - 2);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
        }
    }

    public static void Show() {
        System.out.println(hero.getName()+",您的英雄信息如下:");
        System.out.println("血量值:"+hero.getBlood());
        System.out.println("能量值:"+hero.getEnergy());
        System.out.println("体力值:"+hero.getStrength());
        System.out.println("等级:"+hero.getLevel());
        System.out.println("金币:"+hero.getMoney());
    }

    public static void main(String[] args) {
        Welcome();
        creatHero();
        mainGame();
    }

}