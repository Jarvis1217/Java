package com.game;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final Hero hero = new Hero();
    protected static String path = "./dataBase/";

    //分隔符
    public static void printSign() {
        for (int i = 0; i < 50; i++)
            System.out.print("*");
        System.out.println();
    }

    //初始界面
    public static String Welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print("选择一个名字:");
        String name = sc.next();
        printSign();
        return name;
    }

    //创建角色
    public static int creatHero(String name) {
        File file = new File(path + name + ".txt");
        if (file.exists()) {
            int[] attribute = new int[6];
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(path + name + ".txt"));
                BufferedReader br = new BufferedReader(reader);
                String line = "";
                try {
                    line = br.readLine();
                    int i = 0;
                    while (line != null) {
                        attribute[i] = Integer.parseInt(line);
                        line = br.readLine();
                        i += 1;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            hero.setId(attribute[0]);
            hero.setName(name);
            hero.setBlood(attribute[1]);
            hero.setEnergy(attribute[2]);
            hero.setStrength(attribute[3]);
            hero.setLevel(attribute[4]);
            hero.setMoney(attribute[5]);
            return 0;
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("请选择你的职业:");
        System.out.println("1-射手(Blood:100,Energy:100)");
        System.out.println("2-法师(Blood:100,Energy:200)");
        System.out.println("3-战士(Blood:200)");

        System.out.print("职业选择:");
        int n = sc.nextInt();
        printSign();

        if (n == 1) {
            hero.setName(name);
            hero.setBlood(100);
            hero.setEnergy(100);
            hero.setId(1);
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(path + name + ".txt"));
                out.write("1\r\n");
                out.write("100\r\n");
                out.write("100\r\n");
                out.write("100\r\n");
                out.write("1\r\n");
                out.write("0\r\n");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (n == 2) {
            hero.setName(name);
            hero.setBlood(100);
            hero.setEnergy(200);
            hero.setId(2);

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(path + name + ".txt"));
                out.write("2\r\n");
                out.write("100\r\n");
                out.write("200\r\n");
                out.write("100\r\n");
                out.write("1\r\n");
                out.write("0\r\n");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (n == 3) {
            hero.setName(name);
            hero.setBlood(200);
            hero.setEnergy(0);
            hero.setId(3);

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(path + name + ".txt"));
                out.write("3\r\n");
                out.write("200\r\n");
                out.write("0\r\n");
                out.write("100\r\n");
                out.write("1\r\n");
                out.write("0\r\n");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //主游戏逻辑
    public static void mainGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println(hero.getName() + ",Welcome");
        boolean flag = true;
        while (flag) {
            System.out.println("1-商店;2-战斗,3-信息,4-退出");
            System.out.print("你的选择:");
            int n = sc.nextInt();
            if (n == 1)
                Shop();
            if (n == 2) {
                Fight();
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

    public static int Shop() {
        Scanner sc = new Scanner(System.in);
        System.out.println(hero.getName() + ",您的金币数为:" + hero.getMoney());
        System.out.println("1-治疗与回复(10金币)");
        System.out.println("2-回复体力值(5金币)");
        System.out.println("3-升级(10金币)");
        System.out.println("4-离开(0金币)");
        System.out.print("你的选择:");
        int n = sc.nextInt();
        if (n == 1 && hero.getId() == 3 && hero.getMoney() >= 10) {
            hero.setBlood(200 + hero.getLevel()*2);
            hero.setEnergy(0);
            hero.setMoney(hero.getMoney() - 10);
            overWrite();
            System.out.println("欢迎下次光临!");
            printSign();
            return 0;
        } else if (n == 1 && hero.getId() == 2 && hero.getMoney() >= 10) {
            hero.setBlood(100 + hero.getLevel());
            hero.setEnergy(200 + hero.getLevel()*2);
            hero.setMoney(hero.getMoney() - 10);
            overWrite();
            System.out.println("欢迎下次光临!");
            printSign();
            return 0;
        } else if (n == 1 && hero.getId() == 1 && hero.getMoney() >= 10) {
            hero.setBlood(100 + hero.getLevel());
            hero.setEnergy(100 + hero.getLevel());
            hero.setMoney(hero.getMoney() - 10);
            overWrite();
            System.out.println("欢迎下次光临!");
            printSign();
            return 0;
        }

        if (n == 2 && hero.getMoney() >= 5) {
            hero.setStrength(100);
            hero.setMoney(hero.getMoney() - 5);
            overWrite();
            System.out.println("欢迎下次光临!");
            printSign();
            return 0;
        }

        if (n == 3 && hero.getMoney() >= 10) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() - 10);
            System.out.println("欢迎下次光临!");
            if(hero.getId() == 1) {
                hero.setBlood(hero.getBlood() + 1);
                hero.setEnergy(hero.getEnergy() + 1);
                overWrite();
            }
            else if(hero.getId() == 2) {
                hero.setBlood(hero.getBlood() + 1);
                hero.setEnergy(hero.getEnergy() +2);
                overWrite();
            }
            else if(hero.getId() == 3) {
                hero.setBlood(hero.getBlood() + 2);
                overWrite();
            }
            overWrite();
            printSign();
            return 0;
        }
        if (n == 4) {
            System.out.println("欢迎下次光临!");
            printSign();
            return 0;
        } else {
            System.out.println("余额不足!");
            printSign();
        }
        return 0;
    }

    public static int Fight() {
        if (hero.getId() == 1 && hero.getBlood() >= 2 && hero.getEnergy() >= 2 && hero.getStrength() >= 1) {
            hero.setBlood(hero.getBlood() - 2);
            hero.setEnergy(hero.getEnergy() - 2);
            hero.setStrength(hero.getStrength() - 1);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
            overWrite();
            hero.setBlood(hero.getBlood() + 1);
            hero.setEnergy(hero.getEnergy() + 1);
            overWrite();
            System.out.println("战斗结束，请查看个人信息");
            printSign();
            return 0;
        }
        if (hero.getId() == 2 && hero.getBlood() >= 2 && hero.getEnergy() >= 3 && hero.getStrength() >= 1) {
            hero.setBlood(hero.getBlood() - 2);
            hero.setEnergy(hero.getEnergy() - 3);
            hero.setStrength(hero.getStrength() - 1);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
            overWrite();
            hero.setBlood(hero.getBlood() + 1);
            hero.setEnergy(hero.getEnergy() +2);
            overWrite();
            System.out.println("战斗结束，请查看个人信息");
            printSign();
            return 0;
        }
        if (hero.getId() == 3 && hero.getBlood() >= 1 && hero.getStrength() >= 2) {
            hero.setBlood(hero.getBlood() - 3);
            hero.setStrength(hero.getStrength() - 2);
            hero.setLevel(hero.getLevel() + 1);
            hero.setMoney(hero.getMoney() + 2);
            overWrite();
            hero.setBlood(hero.getBlood() + 2);
            overWrite();
            System.out.println("战斗结束，请查看个人信息");
            printSign();
            return 0;
        } else {
            System.out.println("无法战斗,请及时恢复!");
            printSign();
        }
        return 0;
    }

    public static void Show() {
        System.out.println(hero.getName() + ",您的英雄信息如下:");
        int[] attribute = new int[6];
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path + hero.getName() + ".txt"));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            try {
                line = br.readLine();
                int i = 0;
                while (line != null) {
                    attribute[i] = Integer.parseInt(line);
                    line = br.readLine();
                    i += 1;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("血量值:" + attribute[1]);
        System.out.println("能量值:" + attribute[2]);
        System.out.println("体力值:" + attribute[3]);
        System.out.println("等级:" + attribute[4]);
        System.out.println("金币:" + attribute[5]);
    }

    public static void overWrite() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path + hero.getName() + ".txt"));
            clearFile();
            out.write(hero.getId() + "\r\n");
            out.write(hero.getBlood() + "\r\n");
            out.write(hero.getEnergy() + "\r\n");
            out.write(hero.getStrength() + "\r\n");
            out.write(hero.getLevel() + "\r\n");
            out.write(hero.getMoney() + "\r\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFile() {
        File file = new File(path + hero.getName() + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String name = Welcome();
        creatHero(name);
        mainGame();
    }

}