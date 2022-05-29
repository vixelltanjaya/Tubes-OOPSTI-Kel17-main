package com.monstersaku;

import com.monstersaku.util.*;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        
        
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();
            try {
                // System.out.printf("Filename: %s\n", "configs/movepool.csv");
                CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
                reader.setSkipHeader(true);
                List<String[]> lines = reader.read();
                 System.out.println("=========== CONTENT START ===========");
                
                CSVReader readerEffect = new CSVReader(new File(Main.class.getResource("configs/element-type-effectivity-chart.csv").toURI()), ";");
                readerEffect.setSkipHeader(true);
                List<String[]> linesEffect = readerEffect.read();
                // System.out.println(linesEffect);
                System.out.println("=========== CONTENT START ===========");
                
                for (String[] lineEffect:linesEffect){
                    // System.out.println(lineEffect);
                    ElementType EffSource = ElementType.valueOf(lineEffect[0]);
                    // System.out.println(ElementType.valueOf(lineEffect[0]));
                    ElementType EffTarget = ElementType.valueOf(lineEffect[1]);
                    // System.out.println(ElementType.valueOf(lineEffect[1]));
                    Double effectivity = Double.parseDouble(lineEffect[2]);
                    // System.out.println(Double.parseDouble(lineEffect[2]));

                    EffectivityPool.addEffectivity(EffSource, EffTarget, effectivity);
                }
                // System.out.println(EffectivityPool.getEffectivity(ElementType.GRASS, ElementType.FIRE));
                                
                // System.out.printf("Filename: %s\n", "configs/monsterpool.csv");
                CSVReader readerMon = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
                readerMon.setSkipHeader(true);
                List<String[]> linesMon = readerMon.read();
                // System.out.println(linesMon);
                List<Integer> ranIDList = new ArrayList<Integer>();
                List<String[]> dataMonster = new ArrayList<String[]>();
                System.out.println("=========== CONTENT START ===========");

                int totalMonsterpool = linesMon.size();//total monter di pool
                
                // bikin list 12 rand int 
                for (int i = 0;i<12;i++){
                    int ranId = (int)(Math.random()*totalMonsterpool+1);
                    ranIDList.add(ranId);
                }

                for (int i = 0; i < ranIDList.size(); i++) {
                    dataMonster.add(linesMon.get(ranIDList.get(i) - 1));
                }

                for (String[] lineMon : dataMonster) {
                    String stat = lineMon[3];
                    String[] arrofstats = stat.split(",",7);
                    ArrayList<Double> stats = new ArrayList<Double>();
                    for(String a : arrofstats){
                        Double d = Double.parseDouble(a);
                        // System.out.println(d);
                        stats.add(d);
                    }
                    Stats basestats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
                    ArrayList<ElementType> eltype = new ArrayList<ElementType>();
                    String eltaip = lineMon[2];
                    String[] arrofeltaip = eltaip.split(",",7);
                    for(String a : arrofeltaip){
                        if(a.equals("FIRE")){
                            eltype.add(ElementType.FIRE);
                        }
                        else if(a.equals("NORMAL")){
                            eltype.add(ElementType.NORMAL);
                        }
                        else if(a.equals("GRASS")){
                            eltype.add(ElementType.GRASS);
                        }
                        else if(a.equals("WATER")){
                            eltype.add(ElementType.WATER);
                        }
                    }
                    String movMon = lineMon[4];
                    List<String> arrofmov = new ArrayList<String>(Arrays.asList(movMon.split(",",7)));
                    List<Integer> arrintmove = new ArrayList<>();
                        for (String s : arrofmov) {
                            arrintmove.add(Integer.parseInt(s));
        }
                    List<String[]> datamove = new ArrayList<String[]>();
                    ArrayList<Move> monsmove = new ArrayList<Move>();

                    for (int i = 0; i < arrofmov.size(); i++) {
                        datamove.add(lines.get(arrintmove.get(i) - 1));
                    }

                    for (String[] line : datamove) {
                        Integer idmove = Integer.parseInt(line[0]);
                        MoveType tipemove = MoveType.valueOf(line[1]);
                        String movename = line[2];
                        ElementType moveelementType = ElementType.valueOf(line[3]);
                        Integer accuracy = Integer.parseInt(line[4]);
                        Integer priority = Integer.parseInt(line[5]);
                        Integer ammunition = Integer.parseInt(line[6]);
                        Target target = Target.valueOf(line[7]);
                        if(tipemove.equals(MoveType.STATUS)){
                            String condition = line[8];
                            String movestats = line[9];
                            String[] arrofmovestats = movestats.split(",",6);
                            Double hp = Double.parseDouble(arrofmovestats[0]);
                            Double atk = Double.parseDouble(arrofmovestats[1]);
                            Double def = Double.parseDouble(arrofmovestats[2]);
                            Double spcatk = Double.parseDouble(arrofmovestats[3]);
                            Double spcdef = Double.parseDouble(arrofmovestats[4]);
                            Double speed = Double.parseDouble(arrofmovestats[5]);
                            if (condition.equals("-")){
                                StatusMove mov = new StatusMove(idmove, tipemove, movename, moveelementType, accuracy, priority, ammunition, target, StatusCondition.NONE, hp, atk, def, spcatk, spcdef, speed);
                                monsmove.add(mov);
                            }
                            else{
                                StatusCondition statusCondition = StatusCondition.valueOf(line[8]);
                                StatusMove mov = new StatusMove(idmove, tipemove, movename, moveelementType, accuracy, priority, ammunition, target, statusCondition, hp, atk, def, spcatk, spcdef, speed);
                                monsmove.add(mov);
                            }
                            
                        }else if(tipemove.equals(MoveType.NORMAL)){
                            Double damage = Double.parseDouble(line[8]);
                            NormalMove mov = new NormalMove(idmove, tipemove, movename, moveelementType, accuracy, priority, ammunition, target, damage);
                            monsmove.add(mov);
                            
                        }
                        else if(tipemove.equals(MoveType.SPECIAL)){
                            Double damage = Double.parseDouble(line[8]);
                            SpecialMove mov = new SpecialMove(idmove, tipemove, movename, moveelementType, accuracy, priority, ammunition, target, damage);
                            monsmove.add(mov);
                            
                        }
                                              

                    }
                    DefaultMove mov = new DefaultMove();
                    monsmove.add(mov);
            

                    // System.out.println();
                    Integer idmons = Integer.parseInt(lineMon[0]);
                    Monster hoho = new Monster(idmons, lineMon[1], eltype, basestats, monsmove, false);
                    arrmonster.add(hoho);
                    
                }

                System.out.println("=========== CONTENT END ===========");
                System.out.println();
                } catch (Exception e) {
                }
                Player.start();
                Player.startgame();

                Scanner scan = new Scanner(System.in);
                System.out.println("Enter player 1 name: ");
                String player1name = scan.nextLine();
                System.out.println("Enter player 2 name: ");
                String player2name = scan.nextLine();
              
                List<Monster> player1monsterarray = new ArrayList<Monster>();
                for (int g = 0; g < 6;g++){
                    player1monsterarray.add(arrmonster.get(g));
                }
                
                List<Monster> player2monsterarray = new ArrayList<Monster>();
                for (int q = 6; q < 12;q++){
                    player2monsterarray.add(arrmonster.get(q));
                }
                
                Player player1 = new Player(player1name, player1monsterarray);
                Player player2 = new Player(player2name, player2monsterarray);
                
               
                boolean p1dead = 
                player1.getListMonster().get(0).getIsDead() &&
                player1.getListMonster().get(1).getIsDead() &&
                player1.getListMonster().get(2).getIsDead() &&
                player1.getListMonster().get(3).getIsDead() &&
                player1.getListMonster().get(4).getIsDead() &&
                player1.getListMonster().get(5).getIsDead();
                boolean p2dead = 
                player2.getListMonster().get(0).getIsDead() &&
                player2.getListMonster().get(1).getIsDead() &&
                player2.getListMonster().get(2).getIsDead() &&
                player2.getListMonster().get(3).getIsDead() &&
                player2.getListMonster().get(4).getIsDead() &&
                player2.getListMonster().get(5).getIsDead();

                

                boolean done = (p1dead || p2dead);
                Monster p1currM = player1.getListMonster().get(0);
                Monster p2currM = player2.getListMonster().get(0);
                int p1menu = 0;
                int p2menu = 0;
                int move1 = -1;
                int move2 = -1;
                int turn = 0;
                // System.out.println(done);
                
                
                
                while (done==false){
                    Boolean info = true;
                    while(info == true){
                        System.out.println("=====================================");
                        System.out.println("--Pilih Menu Berikut--");
                        System.out.println("1.View Monster Info");
                        System.out.println("2.View Game Info");
                        System.out.println("0.Continue Game");
                        Scanner s = new Scanner(System.in);
                        int information = 4;
                        information = s.nextInt();

                        if (information == 1){//view monster info
                            System.out.println("=====================================");
                            System.out.println("--Monster Info--");
                            System.out.println("=====================================");
                            System.out.println("-- Monster List "+player1name+" --");
                            System.out.println("=====================================");
                            System.out.println();
                            for (Monster m : player1.getListMonster()){
                                System.out.println(m.getNama());
                                System.out.printf("%s","Type :");
                                for (int elm1 = 0;elm1 < m.getElementTypes().size()-1;elm1++){
                                    System.out.printf("%s, ",m.getElementTypes().get(elm1));
                                }
                                System.out.printf("%s",m.getElementTypes().get(m.getElementTypes().size()-1));
                                System.out.println();
                                System.out.println("    HP: " + m.getBaseStats().getHP()+" / " + m.getBaseStats().getMaxHP());
                                System.out.println("    Attack: " + m.getBaseStats().getAttackPoint());
                                System.out.println("    Defense: " + m.getBaseStats().getDefensePoint());
                                System.out.println("    Sp.Attack: " + m.getBaseStats().getSpecialAttackPoint());
                                System.out.println("    Sp.Defense: " + m.getBaseStats().getSpecialDefensePoint());
                                System.out.println("    Speed: " + m.getBaseStats().getSpeed());
                                System.out.println();
                            }
                            System.out.println("=====================================");
                            System.out.println("-- Monster List "+player2name+" --");  
                            System.out.println("=====================================");
                            System.out.println();
                            for (Monster k : player2.getListMonster()){
                                System.out.println(k.getNama());
                                System.out.printf("%s","Type :");
                                for (int elm2 = 0;elm2 < k.getElementTypes().size()-1;elm2++){
                                    System.out.printf("%s, ",k.getElementTypes().get(elm2));
                                }
                                System.out.printf("%s",k.getElementTypes().get(k.getElementTypes().size()-1));
                                System.out.println();
                                System.out.println("    HP: " + k.getBaseStats().getHP()+" / " + k.getBaseStats().getMaxHP());
                                System.out.println("    Attack: " + k.getBaseStats().getAttackPoint());
                                System.out.println("    Defense: " + k.getBaseStats().getDefensePoint());
                                System.out.println("    Sp.Attack: " + k.getBaseStats().getSpecialAttackPoint());
                                System.out.println("    Sp.Defense: " + k.getBaseStats().getSpecialDefensePoint());
                                System.out.println("    Speed: " + k.getBaseStats().getSpeed());
                                System.out.println();    
                            }
                        }
                        else if (information == 2){
                            System.out.println("=====================================");
                            System.out.println("--Game Info--");// print game info
                            System.out.println("=====================================");
                            System.out.println("Turn: "+turn);
                            System.out.println("=====================================");
                            System.out.println("-- Monster List "+player1name+" --");
                            System.out.println("=====================================");
                            player1.printGameInfo(p1currM);
                            System.out.println("=====================================");
                            System.out.println("-- Monster List "+player2name+" --");
                            System.out.println("=====================================");
                            player2.printGameInfo(p2currM);

                        }
                        else if (information == 0){
                            info = false;
                        }
                        else{
                           System.out.println("Input yang anda masukkan salah silahkan melakukan input lagi"); 
                        }

                    }

                    Player.newTurn();
                    turn++;
                    System.out.println();
                    int c1 =0;
                    int c2 =0;
                    System.out.println("=====================================");
                    System.out.println("-- Monster List "+player1name+" --");
                    for (Monster m : player1.getListMonster()){
                        if (m.equals(p1currM)){
                            System.out.println(c1+1+". "+m.getNama()+" --On Field");
                        }
                        else if(m.isIsDead()==true){
                            System.out.println(c1+1+". "+m.getNama()+" --Fainted");
                        }
                        else{
                        System.out.println(c1+1+". "+m.getNama());
                        }
                        c1++;

                    }
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("-- Monster List "+player2name+" --");
                    for (Monster m : player2.getListMonster()){
                        if (m.equals(p2currM)){
                            System.out.println(c2+1+". "+m.getNama()+" --On Field");
                        }
                        else if(m.isIsDead()==true){
                            System.out.println(c2+1+". "+m.getNama()+" --Fainted");
                        }
                        else {
                        System.out.println(c2+1+". "+m.getNama());
                        }
                        c2++;
                    }
                    Scanner sc = new Scanner(System.in);
                    while (!(p1menu == 1 || p1menu == 2)){
                        System.out.println("=====================================");
                        System.out.println("Giliran "+player1name);
                        System.out.println("-- Pilih Menu Berikut --");
                        System.out.println("   1. Move");
                        System.out.println("   2. Switch");
                        System.out.println("Menu yang kamu pilih : ");
                        p1menu = sc.nextInt();
                    }
                    if (p1menu == 1) {
                        boolean menu1done = true;
                        while (menu1done){
                            System.out.println("=====================================");
                            System.out.println("Pilih move yang akan digunakan:");
                            p1currM.printMove();
                            move1 = sc.nextInt();
                            if (move1> p1currM.getMoves().size()| move1 < 1){
                                System.out.println("Masukan tidak sesuai silahkan ulangi");
                                menu1done = true;
                            }
                            else{
                                if (p1currM.getMoves().get(move1-1).getAmmunition()<= 0){
                                    System.out.println(p1currM.getMoves().get(move1-1).getName()+" tidak dapat digunakan karena amunisi habis");
                                    System.out.println("silahkan memilih move kembali");
                                    menu1done = true;
                                }
                                else{
                                    menu1done =false;
                                }
                            } 
                        }
                    }
                    else if (p1menu == 2) {
                        p1currM = player1.switchMenu(p1currM);
                    }
                    while (!(p2menu == 1 || p2menu == 2)){
                        System.out.println("=====================================");
                        System.out.println("Giliran "+player2name);
                        System.out.println("-- Pilih Menu Berikut --");
                        System.out.println("   1. Move");
                        System.out.println("   2. Switch");
                        System.out.println("Menu yang kamu pilih : ");

                        p2menu = sc.nextInt();
                    }
                    if (p2menu == 1) {
                        Boolean menu2done = true;
                        while (menu2done){
                            System.out.println("=====================================");
                            System.out.println("Pilih move yang akan digunakan:");
                            p2currM.printMove();
                            move2 = sc.nextInt();
                            if (move2>  p2currM.getMoves().size()|| move2 < 1){
                                System.out.println("Masukan tidak sesuai silahkan ulangi");
                                menu2done = true;
                            }
                            else{
                                if (p2currM.getMoves().get(move2-1).getAmmunition()<= 0){
                                    System.out.println(p2currM.getMoves().get(move2-1).getName()+" tidak dapat digunakan karena amunisi habis");
                                    System.out.println("silahkan memilih move kembali");
                                    menu2done = true;
                                }
                                else{
                                    menu2done =false;
                                }
                            } 
                        }
                    }
                    else if (p2menu == 2) {
                        p2currM = player2.switchMenu(p2currM);
                    }
                    player1.beforeEffect();
                    player2.beforeEffect();
                    System.out.println("=====================================");
                    if (p1menu == 1 && p2menu == 2){
                        System.out.println(p1currM.getNama()+" menggunakan "+p1currM.getMoves().get(move1-1).getName());
                        if (p1currM.getSleepCount()>0){
                            System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                        }
                        else{
                            p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                        }
                    }
                    else if (p1menu == 2 && p2menu == 1){
                        System.out.println(p2currM.getNama()+" menggunakan "+p2currM.getMoves().get(move2-1).getName());
                        if (p2currM.getSleepCount()>0){
                            System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                        }
                        else{
                            p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                        }
                    }
                    else if (p1menu == 1 && p2menu == 1){
                        if(p1currM.getMoves().get(move1-1).getPriority()>p2currM.getMoves().get(move2-1).getPriority()){
                            if (p1currM.getSleepCount()>0){
                                System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                            }
                            else{
                                p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                            }
                            if (!p2currM.isIsDead()){
                                if(p2currM.getSleepCount()>0){
                                    System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                }
                                else{
                                    p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                                } 
                            }
                        }
                        else if(p1currM.getMoves().get(move1-1).getPriority()<p2currM.getMoves().get(move2-1).getPriority()){
                            if(p2currM.getSleepCount()>0){
                                System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                            }
                            else{
                                p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                            } 
                            if (!p1currM.isIsDead()){
                                if (p1currM.getSleepCount()>0){
                                    System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                }
                                else{
                                    p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                                }
                            }
                        }
                        else if (p1currM.getMoves().get(move1-1).getPriority()==p2currM.getMoves().get(move2-1).getPriority()){
                            if(p1currM.getBaseStats().getSpeed()>p2currM.getBaseStats().getSpeed()){
                                if (p1currM.getSleepCount()>0){
                                    System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                }
                                else{
                                    p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                                }
                                if (!p2currM.isIsDead()){
                                    if(p2currM.getSleepCount()>0){
                                        System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                    }
                                    else{
                                        p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                                    } 
                                }
                            }
                            else if(p1currM.getBaseStats().getSpeed()<p2currM.getBaseStats().getSpeed()){
                                if(p2currM.getSleepCount()>0){
                                    System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                }
                                else{
                                    p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                                } 
                                if (!p1currM.isIsDead()){
                                    if (p1currM.getSleepCount()>0){
                                        System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                    }
                                    else{
                                        p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                                    }
                                }
                            }
                            else{
                                Random random = new Random();
                                int urutan = random.nextInt(1);
                                if (urutan==0){
                                    if (p1currM.getSleepCount()>0){
                                        System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                    }
                                    else{
                                        p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                                    }
                                    if (!p2currM.isIsDead()){
                                        if(p2currM.getSleepCount()>0){
                                            System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                        }
                                        else{
                                            p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                                        } 
                                    }
                                }
                                else{
                                    if(p2currM.getSleepCount()>0){
                                        System.out.println(p2currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                    }
                                    else{
                                        p2currM.getMoves().get(move2-1).execute(p2currM, p1currM);
                                    } 
                                    if (!p1currM.isIsDead()){
                                        if (p1currM.getSleepCount()>0){
                                            System.out.println(p1currM.getNama()+" tidak bisa bergerak karena effek sleep");
                                        }
                                        else{
                                            p1currM.getMoves().get(move1-1).execute(p1currM, p2currM);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    player1.afterEffect();
                    player2.afterEffect();
                    System.out.println("=====================================");
                    System.out.println(p1currM.getNama()+" "+p1currM.getBaseStats().getHP()+"/"+p1currM.getBaseStats().getMaxHP());
                    if (p1currM.isIsDead()){
                        System.out.println(p1currM.getNama()+" fainted");
                        int deadMonC1 = 0;
                        for (Monster m : player1.listMonster){
                            if (m.isIsDead()==true){
                                deadMonC1++;
                            }
                        }
                        if(deadMonC1<=5){
                            p1currM = player1.switchMenu(p1currM);
                        }
                    }    
                    System.out.println("=====================================");
                    System.out.println(p2currM.getNama()+" "+p2currM.getBaseStats().getHP()+"/"+p2currM.getBaseStats().getMaxHP());
                    if (p2currM.isIsDead()){
                        System.out.println(p2currM.getNama()+" fainted");
                        int deadMonC2 = 0;
                        for (Monster m : player2.listMonster){
                            if (m.isIsDead()==true){
                                deadMonC2++;
                            }
                        }
                        if(deadMonC2<=5){
                            p2currM = player2.switchMenu(p2currM);
                        }
                    }    

                    p1dead = 
                    player1.getListMonster().get(0).getIsDead() &&
                    player1.getListMonster().get(1).getIsDead() &&
                    player1.getListMonster().get(2).getIsDead() &&
                    player1.getListMonster().get(3).getIsDead() &&
                    player1.getListMonster().get(4).getIsDead() &&
                    player1.getListMonster().get(5).getIsDead();
                    p2dead = 
                    player2.getListMonster().get(0).getIsDead() &&
                    player2.getListMonster().get(1).getIsDead() &&
                    player2.getListMonster().get(2).getIsDead() &&
                    player2.getListMonster().get(3).getIsDead() &&
                    player2.getListMonster().get(4).getIsDead() &&
                    player2.getListMonster().get(5).getIsDead();

                    if (p1dead == true || p2dead == true){
                        done =true;
                    }                    
                    p1menu = 0;
                    p2menu = 0;
                }
                System.out.println("=====================================");
                if (p1dead == true){
                    System.out.println(player2name+" WINS");
                }
                else if (p2dead == true){
                    System.out.println(player1name+" WINS");
                }
                else{
                    System.out.println("TIE");
                }
    }
}

